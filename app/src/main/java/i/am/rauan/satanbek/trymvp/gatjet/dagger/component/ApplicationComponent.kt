package i.am.rauan.satanbek.trymvp.gatjet.dagger.component

import dagger.Component
import i.am.rauan.satanbek.trymvp.gatjet.BaseApp
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)
}