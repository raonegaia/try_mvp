package i.am.rauan.satanbek.trymvp.gatjet.game.managers

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import i.am.rauan.satanbek.trymvp.gatjet.game.objects.Obstacle
import i.am.rauan.satanbek.trymvp.gatjet.game.objects.ReacPlayer
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Constants
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Functions

class ObstacleManager(
    private val playerGap: Int,
    private val obstacleGap: Int,
    private val obstacleHeight: Int,
    private val color: Int) {

    private val obstacles: ArrayList<Obstacle>

    private var startTime: Long = 0
    private var initTime: Long = 0

    private var score = 0

    init {
        initTime = System.currentTimeMillis()
        startTime = initTime

        obstacles = ArrayList()
        populateObstacles()
    }

    fun playerCollide(player: ReacPlayer): Boolean {
        for (o in obstacles) {
            if (o.playerCollide(player)) return true
        }

        return false
    }

    private fun populateObstacles() {
        var curry = -5 * Constants.SCREEN_HEIGHt / 4
        Log.i("main", "added: $curry $obstacles")

        while (curry < 0) {
            val xStart = (Math.random() * (Constants.SCREEN_WITHD - playerGap)).toInt()
            obstacles.add(Obstacle(obstacleHeight, color, xStart, curry, playerGap))

            curry += obstacleHeight + obstacleGap
            Log.i("main", "added: $curry $obstacles")
        }
    }

    fun update() {
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME

        val elapsedTime = (System.currentTimeMillis() - startTime).toInt()
        startTime = System.currentTimeMillis()

        val speed = Math.sqrt(1 + (startTime - initTime) / 2000.0).toFloat() * Constants.SCREEN_HEIGHt / 10000.0f

        for (o in obstacles) {
            o.incrementY(speed * elapsedTime)
        }

        if (obstacles.isNotEmpty() && obstacles[obstacles.size - 1].rectangle.top >= Constants.SCREEN_HEIGHt) {
            var xStart = (Math.random() * (Constants.SCREEN_WITHD - playerGap)).toInt()

            obstacles.add(
                0,
                Obstacle(
                    obstacleHeight,
                    color,
                    xStart,
                    obstacles[0].rectangle.top - obstacleHeight - obstacleGap,
                    playerGap)
            )

            obstacles.removeAt(obstacles.size - 1)
            score++
        }
    }

    fun draw(canvas: Canvas) {
        for (o in obstacles)
            o.draw(canvas)

        val paint = Paint()
        paint.textSize = 100f
        paint.color = Functions.hexToRgb(Constants.obstacleColor)
        canvas.drawText("$score", 50f, 50 + paint.descent() - paint.ascent(), paint)
    }
}