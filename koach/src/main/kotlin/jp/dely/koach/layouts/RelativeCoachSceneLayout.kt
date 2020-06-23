package jp.dely.koach.layouts

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.ViewSpec
import kotlin.math.min

@SuppressLint("ViewConstructor", "RtlHardcoded")
class RelativeCoachSceneLayout(
    context: Context,
    private val targetViewSpec: ViewSpec,
    private val direction: RelativeDirection,
    @Px
    private val marginTop: Int,
    @Px
    private val marginBottom: Int,
    @Px
    private val marginLeft: Int,
    @Px
    private val marginRight: Int
) : ViewGroup(context), ICoachSceneLayout {

    override fun addCoachView(view: View) {
        addView(view)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)


        val viewSpaceHorizontal = when (direction) {
            RelativeDirection.Above, RelativeDirection.Below -> 0
            RelativeDirection.Left -> width - targetViewSpec.rect.left
            RelativeDirection.Right -> targetViewSpec.rect.right
        }
        val viewSpaceVertical = when (direction) {
            RelativeDirection.Above -> height - targetViewSpec.rect.top
            RelativeDirection.Below -> targetViewSpec.rect.bottom
            RelativeDirection.Left, RelativeDirection.Right -> 0
        }

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }

            child.measure(
                calculateMeasureSpec(
                    widthMeasureSpec,
                    child.layoutParams?.width ?: LayoutParams.WRAP_CONTENT,
                    marginLeft + marginRight + viewSpaceHorizontal
                ),
                calculateMeasureSpec(
                    heightMeasureSpec,
                    child?.layoutParams?.height ?: LayoutParams.WRAP_CONTENT,
                    marginTop + marginBottom + viewSpaceVertical
                )
            )
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue
            }

            val left = when (direction) {
                RelativeDirection.Above -> marginLeft
                RelativeDirection.Below -> marginLeft
                RelativeDirection.Left -> targetViewSpec.rect.left - child.measuredWidth - marginRight
                RelativeDirection.Right -> targetViewSpec.rect.right + marginLeft
            }
            val top = when (direction) {
                RelativeDirection.Above -> targetViewSpec.rect.top - child.measuredHeight - marginBottom
                RelativeDirection.Below -> targetViewSpec.rect.bottom + marginTop
                RelativeDirection.Left -> marginTop
                RelativeDirection.Right -> marginTop
            }

            child.layout(left, top, left + child.measuredWidth, top + child.measuredHeight)
        }
    }

    private fun calculateMeasureSpec(
        measureSpec: Int,
        layoutParameterSize: Int,
        margin: Int
    ): Int {
        val parentMode = MeasureSpec.getMode(measureSpec)
        val parentSize = MeasureSpec.getSize(measureSpec)
        val size: Int
        val mode: Int
        when (parentMode) {
            MeasureSpec.AT_MOST -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.AT_MOST
                }
                else -> {
                    size = min(layoutParameterSize, parentSize - margin)
                    mode = MeasureSpec.EXACTLY
                }
            }
            MeasureSpec.UNSPECIFIED -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.UNSPECIFIED
                }
                else -> {
                    size = min(layoutParameterSize, parentSize - margin)
                    mode = MeasureSpec.AT_MOST
                }
            }
            else -> when (layoutParameterSize) {
                LayoutParams.MATCH_PARENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.EXACTLY
                }
                LayoutParams.WRAP_CONTENT -> {
                    size = parentSize - margin
                    mode = MeasureSpec.AT_MOST
                }
                else -> {
                    size = min(layoutParameterSize, parentSize - margin)
                    mode = MeasureSpec.EXACTLY
                }
            }
        }
        return MeasureSpec.makeMeasureSpec(size, mode)
    }
}