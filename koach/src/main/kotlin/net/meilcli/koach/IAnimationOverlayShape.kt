package net.meilcli.koach

import android.graphics.Canvas
import android.graphics.Paint

interface IAnimationOverlayShape : IOverlayShape, IRepeatableAnimationProperty {

    /**
     * [value] will be range of [0, 1]
     */
    fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float)
}