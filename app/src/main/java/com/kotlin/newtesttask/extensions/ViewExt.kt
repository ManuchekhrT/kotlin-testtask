package com.kotlin.newtesttask.extensions

import android.content.Context
import android.view.View
import android.widget.Toast


fun View.showLoading(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}