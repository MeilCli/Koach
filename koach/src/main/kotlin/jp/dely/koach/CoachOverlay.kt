package jp.dely.koach

import android.graphics.Color
import androidx.annotation.ColorInt
import jp.dely.koach.overlays.animates.EmptyOverlayAnimatedListener
import jp.dely.koach.overlays.clicks.NextSceneOverlayClickListener

/**
 * [canClickSurfaceView]: if value is true, overlay view is not clickable and do not set clickListener
 */
class CoachOverlay(
    internal val clickListener: IOverlayClickListener = NextSceneOverlayClickListener,
    internal val animatedListener: IOverlayAnimatedListener = EmptyOverlayAnimatedListener,
    @ColorInt
    internal val color: Int = defaultOverlayColor,
    internal val canClickTargetView: Boolean = false,
    internal val canClickSurfaceView: Boolean = false
) {

    companion object {
        @ColorInt
        private val defaultOverlayColor = Color.parseColor("#D0000000")
    }
}