package i.am.rauan.satanbek.trymvp.gatjet.game.managers

import android.graphics.Canvas
import i.am.rauan.satanbek.trymvp.gatjet.game.objects.Coin
import i.am.rauan.satanbek.trymvp.gatjet.game.objects.ReacPlayer
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Functions
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Constants

class CoinManager() {
    private var socre = 0
    private var coins: ArrayList<Coin> = arrayListOf()

    init {
        populateCoins()
    }

    private fun populateCoins() {
        for (i in 1..6) {
            coins.add(Coin(Functions.hexToRgb(Constants.coinBgColor), 20 * i, 20 * i, 50))
        }
    }

    fun playerCollide(player: ReacPlayer): Boolean {
        for (c in coins) {
            if (c.playerCollide(player)) {
                coins.remove(c)
                addNewCoin()

                return true
            }
        }

        return false
    }

    private fun addNewCoin() {
        coins.add(Coin(Functions.hexToRgb(Constants.coinBgColor), (200 * Math.random()).toInt(), (200 * Math.random()).toInt(), 50))
    }

    fun update() {

    }

    fun draw(canvas: Canvas) {
        for (c in coins) {
            c.draw(canvas)
        }


    }
}