package i.am.rauan.satanbek.trymvp.gatjet.dagger.component

import dagger.Component
import i.am.rauan.satanbek.trymvp.gatjet.dagger.module.FragmentModule
import i.am.rauan.satanbek.trymvp.gatjet.ui.about.AboutFragment
import i.am.rauan.satanbek.trymvp.gatjet.ui.list.ListFragment

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(aboutFragment: AboutFragment)

    fun inject(listFragment: ListFragment)
}