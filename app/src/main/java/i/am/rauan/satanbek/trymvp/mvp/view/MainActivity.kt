package i.am.rauan.satanbek.trymvp.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import i.am.rauan.satanbek.trymvp.R
import i.am.rauan.satanbek.trymvp.mvp.contract.ContractInterface
import i.am.rauan.satanbek.trymvp.mvp.model.MainActivityModel
import i.am.rauan.satanbek.trymvp.mvp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContractInterface.View {

    private var presenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this, MainActivityModel())
    }

    override fun onResume() {
        super.onResume()

        presenter?.initView()
    }

    override fun initView() {
        tvCounter.text = presenter?.getCounter()
        increment.setOnClickListener { presenter?.incrementValue() }
    }

    override fun updateViewData() {
        tvCounter.text = presenter?.getCounter()
    }
}