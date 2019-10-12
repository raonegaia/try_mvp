package i.am.rauan.satanbek.trymvp.gatjet.ui.main

import i.am.rauan.satanbek.trymvp.gatjet.ui.base.BaseActivityContract
import i.am.rauan.satanbek.trymvp.gatjet.ui.base.BaseContract

class MainContract {
    interface View: BaseActivityContract {
        fun showAboutFragment()
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onDrawerOptionAboutClick()
    }
}