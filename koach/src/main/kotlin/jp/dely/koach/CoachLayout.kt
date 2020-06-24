package jp.dely.koach

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout

@SuppressLint("ViewConstructor")
class CoachLayout(
    context: Context,
    private val coach: Coach
) : FrameLayout(context) {

    private abstract class BaseAnimatorListener(
        private val coach: Coach,
        private val coachScene: CoachScene
    ) : Animator.AnimatorListener {

        private fun raiseEvent(event: AnimatedEvent) {
            coachScene.coachSceneAnimation?.listener?.animationEvent(
                coach,
                coachScene,
                event
            )
        }

        override fun onAnimationRepeat(animation: Animator?) {
            raiseEvent(AnimatedEvent.Repeat)
        }

        override fun onAnimationEnd(animation: Animator?) {
            raiseEvent(AnimatedEvent.End)
        }

        override fun onAnimationCancel(animation: Animator?) {
            raiseEvent(AnimatedEvent.Cancel)
        }

        override fun onAnimationStart(animation: Animator?) {
            raiseEvent(AnimatedEvent.Start)
        }
    }

    private inner class AnimatorUpdateListener(
        private val animator: ICoachSceneAnimator
    ) : ValueAnimator.AnimatorUpdateListener {

        override fun onAnimationUpdate(animation: ValueAnimator?) {
            animation ?: return
            val coachScene = currentCoachScene ?: return
            val targetViewSpec = currentTargetViewSpec ?: return

            animator.updateOverlay(
                coach,
                coachScene,
                targetViewSpec,
                overlayView.overlayPaint,
                animation.animatedValue as Float
            )
            for (coachSceneLayout in currentCoachSceneLayouts) {
                val coachView = coachSceneLayout.coachView
                if (coachView != null) {
                    animator.updateView(
                        coach,
                        coachScene,
                        targetViewSpec,
                        coachView,
                        animation.animatedValue as Float
                    )
                }
            }
            overlayView.invalidate()
            invalidate()
        }
    }

    private val overlayView = CoachOverlayView(context, coach)
    private var currentCoachScene: CoachScene? = null
    private var currentCoachSceneLayouts = mutableListOf<ICoachSceneLayout>()
    private var currentTargetViewSpec: ViewSpec? = null
    private var animator: ValueAnimator? = null

    init {
        addView(overlayView)
    }

    fun replaceScene(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        if (currentCoachScene != null) {
            for (layout in currentCoachSceneLayouts) {
                if (layout is View) {
                    removeView(layout)
                }
            }
            currentCoachScene = null
            currentCoachSceneLayouts.clear()
        }
        animator?.cancel()

        for (coachSceneUi in coachScene.coachSceneUis) {
            val coachSceneLayout = coachSceneUi.layoutProvider.provide(context, targetViewSpec)
            if (coachSceneLayout !is View) {
                throw IllegalStateException("CoachSceneLayout must implement View")
            }
            addView(coachSceneLayout)

            val coachView = coachSceneUi.coachViewProvider.provide(context, coachSceneLayout)
            coachSceneLayout.addCoachView(coachView)
            currentCoachSceneLayouts.add(coachSceneLayout)
        }

        overlayView.setScene(coachScene, targetViewSpec)

        currentCoachScene = coachScene
        currentTargetViewSpec = targetViewSpec

        startAnimationIfNeeded(coachScene, targetViewSpec)
    }

    private fun startAnimationIfNeeded(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        if (coachScene.coachSceneAnimation?.startAnimator != null) {
            startAnimationOfStart(coachScene, targetViewSpec)
        } else if (coachScene.coachSceneAnimation?.centerAnimator != null) {
            startAnimationOfCenter(coachScene, targetViewSpec)
        }
    }

    private fun startAnimationOfStart(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        val startAnimator = coachScene.coachSceneAnimation?.startAnimator ?: return
        coachScene.coachSceneAnimation.listener?.onCurrentAnimator(
            startAnimator,
            ICoachSceneAnimatedListener.Type.Start
        )

        animator = ValueAnimator.ofFloat(*(startAnimator.animation.values)).apply {
            duration = startAnimator.duration
            interpolator = startAnimator.interpolator
            addUpdateListener(AnimatorUpdateListener(startAnimator))
            addListener(object : BaseAnimatorListener(coach, coachScene) {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    startAnimationOfCenter(coachScene, targetViewSpec)
                }
            })
        }
        animator?.start()
    }

    private fun startAnimationOfCenter(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        val centerAnimator = coachScene.coachSceneAnimation?.centerAnimator ?: return
        coachScene.coachSceneAnimation.listener?.onCurrentAnimator(
            centerAnimator,
            ICoachSceneAnimatedListener.Type.Center
        )

        val connectionStrategy = coachScene.coachSceneAnimation.connectionStrategy
        val currentAnimatedValue = animator?.animatedValue as? Float
        val animation = if (connectionStrategy != null && currentAnimatedValue != null) {
            connectionStrategy.nextAnimation(currentAnimatedValue, centerAnimator.animation)
        } else {
            centerAnimator.animation
        }

        animator = ValueAnimator.ofFloat(*(animation.values)).apply {
            duration = centerAnimator.duration
            interpolator = centerAnimator.interpolator
            repeatCount = centerAnimator.repeatCount
            centerAnimator.repeatMode?.also {
                repeatMode = it
            }
            addUpdateListener(AnimatorUpdateListener(centerAnimator))
            addListener(object : BaseAnimatorListener(coach, coachScene) {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    startAnimationOfEnd(coachScene, targetViewSpec)
                }
            })
        }
        animator?.start()
    }

    private fun startAnimationOfEnd(
        coachScene: CoachScene,
        @Suppress("UNUSED_PARAMETER")
        targetViewSpec: ViewSpec
    ) {
        val endAnimator = coachScene.coachSceneAnimation?.endAnimator ?: return
        coachScene.coachSceneAnimation.listener?.onCurrentAnimator(
            endAnimator,
            ICoachSceneAnimatedListener.Type.End
        )

        val connectionStrategy = coachScene.coachSceneAnimation.connectionStrategy
        val currentAnimatedValue = animator?.animatedValue as? Float
        val animation = if (connectionStrategy != null && currentAnimatedValue != null) {
            connectionStrategy.nextAnimation(currentAnimatedValue, endAnimator.animation)
        } else {
            endAnimator.animation
        }

        animator = ValueAnimator.ofFloat(*(animation.values)).apply {
            duration = endAnimator.duration
            interpolator = endAnimator.interpolator
            addUpdateListener(AnimatorUpdateListener(endAnimator))
            addListener(object : BaseAnimatorListener(coach, coachScene) {})
        }
        animator?.start()
    }

    fun finishAnimation() {
        animator?.cancel()
        overlayView.finishAnimation()
    }
}