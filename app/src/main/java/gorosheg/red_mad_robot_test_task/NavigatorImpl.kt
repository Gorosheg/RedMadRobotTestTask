package gorosheg.red_mad_robot_test_task

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import gorosheg.characters.presentation.CharactersFragment
import gorosheg.description.presentation.DescriptionFragment
import gorosheg.episodes.presentation.EpisodesFragment
import gorosheg.myapplication.navigator.CharacterNavigator
import gorosheg.myapplication.navigator.DescriptionNavigator
import gorosheg.myapplication.navigator.EpisodesNavigator

private const val CHARACTERS = "CharacterScreen"
private const val DESCRIPTION = "Description"
private const val EPISODES = "Episodes"

class NavigatorImpl : MainNavigator, CharacterNavigator, DescriptionNavigator, EpisodesNavigator {

    override fun navigateToCharactersScreen(activity: FragmentActivity) {
        val fragment = CharactersFragment.newInstance()
        activity.navigateToNextFragment(fragment, CHARACTERS)
    }

    override fun navigateToDescriptionScreen(activity: FragmentActivity, characterId: Int) {
        val fragment = DescriptionFragment.newInstance(characterId)
        activity.navigateToNextFragment(fragment, DESCRIPTION)
    }

    override fun navigateToEpisodesScreen(activity: FragmentActivity, characterId: Int) {
        val fragment = EpisodesFragment.newInstance(characterId)
        activity.navigateToNextFragment(fragment, EPISODES)
    }

    private fun FragmentActivity.navigateToNextFragment(fragment: Fragment, fragmentKey: String) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            addToBackStack(fragmentKey)
            commit()
        }
    }

    override fun back(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStack()
    }
}