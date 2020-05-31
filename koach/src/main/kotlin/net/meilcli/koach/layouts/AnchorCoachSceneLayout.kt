package net.meilcli.koach.layouts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import net.meilcli.koach.ICoachSceneLayout
import net.meilcli.koach.TargetViewSpec
import kotlin.math.max

@SuppressLint("ViewConstructor", "RtlHardcoded")
class AnchorCoachSceneLayout(
    context: Context,
    private val targetViewSpec: TargetViewSpec,
    private val targetAnchorGravity: Int,
    private val gravity: Int,
    @Px
    private val marginVertical: Int,
    @Px
    private val marginHorizontal: Int
) : ViewGroup(context), ICoachSceneLayout {

    companion object {

        private const val defaultGravity = Gravity.TOP or Gravity.LEFT
    }

    override fun addCoachView(view: View) {
        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        addView(view, layoutParams)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val anchorGravity = if (targetAnchorGravity == Gravity.NO_GRAVITY) {
            defaultGravity
        } else {
            targetAnchorGravity
        }
        val gravity = if (this.gravity == Gravity.NO_GRAVITY) defaultGravity else this.gravity

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }

            val childWidth = calculateMeasureSize(
                targetViewSpec.rect.left,
                targetViewSpec.rect.right,
                width,
                marginHorizontal,
                convertRelativeHorizontalGravity(gravity),
                convertRelativeHorizontalGravity(anchorGravity)
            )
            val childHeight = calculateMeasureSize(
                targetViewSpec.rect.top,
                targetViewSpec.rect.bottom,
                height,
                marginVertical,
                convertRelativeVerticalGravity(gravity),
                convertRelativeVerticalGravity(anchorGravity)
            )

            child.measure(
                MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST)
            )
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val anchorGravity = if (targetAnchorGravity == Gravity.NO_GRAVITY) {
            defaultGravity
        } else {
            targetAnchorGravity
        }
        val gravity = if (this.gravity == Gravity.NO_GRAVITY) defaultGravity else this.gravity

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }

            val left = calculateLayoutPosition(
                targetViewSpec.rect.left,
                targetViewSpec.rect.right,
                child.measuredWidth,
                marginHorizontal,
                convertRelativeHorizontalGravity(gravity),
                convertRelativeHorizontalGravity(anchorGravity)
            )
            val top = calculateLayoutPosition(
                targetViewSpec.rect.top,
                targetViewSpec.rect.bottom,
                child.measuredHeight,
                marginVertical,
                convertRelativeVerticalGravity(gravity),
                convertRelativeVerticalGravity(anchorGravity)
            )

            child.layout(left, top, left + child.measuredWidth, top + child.measuredHeight)
        }
    }

    private fun convertRelativeHorizontalGravity(gravity: Int): Int {
        val absoluteGravity = if (Build.VERSION_CODES.JELLY_BEAN_MR1 < Build.VERSION.SDK_INT) {
            Gravity.getAbsoluteGravity(gravity, layoutDirection)
        } else {
            gravity
        }
        return when (absoluteGravity and Gravity.HORIZONTAL_GRAVITY_MASK) {
            Gravity.CENTER_HORIZONTAL -> Gravity.CENTER
            Gravity.RIGHT -> Gravity.END
            Gravity.LEFT -> Gravity.START
            else -> Gravity.START
        }
    }

    private fun convertRelativeVerticalGravity(gravity: Int): Int {
        val absoluteGravity = if (Build.VERSION_CODES.JELLY_BEAN_MR1 < Build.VERSION.SDK_INT) {
            Gravity.getAbsoluteGravity(gravity, layoutDirection)
        } else {
            gravity
        }
        return when (absoluteGravity and Gravity.VERTICAL_GRAVITY_MASK) {
            Gravity.CENTER_VERTICAL -> Gravity.CENTER
            Gravity.TOP -> Gravity.START
            Gravity.BOTTOM -> Gravity.END
            else -> Gravity.START
        }
    }

    private fun calculateMeasureSize(
        anchorStart: Int,
        anchorEnd: Int,
        size: Int,
        margin: Int,
        gravity: Int,
        anchorGravity: Int
    ): Int {
        val basePoint = when (anchorGravity) {
            Gravity.CENTER -> (anchorStart + anchorEnd) / 2
            Gravity.END -> anchorEnd
            Gravity.START -> anchorStart
            else -> anchorStart
        }
        return max(
            when (gravity) {
                Gravity.CENTER -> size
                Gravity.END -> size - (basePoint + margin)
                Gravity.START -> basePoint - margin
                else -> basePoint - margin
            },
            0
        )
    }

    private fun calculateLayoutPosition(
        anchorStart: Int,
        anchorEnd: Int,
        size: Int,
        margin: Int,
        gravity: Int,
        anchorGravity: Int
    ): Int {
        val basePoint = when (anchorGravity) {
            Gravity.CENTER -> (anchorStart + anchorEnd) / 2
            Gravity.END -> anchorEnd
            Gravity.START -> anchorStart
            else -> anchorStart
        }
        return when (gravity) {
            Gravity.CENTER -> basePoint - size / 2
            Gravity.END -> basePoint + margin
            Gravity.START -> basePoint - size - margin
            else -> basePoint - size - margin
        }
    }
}