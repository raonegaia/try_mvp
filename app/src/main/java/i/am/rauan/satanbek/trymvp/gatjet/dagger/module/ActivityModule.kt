package i.am.rauan.satanbek.trymvp.gatjet.dagger.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import i.am.rauan.satanbek.trymvp.gatjet.ui.main.MainContract
import i.am.rauan.satanbek.trymvp.gatjet.ui.main.MainPresenter

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}