package i.am.rauan.satanbek.trymvp.gatjet.ui.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun onDrawerOptionAboutClick() {
        view.showAboutFragment()
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showListFragment()
    }

}