package i.am.rauan.satanbek.trymvp.gatjet.ui.list

import i.am.rauan.satanbek.trymvp.gatjet.requests.api.CoreApi
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Album
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.DetailsViewModel
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Post
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class ListPresenter: ListContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private val coreApi: CoreApi = CoreApi.create()
    private lateinit var view: ListContract.View

    override fun loadData() {
        var subscribe = coreApi.getPostList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ list: List<Post>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!.take(15))
            }, {error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun loadDataAll() {
        var subscribe = Observable.zip(coreApi.getPostList(), coreApi.getUserList(), coreApi.getAlbumList(),
            Function3<List<Post>, List<User>, List<Album>, DetailsViewModel> {
                posts, users, albums ->
                createDetailedView(posts, users, albums)
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ model: DetailsViewModel? ->
                view.showProgress(false)
                view.loadDataAllSuccess(model!!)
            },{ error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    private fun createDetailedView(posts: List<Post>, users: List<User>, albums: List<Album>):DetailsViewModel {
        var postList = posts.take(30)
        var userList = users.take(30)
        var albumList = albums.take(30)

        return DetailsViewModel(postList, userList, albumList)
    }
    override fun deleteItem(item: Post) {
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

}