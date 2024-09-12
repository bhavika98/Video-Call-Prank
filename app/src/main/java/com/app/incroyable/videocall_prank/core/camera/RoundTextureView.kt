package com.app.incroyable.videocall_prank.core.camera

import android.content.Context
import android.graphics.Outline
import android.graphics.Rect
import android.util.AttributeSet
import android.view.TextureView
import android.view.View
import android.view.ViewOutlineProvider

class RoundTextureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TextureView(context, attrs) {

    var radius = 0

    init {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                val rect = Rect(0, 0, view?.measuredWidth ?: 0, view?.measuredHeight ?: 0)
                outline?.setRoundRect(rect, radius.toFloat())
            }
        }
        clipToOutline = true
    }

    fun turnRound() {
        invalidateOutline()
    }
}

