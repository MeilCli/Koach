package net.meilcli.koach

import android.graphics.Canvas
import android.graphics.Paint

interface IOverlayShape {

    fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec)
}