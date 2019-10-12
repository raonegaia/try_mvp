package i.am.rauan.satanbek.trymvp.gatjet.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.gatjet.dagger.component.DaggerActivityComponent
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.ActivityModule
import i.am.rauan.satanbek.trymvp.gatjet.ui.about.AboutFragment
import i.am.rauan.satanbek.trymvp.gatjet.ui.list.ListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showAlertPregressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideAlertPrpgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sayHi() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject lateinit var presenter: MainContract.Presenter

    override fun showAboutFragment() {
        if (supportFragmentManager.findFragmentByTag(AboutFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame, AboutFragment.instance(), AboutFragment.TAG)
                .commit()
        } else {
            // Maybe an animation like shake hello text
        }
    }

    override fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, ListFragment.instance(), ListFragment.TAG)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        injectDependency()

        presenter.attach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.nav_item_info -> {
                presenter.onDrawerOptionAboutClick()
                return true
            }
            else -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

}
