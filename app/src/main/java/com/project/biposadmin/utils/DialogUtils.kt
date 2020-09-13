package com.project.biposadmin.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.biposadmin.R
import com.project.biposadmin.model.Order
import com.project.biposadmin.presentation.order_list.OrderPresenter
import java.util.*

object DialogUtils {

    fun showDialogOrderDetail(activity: Activity, order: Order, presenter: OrderPresenter) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_resi)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        Objects.requireNonNull(dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)))

        val edtResiNumber =  dialog.findViewById<EditText>(R.id.edt_resiNumber)


        val btnConfirm = dialog.findViewById<Button>(R.id.btn_input)
        btnConfirm.setOnClickListener {
            val resiNumber =  edtResiNumber.text.toString()
            if (resiNumber.isNotEmpty()) {
                order.resiNumber = resiNumber
                presenter.postOrderStatus(order)

                dialog.dismiss()
            } else {
                Toast.makeText(activity, "harap isi resi terlebih dahulu", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()
    }
}