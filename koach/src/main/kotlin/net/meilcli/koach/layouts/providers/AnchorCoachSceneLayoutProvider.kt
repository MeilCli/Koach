package net.meilcli.koach.layouts.providers

import android.content.Context
import androidx.annotation.Px
import net.meilcli.koach.ICoachSceneLayout
import net.meilcli.koach.ICoachSceneLayoutProvider
import net.meilcli.koach.TargetViewSpec
import net.meilcli.koach.layouts.AnchorCoachSceneLayout

class AnchorCoachSceneLayoutProvider(
    private val anchorGravity: Int,
    private val gravity: Int,
    @Px
    private val marginVertical: Int = 0,
    @Px
    private val marginHorizontal: Int = 0
) : ICoachSceneLayoutProvider {

    override fun provide(context: Context, targetViewSpec: TargetViewSpec): ICoachSceneLayout {
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