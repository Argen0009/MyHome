package mbk.io.model

import android.app.Application
import mbk.io.myhome.di.HomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(HomeModule)
        }
    }
}