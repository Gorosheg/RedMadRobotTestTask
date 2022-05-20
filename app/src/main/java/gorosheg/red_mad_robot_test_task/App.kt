package gorosheg.red_mad_robot_test_task

import android.app.Application
import gorosheg.characters.di.charactersModule
import gorosheg.description.di.descriptionModule
import gorosheg.episodes.di.episodeModule
import gorosheg.network.networkModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                navigatorModule,
                charactersModule,
                descriptionModule,
                episodeModule,
                networkModule
            )
        }
    }
}