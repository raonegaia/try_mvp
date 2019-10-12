package i.am.rauan.satanbek.trymvp.gatjet.game.interfaces

import android.graphics.Canvas

interface GameObject {
    fun draw(canvas: Canvas)
    fun update()
}