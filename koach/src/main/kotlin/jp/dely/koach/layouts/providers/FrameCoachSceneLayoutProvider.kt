package jp.dely.koach.layouts.providers

import android.content.Context
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.ICoachSceneLayoutProvider
import jp.dely.koach.ViewSpec
import jp.dely.koach.layouts.FrameCoachSceneLayout

class FrameCoachSceneLayoutProvider(
    private val gravity: Int,
    @Px private val marginTop: Int = 0,
    @Px private val marginBottom: Int = 0,
    @Px private val marginLeft: Int = 0,
    @Px private val marginRight: Int = 0
) : ICoachSceneLayoutProvider {

    constructor(
        gravity: Int,
        margin: Int
    ) : this(gravity, margin, margin, margin, margin)

    override fun provide(context: Context, targetViewSpec: ViewSpec): ICoachSceneLayout {
        return FrameCoachSceneLayout(
            context,
            gravity,
            marginTop,
            marginBottom,
            marginLeft,
            marginRight
        )
    }
}