package i.am.rauan.satanbek.trymvp.gatjet.ui.home

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.gatjet.util.Constants
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun onStart() {

        // Set username
        setUsername(Constants.DEFAULT_USERNAME)

        super.onStart()
    }

    private fun setUsername(username: String) {
        tvUsername.text = username
    }
}
