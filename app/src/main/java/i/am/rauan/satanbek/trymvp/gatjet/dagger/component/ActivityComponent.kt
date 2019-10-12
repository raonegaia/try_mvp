package i.am.rauan.satanbek.trymvp.gatjet.dagger.component

import dagger.Component
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.ActivityModule
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.DataModule
import i.am.rauan.satanbek.trymvp.gatjet.ui.main.MainActivity

@Component(modules = [ActivityModule::class, DataModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}