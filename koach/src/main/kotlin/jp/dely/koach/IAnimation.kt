package jp.dely.koach

interface IAnimation {

    companion object {

        val expand = object : IAnimation {
            override val values = floatArrayOf(0f, 1f)
        }

        val contract = object : IAnimation {
            override val values = floatArrayOf(1f, 0f)
        }

        val fadeIn = object : IAnimation {
            override val values = floatArrayOf(0f, 1f)
        }

        val fadeOut = object : IAnimation {
            override val values = floatArrayOf(1f, 0f)
        }

        val none = object : IAnimation {
            override val values = floatArrayOf(1f, 1f)
        }
    }

    /**
     * values will be range of [0f, 1f]
     */
    val values: FloatArray
}