package jp.dely.koach.layouts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.ViewSpec
import kotlin.math.max
import kotlin.math.min

@SuppressLint("ViewConstructor", "RtlHardcoded")
class AnchorCoachSceneLayout(
    context: Context,
    private val targetViewSpec: ViewSpec,
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

    override var coachView: View? = null
        private set

    override fun addCoachView(view: View) {
        addView(view)
        coachView = view
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

            val childMaxWidth = calculateMeasureMaxSize(
                targetViewSpec.rect.left,
                targetViewSpec.rect.right,
                width,
                marginHorizontal,
                convertRelativeHorizontalGravity(gravity),
                convertRelativeHorizontalGravity(anchorGravity)
            )
            val childMaxHeight = calculateMeasureMaxSize(
                targetViewSpec.rect.top,
                targetViewSpec.rect.bottom,
                height,
                marginVertical,
                convertRelativeVerticalGravity(gravity),
                convertRelativeVerticalGravity(anchorGravity)
            )

            child.measure(
                calculateMeasureSpec(
                    widthMeasureSpec,
                    childMaxWidth,
                    child.layoutParams?.width ?: LayoutParams.WRAP_CONTENT
                ),
                calculateMeasureSpec(
                    heightMeasureSpec,
                    childMaxHeight,
                    child?.layoutParams?.height ?: LayoutParams.WRAP_CONTENT
                )
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

    private fun calculateMeasureMaxSize(
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

    private fun calculateMeasureSpec(
        measureSpec: Int,
        maxSize: Int,
        layoutParameterSize: Int
    ): Int {
        val parentMode = MeasureSpec.getMode(measureSpec)
        val size: Int
        val mode: Int
        when (parentMode) {
            MeasureSpec.AT_MOST -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = maxSize
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = maxSize
                    mode = MeasureSpec.AT_MOST
                }
                else -> {
                    size = min(layoutParameterSize, maxSize)
                    mode = MeasureSpec.EXACTLY
                }
            }
            MeasureSpec.UNSPECIFIED -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = maxSize
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = maxSize
                    mode = MeasureSpec.UNSPECIFIED
                }
                else -> {
                    size = min(layoutParameterSize, maxSize)
                    mode = MeasureSpec.AT_MOST
                }
            }
            else -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = maxSize
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = maxSize
                    mode = MeasureSpec.AT_MOST
                }
                else -> {
                    size = min(layoutParameterSize, maxSize)
                    mode = MeasureSpec.EXACTLY
                }
            }
        }
        return MeasureSpec.makeMeasureSpec(size, mode)
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