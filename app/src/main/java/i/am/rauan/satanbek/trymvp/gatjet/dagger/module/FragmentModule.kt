package i.am.rauan.satanbek.trymvp.gatjet.dagger.module

import dagger.Module
import dagger.Provides
import i.am.rauan.satanbek.trymvp.gatjet.requests.api.CoreApi
import i.am.rauan.satanbek.trymvp.gatjet.ui.about.AboutContract
import i.am.rauan.satanbek.trymvp.gatjet.ui.about.AboutPresenter
import i.am.rauan.satanbek.trymvp.gatjet.ui.list.ListContract
import i.am.rauan.satanbek.trymvp.gatjet.ui.list.ListPresenter

@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideCoreApi(): CoreApi {
        return CoreApi.create()
    }
}