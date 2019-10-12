package i.am.rauan.satanbek.trymvp.gatjet.ui.about

import i.am.rauan.satanbek.trymvp.gatjet.requests.api.CoreApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AboutPresenter: AboutContract.Presenter {
    private var subscriptions = CompositeDisposable()
    private var coreApi: CoreApi = CoreApi.create()
    private lateinit var view: AboutContract.View

    override fun loadMessage() {
        var subscribe = Observable.just(true).delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.showProgress(false)
                view.loadMessageSuccess("SUCCESS")
            }

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: AboutContract.View) {
        this.view = view
    }

}