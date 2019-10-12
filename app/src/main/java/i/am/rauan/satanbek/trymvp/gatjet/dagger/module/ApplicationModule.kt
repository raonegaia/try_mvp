package i.am.rauan.satanbek.trymvp.gatjet.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import i.am.rauan.satanbek.trymvp.gatjet.BaseApp
import javax.inject.Singleton

@Module
class ApplicationModule (private val baseApp: BaseApp) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}