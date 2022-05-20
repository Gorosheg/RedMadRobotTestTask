package gorosheg.myapplication.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Toolbar.toolbarSettings(
    activity: AppCompatActivity,
    isBackArrowEnabled: Boolean,
    callback: (AppCompatActivity) -> Unit
) {
    activity.setSupportActionBar(this)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(isBackArrowEnabled)

    setNavigationOnClickListener {
        callback.invoke(activity)
    }
}