package jp.dely.koach

class Animation(override val values: FloatArray) : IAnimation {

    companion object {

        val expand = Animation(floatArrayOf(0f, 1f))
        val contract = Animation(floatArrayOf(1f, 0f))
        val fadeIn = Animation(floatArrayOf(0f, 1f))
        val fadeOut = Animation(floatArrayOf(1f, 0f))
        val none = Animation(floatArrayOf(1f, 1f))
    }
}