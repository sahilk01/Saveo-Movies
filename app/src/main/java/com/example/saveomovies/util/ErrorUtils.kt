package com.example.saveomovies.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

fun handleError(msg: String?, rootView: View, progressBar: ProgressBar? = null) {
    progressBar?.gone()
    msg?.let {
        showSnackBar(it, rootView)
    }
}