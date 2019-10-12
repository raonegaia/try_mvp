package i.am.rauan.satanbek.trymvp.gatjet.game.objects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import i.am.rauan.satanbek.trymvp.gatjet.game.interfaces.GameObject


class Coin(var color: Int,
           var startX: Int,
           var startY: Int, val height: Int): GameObject {
    var rectangle: Rect

    init {
        this.rectangle = Rect(50, startX, startY, startX + height)
        this.color = color
    }

    override fun draw(canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = color

        canvas.drawRect(rectangle, paint)

    }

    override fun update() {
    }

    fun playerCollide(player: ReacPlayer): Boolean {
        return Rect.intersects(rectangle, player.rectangle)
    }

    fun update(p: Point) {
        rectangle.set(p.x - rectangle.width() / 2,
            p.y - rectangle.width() / 2,
            p.x + rectangle.width() / 2,
            p.y + rectangle.width() / 2)
    }

}