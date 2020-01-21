package com.doctoremr.utils.viewutils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.wipro.pocapp.MyWiproPoc
import com.wipro.pocapp.R
import com.wipro.pocapp.ui.home.MainActivity

fun ProgressBar.show() {
    visibility = View.VISIBLE
}


fun ProgressBar.hide() {
    visibility = View.INVISIBLE
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.login() {
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Context.logout() {
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun ProgressBar.toggle(context: Context) {
    if (visibility == View.VISIBLE) {
        visibility = View.GONE
    } else {
        visibility = View.GONE
    }
}

fun Context.showTextInputError(textInputLayout: TextInputLayout, messageId: Int) {
    textInputLayout.getEditText()?.requestFocus()
    textInputLayout.setErrorEnabled(true)
    textInputLayout.setError(textInputLayout.getContext().getString(messageId))
}

fun getStringValue(id: Int): String {
    return MyWiproPoc().getInstance().getApplicationContext().getString(id)
}

fun FragmentActivity.showProgressBar() {
    findViewById<ProgressBar>(R.id.progress_bar_global).visibility = View.VISIBLE
}

fun FragmentActivity.hideProgressBar() {
    findViewById<ProgressBar>(R.id.progress_bar_global).visibility = View.GONE
}



