package i.am.rauan.satanbek.trymvp.gatjet.game.views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import i.am.rauan.satanbek.trymvp.gatjet.game.GameActivity
import i.am.rauan.satanbek.trymvp.gatjet.game.managers.CoinManager
import i.am.rauan.satanbek.trymvp.gatjet.game.managers.ObstacleManager
import i.am.rauan.satanbek.trymvp.gatjet.game.objects.ReacPlayer
import i.am.rauan.satanbek.trymvp.gatjet.game.threads.MainThread
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Constants
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Functions
import i.am.rauan.satanbek.trymvp.gatjet.game.view.GameView

class GamaPanel(context: Context, attrs: AttributeSet?): SurfaceView(context), SurfaceHolder.Callback {

    private var thread: MainThread
    private val r = Rect()

    private val player: ReacPlayer
    private var playerPoint: Point? = null
    private var obstacleManager: ObstacleManager? = null
    private var coinManager: CoinManager? = null

    private var movingPlayer: Boolean = false

    private var gameOver: Boolean = false
    private var gameOverSended = false
    private var gameOverTime: Long = 0

    private var coinCount = 0

    private var canvas: Canvas? = null
    private var mGameView: GameView? = null

    init {
        holder.addCallback(this)
        thread = MainThread(holder, this)
        isFocusable = false
        player = ReacPlayer(Rect(100, 100, 170, 170), Functions.hexToRgb(Constants.playerBgColor))
        playerPoint = Point(Constants.SCREEN_WITHD / 2, 3 * Constants.SCREEN_HEIGHt / 4)
        player.update(playerPoint!!)

        Log.i("main", "GamePanel init")
        obstacleManager = ObstacleManager(200, 350, 75, Functions.hexToRgb(Constants.obstacleColor))
        coinManager = CoinManager()

        if (context is GameView) mGameView = context
    }

    private fun reset() {
        playerPoint = Point(Constants.SCREEN_WITHD / 2, 3 * Constants.SCREEN_HEIGHt / 4)
        player.update(playerPoint!!)
        obstacleManager = ObstacleManager(200, 350, 75, Functions.hexToRgb(Constants.obstacleColor))
        coinManager = CoinManager()
        movingPlayer = false
        gameOverSended = false
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        var retry = true
        while(retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        startThread()
    }

    private fun startThread() {
        Constants.INIT_TIME = System.currentTimeMillis()
        thread.setRunning(true)
        thread.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!gameOver && player.rectangle.contains(event.x.toInt(), event.y.toInt()))
                    movePlayerPosition(event)
                    movingPlayer = true
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
                    reset()
                    gameOver = false
                }
            }

            MotionEvent.ACTION_MOVE -> {
                movePlayerPosition(event)
            }

            MotionEvent.ACTION_UP -> movingPlayer = false
        }
//        return super.onTouchEvent(event)
        return true
    }

    // r1: Code

    private fun movePlayerPosition(event: MotionEvent?) {
        if (!gameOver && movingPlayer)
            playerPoint!!.set(event!!.x.toInt(), event.y.toInt())
    }

    //r1: Code end

    fun update() {
        if (!gameOver) {
            player.update(playerPoint!!)
            obstacleManager!!.update()
            coinManager!!.update()

            if (obstacleManager!!.playerCollide(player)) {
                gameOver = true
                gameOverTime = System.currentTimeMillis()
            }

            if (coinManager!!.playerCollide(player)) {
                Log.i("main", "Coin earned")
                coinCount ++

                // TODO: desplay text like that -> { "Coin earned: 222 $" }

//                val paint = Paint()
//                paint.textSize = 44f
//                paint.color = Functions.hexToRgb(Constants.coinBgColor)
//
//                paint.textAlign = Paint.Align.RIGHT
//                canvas!!.getClipBounds(r)
//
//                val cHeight = r.height()
//                val cWidth = r.width()
//                paint.getTextBounds("Coin earned: $coinCount $" , 0, "Coin earned: $coinCount $".length, r)
//                val x = cWidth / 2f - r.width() / 2f - r.left.toFloat()
//                val y = cHeight / 2f - r.height() / 2f - r.bottom
//
//                canvas!!.drawText("Coin earned: $coinCount $", 679.5f, 360.0f, paint)

                Log.i("balance", "Balance: $coinCount $")
            }
        }

    }

    override fun draw(thatCanvas: Canvas?) {
        this.canvas = thatCanvas

        super.draw(canvas)
        canvas!!.drawColor(Functions.hexToRgb(Constants.backgroundColor))
        player.draw(canvas!!)
        obstacleManager!!.draw(canvas!!)
        coinManager!!.draw(canvas!!)

        if (gameOver) {
            if (!gameOverSended) {

                gameOverSended = true
                mGameView?.gameOver()

                val paint = Paint()
                paint.textSize = 100f
                paint.color = Color.BLUE
                drawCenterText(canvas!!, paint, "GAME OVER")
                Log.i(GameActivity.TAG, "gameOverSended=$gameOverSended")
                try {
                    Log.i(GameActivity.TAG, "Stop thread")
                    thread.setRunning(false)
                    thread.join()


                    thread.stop()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.i(GameActivity.TAG, "Stop thread error: ${e.message}")
                }
            }

        }

        if (coinCount != 0) {
            Log.i("balance", "I can dart balance text!!!")
            val paint = Paint()
            paint.textSize = 40f
            paint.color = Functions.hexToRgb(Constants.coinBgColor)
            drawCoinEarnedText(canvas!!, paint, "$coinCount $")
//            val paint = Paint()
//            paint.textSize = 100f
//            paint.color = Functions.hexToRgb(Constants.coinBgColor)
//            thatCanvas!!.drawText("Coin earned: $coinCount $", 5679.5f, 360.0f, paint)
        }
    }


    private fun drawCenterText(canvas: Canvas, paint: Paint, text: String) {
        paint.textAlign = Paint.Align.LEFT
        canvas.getClipBounds(r)

        val cHeight = r.height()
        val cWidth = r.width()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            paint.color = Color.argb(0.4f, 255f, 0f, 0f)
//        }
//
//        canvas.drawRect(Rect(0, 0, cWidth, cHeight), paint)
        paint.getTextBounds(text, 0, text.length, r)
//        val x = cWidth / 2f - r.width() / 2f - r.left.toFloat()
//        val y = cHeight / 2f - r.height() / 2f - r.bottom
        val x = (cWidth - r.width()) / 2f
        val y = (cHeight - r.height()) / 2f + r.height() / 2f

        paint.color = Functions.hexToRgb(Constants.gameOverColor)
        canvas.drawText(text, x, y, paint)
    }

    private fun drawCoinEarnedText(canvas: Canvas, paint: Paint, text: String) {
        paint.textAlign = Paint.Align.LEFT
        canvas.getClipBounds(r)

        val cHeight = r.height()
        val cWidth = r.width()

        paint.getTextBounds(text, 0, text.length, r)
        val x = cWidth - r.width() - r.height()
        val y = r.height() * 2f

        Log.i("coin", "drawText: cHeight=$cHeight, cWidth=$cWidth, width=${r.width()}, height=${r.height()}, x=$x, y=$y")

        canvas.drawText(text, x.toFloat(), y, paint)
    }
}