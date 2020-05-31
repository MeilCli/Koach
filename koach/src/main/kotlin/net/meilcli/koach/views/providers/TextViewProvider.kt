package net.meilcli.koach.views.providers

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatTextView
import net.meilcli.koach.IViewProvider

@SuppressLint("RtlHardcoded")
class TextViewProvider(
    private val text: CharSequence,
    @ColorInt
    private val textColor: Int? = null,
    @Px
    private val textSize: Int? = null,
    @StyleRes
    private val textAppearance: Int? = null,
    private val gravity: Int = Gravity.LEFT or Gravity.TOP
) : IViewProvider {

    override fun provide(context: Context): View {
        return AppCompatTextView(context).apply {
            text = this@TextViewProvider.text
            gravity = this@TextViewProvider.gravity
            if (textAppearance != null) {
                setTextAppearance(context, textAppearance)
            }
            if (textColor != null) {
                setTextColor(textColor)
            }
            if (this@TextViewProvider.textSize != null) {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, this@TextViewProvider.textSize.toFloat())
            }
        }
    }
}