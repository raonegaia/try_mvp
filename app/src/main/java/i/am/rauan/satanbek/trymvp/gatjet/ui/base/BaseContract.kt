package i.am.rauan.satanbek.trymvp.gatjet.ui.base

class BaseContract {
    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun sayHi()
    }
}