package i.am.rauan.satanbek.trymvp.gatjet.game.threads

import android.graphics.Canvas
import android.view.SurfaceHolder
import i.am.rauan.satanbek.trymvp.gatjet.game.views.GamaPanel
import java.lang.Exception

class MainThread(private val surfaceHolder: SurfaceHolder, private val gamePanel: GamaPanel): Thread() {
    private var averageFPS: Double = 0.toDouble()
    private var running: Boolean = false

    fun setRunning(running: Boolean) {
        this.running = running

        if (!running) {
            this.surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    override fun run() {
        var startTime: Long
        var timeMillies: Long = (1000 / MAX_FPS).toLong()
        var waitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        val targetTime: Long = (1000 / MAX_FPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                if (canvas == null)
                    canvas = this.surfaceHolder.lockCanvas()

                synchronized(surfaceHolder) {
                    this.gamePanel.update()
                    this.gamePanel.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillies = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillies

            try {
                if (waitTime > 0)
                    sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }


            totalTime += System.nanoTime() - startTime
            frameCount ++

            if (frameCount == MAX_FPS) {
                averageFPS = (1000 / (totalTime / frameCount / 1000000)).toDouble()
                frameCount = 0
                totalTime = 0
                println(averageFPS)
            }
        }
    }

    companion object {
        val MAX_FPS = 30
        var canvas: Canvas? = null
    }
}