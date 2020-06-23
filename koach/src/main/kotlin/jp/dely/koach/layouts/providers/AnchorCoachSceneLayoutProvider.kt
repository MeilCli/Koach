package jp.dely.koach.layouts.providers

import android.content.Context
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.ICoachSceneLayoutProvider
import jp.dely.koach.ViewSpec
import jp.dely.koach.layouts.AnchorCoachSceneLayout

class AnchorCoachSceneLayoutProvider(
    private val anchorGravity: Int,
    private val gravity: Int,
    @Px
    private val marginVertical: Int = 0,
    @Px
    private val marginHorizontal: Int = 0
) : ICoachSceneLayoutProvider {

    override fun provide(context: Context, targetViewSpec: ViewSpec): ICoachSceneLayout {
        return AnchorCoachSceneLayout(
            context,
            targetViewSpec,
            anchorGravity,
            gravity,
            marginVertical,
            marginHorizontal
        )
    }
}