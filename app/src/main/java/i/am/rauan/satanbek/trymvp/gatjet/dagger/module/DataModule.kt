package i.am.rauan.satanbek.trymvp.gatjet.dagger.module

import android.app.Application
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import i.am.rauan.satanbek.trymvp.gatjet.repositories.MyRepo


@Module
class DataModule (private val app: Application) {

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideMyRepo(): MyRepo = MyRepo(app)


}