package i.am.rauan.satanbek.trymvp.gatjet.ui.about

import i.am.rauan.satanbek.trymvp.gatjet.ui.base.BaseContract

class AboutContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadMessageSuccess(message: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadMessage()
    }
}