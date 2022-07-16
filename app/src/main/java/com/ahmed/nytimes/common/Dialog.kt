package com.ahmed.nytimes.common

import android.app.AlertDialog
import android.content.Context

class Dialog {
    companion object {
        fun showMessageDialog(context: Context,msg:String) {
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.apply {
                setMessage(msg)
                setPositiveButton("Ok") { _, _ ->
                }

            }.create().show()
        }

    }
}