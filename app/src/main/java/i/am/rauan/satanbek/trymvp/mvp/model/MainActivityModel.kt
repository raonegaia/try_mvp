package i.am.rauan.satanbek.trymvp.mvp.model

import i.am.rauan.satanbek.trymvp.mvp.contract.ContractInterface.Model

class MainActivityModel: Model {

    private var mCounter = 0

    override fun getCounter()= mCounter

    override fun incrementCounter() {
        mCounter++
    }
}