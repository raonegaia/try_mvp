package i.am.rauan.satanbek.trymvp.gatjet.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.gatjet.game.utils.Constants
import i.am.rauan.satanbek.trymvp.gatjet.game.view.GameView
import i.am.rauan.satanbek.trymvp.gatjet.game.views.GamaPanel
import kotlinx.android.synthetic.main.activity_base_app.*

class GameActivity : AppCompatActivity(), GameView {
    private lateinit var mInterstitialAd: InterstitialAd
    private var mCountDownTimer: CountDownTimer? = null
    private var mGameIsInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize adMob.
        MobileAds.initialize(this, getString(R.string.admob_application_id))

        Log.i(TAG, "AD_ID: ${getString(R.string.ad_interstitials_one)}")

        // Create InterstitialAd.
        mInterstitialAd = InterstitialAd(this).apply {
            adUnitId = getString(R.string.ad_interstitials_one)
            adListener = (object: AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.i(TAG, "onAdLoaded()")
                }

                override fun onAdFailedToLoad(p0: Int) {
                    super.onAdFailedToLoad(p0)

                    Log.i(TAG, "onAdFailedToLoad() $p0")
                }

                override fun onAdClicked() {
                    super.onAdClicked()

                    Log.i(TAG, "onAdClicked()")
                }

                override fun onAdClosed() {
                    super.onAdClosed()

                    Log.i(TAG, "onAdClosed()")
                }
            })
        }

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val dm = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(dm)
        Constants.SCREEN_WITHD = dm.widthPixels
        Constants.SCREEN_HEIGHt = dm.heightPixels

        Log.i("main", "${Constants.SCREEN_WITHD} ${Constants.SCREEN_HEIGHt}")

        setLoadAd()
        setContentView(GamaPanel(this, null))
//        setContentView(R.layout.activity_base_app)

//        btnShowAd.setOnClickListener {
//
//            showInterstitial()
//
//        }
    }

    private fun setLoadAd() {
        if (!mInterstitialAd.isLoading && !mInterstitialAd.isLoaded) {
            val adRequest = AdRequest.Builder().build()
            AdRequest.ERROR_CODE_INTERNAL_ERROR

            mInterstitialAd.loadAd(adRequest)
        }

    }

    private fun showInterstitial() {
        Log.i(TAG, "showInterstitial(): isLoaded=${mInterstitialAd.isLoaded}")
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
        Log.i(TAG, "showInterstitial(): End")
    }

    override fun gameOver() {
        Log.i(TAG, "gameOver() isLoaded=${mInterstitialAd.isLoaded}")
        showInterstitial()
        Log.i(TAG, "gameOver() 2")
    }

    override fun retry() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val TAG: String = "logcat"
    }
}
