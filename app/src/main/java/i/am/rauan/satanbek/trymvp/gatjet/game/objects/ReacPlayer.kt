package i.am.rauan.satanbek.trymvp.gatjet.game.objects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import i.am.rauan.satanbek.trymvp.gatjet.game.interfaces.GameObject

class ReacPlayer(var rectangle: Rect, private var color: Int): GameObject {

    init {
        this.rectangle = rectangle
        this.color = color
    }
    override fun draw(canvas: Canvas) {
        val paint: Paint = Paint()

        paint.color = color
        canvas.drawRect(rectangle, paint)
    }


    override fun update() {
    }

    fun update(point: Point) {
        rectangle.set(point.x  - rectangle.width() / 2,
            point.y - rectangle.width() / 2,
            point.x + rectangle.width() / 2,
            point.y + rectangle.width() / 2)
    }
}