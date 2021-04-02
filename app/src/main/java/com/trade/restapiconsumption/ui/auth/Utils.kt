package com.trade.restapiconsumption.ui.auth

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.trade.restapiconsumption.data.network.Resource

fun <A: Activity> Activity.startNewActivity(activity: Class<A>)
{
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean)
{
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) { isEnabled = enabled
alpha = if (enabled) 1f else 0.5f
}

fun View.snackBar(message:String, action: (() -> Unit)? = null)
{
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
        failure: Resource.Failure,
        retryAction: (() -> Unit)? = null
)
{
    when{
        failure.isNetworkError ->{
            requireView().snackBar("please check internet connection", retryAction) }
            failure.errorCode == 401 -> {
                if (this is LoginFragment){
                    requireView().snackBar("You have entered incorrect credentials")
                }
                else {
                    //@todo perform logout operation
                }
            }
        else -> {
            val error = failure.errorbody.toString()
            requireView().snackBar(error)
        }
    }

}