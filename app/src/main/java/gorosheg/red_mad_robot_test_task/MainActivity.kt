package gorosheg.red_mad_robot_test_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCharactersFragment()
    }

    private fun createCharactersFragment() {
        navigator.navigateToCharactersScreen(this)
    }

}