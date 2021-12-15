package com.example.saveomovies.util

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun showSnackBar(msg: String, view: View){
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
}