package i.am.rauan.satanbek.trymvp.gatjet.game.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context, attrs: AttributeSet?): View(context) {

    val paint: Paint = Paint()
    var circleX: Float = 100f
    var circleY: Float = 100f
    var circleDefaoutRadius = 40f

    var mCanvas: Canvas? = null

    init {
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.RED
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        mCanvas = canvas

        canvas?.drawColor(Color.GREEN)
        canvas?.drawCircle(circleX, circleY, circleDefaoutRadius, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        circleX = event?.x!!
        circleY = event.y

        invalidate()
        return true
    }
}