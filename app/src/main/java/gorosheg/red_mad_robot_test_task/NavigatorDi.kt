package gorosheg.red_mad_robot_test_task//to navigator

import gorosheg.myapplication.navigator.CharacterNavigator
import gorosheg.myapplication.navigator.DescriptionNavigator
import gorosheg.myapplication.navigator.EpisodesNavigator
import org.koin.dsl.module

val navigatorModule = module {
    single<MainNavigator> { get<NavigatorImpl>() }
    single<CharacterNavigator> { get<NavigatorImpl>() }
    single<DescriptionNavigator> { get<NavigatorImpl>() }
    single<EpisodesNavigator> { get<NavigatorImpl>() }
    single { NavigatorImpl() }
}