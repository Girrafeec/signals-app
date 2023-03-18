package com.girrafeecstud.signals.feature_signals.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SosSignalCountdownDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage("Sending sos")
                .setNegativeButton("Cancel") { _, _ ->

                }
            builder.create()
        }
    }
}