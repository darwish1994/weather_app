package com.currency.weatherapp.common.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.currency.weatherapp.R
import com.currency.weatherapp.databinding.DialogPopupErrorBinding

object DialogUtils {
    fun showErrorAlert(
        context: Context,
        retryListener: () -> Unit,
        cancelListener: () -> Unit?,
        code: Int
    ): AlertDialog {
        val layout: DialogPopupErrorBinding =
            DialogPopupErrorBinding.inflate(LayoutInflater.from(context))
        val alertDialog = AlertDialog.Builder(context).apply {
            setView(layout.root)
            setCancelable(false)
        }.create()
        alertDialog.apply {
            layout.apply {

                when(code){
                    NetworkCode.NO_INTERNET_CODE->{
                        this.ivError.setImageResource(R.drawable.ic_no_internet)
                        this.tvMessageError.text=context.getString(R.string.no_internet_connection_error)
                    }

                  else->{
                        this.ivError.setImageResource(R.drawable.ic_error)
                        this.tvMessageError.text=context.getString(R.string.something_wrong)
                    }

                }




                btnErrorCancel.setOnClickListener {
                    dismiss()
                    cancelListener.invoke()
                }
                layout.btnErrorRetry.setOnClickListener {
                    retryListener.invoke()
                    dismiss()
                }
            }
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }.show()

        return alertDialog

    }
}