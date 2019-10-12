package i.am.rauan.satanbek.trymvp.mvp.presenter

import i.am.rauan.satanbek.trymvp.mvp.contract.ContractInterface.*

class MainActivityPresenter(_view: View, _model: Model): Presenter {

    private var view: View = _view
    private var model: Model = _model

    fun initView() {
        view.initView()
    }

    override fun incrementValue() {
        model.incrementCounter()
        view.updateViewData()
    }

    override fun getCounter() = model.getCounter().toString()

}