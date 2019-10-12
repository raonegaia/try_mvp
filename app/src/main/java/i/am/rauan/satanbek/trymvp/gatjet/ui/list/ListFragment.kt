package i.am.rauan.satanbek.trymvp.gatjet.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.gatjet.dagger.component.DaggerFragmentComponent
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.FragmentModule
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.DetailsViewModel
import i.am.rauan.satanbek.trymvp.gatjet.requests.pojo.Post
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject


class ListFragment : Fragment(), ListContract.View, ListAdapter.onItemClickListener {
    override fun sayHi() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun instance() = ListFragment()

        val TAG: String = "ListFragment"
    }

    @Inject lateinit var presenter: ListContract.Presenter
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Post>) {
        Log.d("main", "loadDataSuccess: $list")

        var adapter: ListAdapter = ListAdapter(context!!, list.toMutableList(), this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

    }

    override fun loadDataAllSuccess(model: DetailsViewModel) {
        print(model.toJson())
    }

    override fun itemRemoveClick(post: Post) {
    }

    override fun itemDetail(postId: String) {
    }

}
