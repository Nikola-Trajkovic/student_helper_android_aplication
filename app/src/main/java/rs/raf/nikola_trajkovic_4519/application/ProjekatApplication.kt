package rs.raf.nikola_trajkovic_4519.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.nikola_trajkovic_4519.modules.beleskeModule
import rs.raf.nikola_trajkovic_4519.modules.movieModule
import rs.raf.nikola_trajkovic_4519.modules.coreModule
import timber.log.Timber

class ProjekatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            movieModule,
            beleskeModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@ProjekatApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}