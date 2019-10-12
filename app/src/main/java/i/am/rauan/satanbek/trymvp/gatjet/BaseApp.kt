package i.am.rauan.satanbek.trymvp.gatjet

import android.app.Application
import i.am.rauan.satanbek.trymvp.BuildConfig
import i.am.rauan.satanbek.trymvp.gatjet.dagger.component.ApplicationComponent
import i.am.rauan.satanbek.trymvp.gatjet.dagger.component.DaggerApplicationComponent
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.ApplicationModule

class BaseApp : Application() {
    companion object {
        lateinit var instance: BaseApp private set
    }

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {

        }
    }

    fun setup() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }
}
