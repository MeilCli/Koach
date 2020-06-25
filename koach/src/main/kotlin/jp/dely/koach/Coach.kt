package jp.dely.koach

import android.app.Activity
import android.os.Build
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.Window

/**
 * [viewGroup] should be layout that stacks like layers, such as FrameLayout
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class Coach(
    private val viewGroup: ViewGroup,
    private val scenes: List<CoachScene>,
    internal val overlay: CoachOverlay
) {

    /**
     * [viewGroup] should be layout that stacks like layers, such as FrameLayout
     */
    class Creator(
        private val viewGroup: ViewGroup,
        private val overlay: CoachOverlay
    ) {

        private val scenes = mutableListOf<CoachScene>()

        val Int.dp: Int
            get() = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                this.toFloat(),
                viewGroup.resources.displayMetrics
            ).toInt()

        val Int.sp: Int
            get() = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                this.toFloat(),
                viewGroup.resources.displayMetrics
            ).toInt()

        fun addScene(coachScene: CoachScene) {
            scenes += coachScene
        }

        fun create(): Coach {
            return Coach(viewGroup, scenes, overlay)
        }
    }

    companion object {

        /**
         * [viewGroup] should be layout that stacks like layers, such as FrameLayout
         */
        fun create(
            viewGroup: ViewGroup,
            overlay: CoachOverlay,
            action: Creator.() -> Unit
        ): Coach {
            val creator = Creator(viewGroup, overlay)
            action(creator)
            return creator.create()
        }

        fun create(
            window: Window,
            overlay: CoachOverlay,
            action: Creator.() -> Unit
        ): Coach {
            return create(
                window.decorView as ViewGroup,
                overlay,
                action
            )
        }

        fun create(
            activity: Activity,
            overlay: CoachOverlay,
            action: Creator.() -> Unit
        ): Coach {
            return create(
                activity.window,
                overlay,
                action
            )
        }
    }

    private val coachLayout = CoachLayout(viewGroup.context, this)
    private var isShown = false
    private var currentSceneIndex = 0

    constructor(
        window: Window,
        scenes: List<CoachScene>,
        overlay: CoachOverlay
    ) : this(window.decorView as ViewGroup, scenes, overlay)

    constructor(
        activity: Activity,
        scenes: List<CoachScene>,
        overlay: CoachOverlay
    ) : this(activity.window, scenes, overlay)

    fun isShowing(): Boolean {
        return isShown
    }

    fun show() {
        if (isShown || scenes.isEmpty()) {
            return
        }
        isShown = true

        viewGroup.addView(coachLayout)

        var showScene = false
        for (i in scenes.indices) {
            if (showScene(scenes[i])) {
                currentSceneIndex = i
                showScene = true
                break
            }
        }

        if (showScene.not()) {
            hide()
        }
    }

    private fun showScene(coachScene: CoachScene): Boolean {
        val targetViewSpec = coachScene.targetViewFinder.find(viewGroup) ?: return false
        coachLayout.replaceScene(coachScene, targetViewSpec)
        return true
    }

    fun scheduleShow() {
        val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                show()
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    @Suppress("DEPRECATION")
                    viewGroup.viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    viewGroup.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        }
        viewGroup.viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    fun showNextSceneOrHide() {
        if (isShown.not()) {
            show()
            return
        }
        if (scenes.lastIndex == currentSceneIndex) {
            hide()
            return
        }

        currentSceneIndex += 1

        var showScene = false
        for (i in currentSceneIndex until scenes.size) {
            if (showScene(scenes[i])) {
                currentSceneIndex = i
                showScene = true
                break
            }
        }

        if (showScene.not()) {
            hide()
        }
    }

    fun hide() {
        coachLayout.finishAnimation()
        viewGroup.removeView(coachLayout)
        isShown = false
    }
}