package net.meilcli.koach

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View

@SuppressLint("ViewConstructor")
class CoachOverlayView(
    context: Context,
    private val coach: Coach
) : View(context), ValueAnimator.AnimatorUpdateListener {

    private class AnimatorListener(
        private val coach: Coach,
        private val coachScene: CoachScene
    ) : Animator.AnimatorListener {

        override fun onAnimationRepeat(animation: Animator?) {
            coach.overlay.animatedListener.animationEvent(
                coach,
                coachScene,
                IOverlayAnimatedListener.Event.Repeat
            )
        }

        override fun onAnimationEnd(animation: Animator?) {
            coach.overlay.animatedListener.animationEvent(
                coach,
                coachScene,
                IOverlayAnimatedListener.Event.End
            )
        }

        override fun onAnimationCancel(animation: Animator?) {
            coach.overlay.animatedListener.animationEvent(
                coach,
                coachScene,
                IOverlayAnimatedListener.Event.Cancel
            )
        }

        override fun onAnimationStart(animation: Animator?) {
            coach.overlay.animatedListener.animationEvent(
                coach,
                coachScene,
                IOverlayAnimatedListener.Event.Start
            )
        }
    }

    private val overlayPaint = Paint().apply {
        color = coach.overlay.color
        isAntiAlias = true
    }
    private val transparentPaint = Paint().apply {
        color = Color.TRANSPARENT
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private var lastTouchX: Float? = null
    private var lastTouchY: Float? = null

    private var coachScene: CoachScene? = null
    private var targetViewSpec: ViewSpec = ViewSpec.empty
    private var shapeAnimator: ValueAnimator? = null

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        setOnTouchListener { _, event ->
            lastTouchX = event.x
            lastTouchY = event.y
            false
        }
        if (coach.overlay.canClickSurfaceView.not()) {
            setOnClickListener {
                val coachScene = coachScene ?: return@setOnClickListener
                val lastTouchX = lastTouchX
                val lastTouchY = lastTouchY

                val clicked = if (coach.overlay.canClickTargetView &&
                    lastTouchX != null &&
                    lastTouchY != null &&
                    targetViewSpec.rect.contains(lastTouchX.toInt(), lastTouchY.toInt())
                ) {
                    targetViewSpec.invokeClick(lastTouchX, lastTouchY)
                    IOverlayClickListener.Clicked.Target
                } else {
                    IOverlayClickListener.Clicked.OutSide
                }
                coach.overlay.clickListener.click(coach, coachScene, clicked)
            }
        }
    }

    fun setScene(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        this.coachScene = coachScene
        this.targetViewSpec = targetViewSpec

        shapeAnimator?.cancel()

        val overlayShape = coachScene.overlayShape
        if (overlayShape is IAnimationOverlayShape) {
            shapeAnimator = ValueAnimator.ofFloat(*(overlayShape.animations.values)).apply {
                duration = overlayShape.duration
                interpolator = overlayShape.interpolator
                repeatCount = overlayShape.repeatCount
                overlayShape.repeatMode?.also {
                    repeatMode = it
                }
                addUpdateListener(this@CoachOverlayView)
                addListener(AnimatorListener(coach, coachScene))
            }
            shapeAnimator?.start()
        } else {
            shapeAnimator = null
        }

        invalidate()
    }

    fun finishAnimation() {
        shapeAnimator?.cancel()
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        if (animation?.isRunning == true) {
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), overlayPaint)

        when (val overlayShape = coachScene?.overlayShape) {
            is IAnimationOverlayShape -> {
                val animator = shapeAnimator
                if (animator != null) {
                    overlayShape.draw(
                        canvas,
                        transparentPaint,
                        targetViewSpec,
                        animator.animatedValue as Float
                    )
                } else {
                    overlayShape.draw(canvas, transparentPaint, targetViewSpec)
                }
            }
            is IOverlayShape -> overlayShape.draw(canvas, transparentPaint, targetViewSpec)
        }
    }
}