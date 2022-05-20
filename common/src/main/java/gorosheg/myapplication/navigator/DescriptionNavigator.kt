package gorosheg.myapplication.navigator

import androidx.fragment.app.FragmentActivity

interface DescriptionNavigator {

    fun navigateToEpisodesScreen(activity: FragmentActivity, characterId: Int)

    fun back(activity: FragmentActivity)
}