package i.am.rauan.satanbek.trymvp.gatjet.ui.home.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Constants
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Functions

class MainView(context: Context, attrs: AttributeSet?): View(context) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Functions.hexToRgb(Constants.backgroundColor)

        canvas?.drawColor(Functions.hexToRgb(Constants.coinBgColor))

        canvas?.drawRect(Rect(50, 50, 100, 100), paint)
    }

}