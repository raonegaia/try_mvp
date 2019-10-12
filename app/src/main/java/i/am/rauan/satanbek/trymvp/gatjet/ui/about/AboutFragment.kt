package i.am.rauan.satanbek.trymvp.gatjet.ui.about

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.gatjet.dagger.component.DaggerFragmentComponent
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.FragmentModule
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject

class AboutFragment : Fragment(), AboutContract.View {
    override fun sayHi() {
        Log.d("main >>>>", "AboutFragment: [sayHi]: System.out.println('Hello, world!')")
    }

    @Inject lateinit var presenter: AboutContract.Presenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_about, container, false)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()

        initView()
    }
    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun loadMessageSuccess(message: String) {
        aboutText.text = "Success: $message"
        aboutText.visibility = View.VISIBLE
    }


    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }
    private fun initView() {
        presenter.loadMessage()
    }

    companion object {
        @JvmStatic
        fun instance() = AboutFragment()

        val TAG: String = "AboutFragment"
    }
}
