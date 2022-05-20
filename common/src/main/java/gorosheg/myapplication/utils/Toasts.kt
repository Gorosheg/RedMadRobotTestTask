package gorosheg.myapplication.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String) {
    context?.showToast(message)
}

private fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
