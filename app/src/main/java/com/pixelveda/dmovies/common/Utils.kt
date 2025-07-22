package com.pixelveda.dmovies.common



import android.content.Context
import androidx.appcompat.app.AlertDialog

object Utils {

    fun defaultDialog(context: Context, title: String, setMessage: String) {
        val dialog =AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(setMessage)
            .setPositiveButton("OK") { dialog, _ ->
                {
                    dialog.dismiss()
                }
            }.create()
        dialog.show()
    }

}