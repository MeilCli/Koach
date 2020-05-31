package net.meilcli.koach

import android.graphics.Color
import androidx.annotation.ColorInt
import net.meilcli.koach.overlays.animates.EmptyOverlayAnimatedListener
import net.meilcli.koach.overlays.clicks.NextSceneOverlayClickListener

class CoachOverlay(
    internal val clickListener: IOverlayClickListener = NextSceneOverlayClickListener,
    internal val animatedListener: IOverlayAnimatedListener = EmptyOverlayAnimatedListener,
    @ColorInt
    internal val color: Int = defaultOverlayColor,
    internal val canClickTargetView: Boolean = false
) {

    companion object {
        @ColorInt
        private val defaultOverlayColor = Color.parseColor("#D0000000")
    }
}