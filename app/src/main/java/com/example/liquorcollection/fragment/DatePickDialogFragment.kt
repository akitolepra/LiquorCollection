package com.example.liquorcollection.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickDialogFragment : DialogFragment() {

    private lateinit var mListener: DatePickerDialog.OnDateSetListener

    companion object {
        fun getInstance(listener: DatePickerDialog.OnDateSetListener): DialogFragment {
            return DatePickDialogFragment().also {
                it.mListener = listener
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        activity?.let {
            return DatePickerDialog(it, mListener, year, month, day)
        }
        throw IllegalStateException("Dialog is not attached  to Activity!")
    }
}