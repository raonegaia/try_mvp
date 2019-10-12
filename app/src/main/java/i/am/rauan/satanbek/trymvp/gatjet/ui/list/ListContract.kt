package i.am.rauan.satanbek.trymvp.gatjet.ui.list

import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.DetailsViewModel
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Post
import i.am.rauan.satanbek.trymvp.gatjet.ui.base.BaseContract

class ListContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Post>)
        fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun loadDataAll()
        fun deleteItem(item: Post)
    }
}