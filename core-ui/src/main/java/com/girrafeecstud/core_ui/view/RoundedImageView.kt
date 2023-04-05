/* Created by Girrafeec */

package com.girrafeecstud.core_ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

class RoundedImageView(
    context: Context, attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

    private val path = Path()
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            it.save()
            path.reset()
            rectF.set(0f, 0f, width.toFloat(), height.toFloat())
            path.addRoundRect(rectF, 360f, 360f, Path.Direction.CW)
            it.clipPath(path)
            super.onDraw(canvas)
            it.restore()
        }
    }
}