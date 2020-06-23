package jp.dely.koach.layouts.providers

import android.content.Context
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.ICoachSceneLayoutProvider
import jp.dely.koach.ViewSpec
import jp.dely.koach.layouts.RelativeCoachSceneLayout
import jp.dely.koach.layouts.RelativeDirection

class RelativeCoachSceneLayoutProvider(
    private val direction: RelativeDirection,
    @Px
    private val marginTop: Int = 0,
    @Px
    private val marginBottom: Int = 0,
    @Px
    private val marginLeft: Int = 0,
    @Px
    private val marginRight: Int = 0
) : ICoachSceneLayoutProvider {

    constructor(
        direction: RelativeDirection,
        @Px
        marginVertical: Int = 0,
        @Px
        marginHorizontal: Int = 0
    ) : this(direction, marginVertical, marginVertical, marginHorizontal, marginHorizontal)

    constructor(
        direction: RelativeDirection,
        @Px
        margin: Int = 0
    ) : this(direction, margin, margin)

    override fun provide(context: Context, targetViewSpec: ViewSpec): ICoachSceneLayout {
        return RelativeCoachSceneLayout(
            context,
            targetViewSpec,
            direction,
            marginTop,
            marginBottom,
            marginLeft,
            marginRight
        )
    }
}