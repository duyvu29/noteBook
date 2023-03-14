package com.example.notebook

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class DialogAddNew {
    lateinit var diaalogInterfacee: DialogInterface

    
    fun setDialogInterface( diaaalogIntrer: DialogInterface){

        this.diaalogInterfacee = diaaalogIntrer

    }

    interface DialogInterface {
         fun reloadList (isReload : Boolean)
    }
    fun showDialog(context: Context?, missionDB : DataBaseApp) { // Activity hoac Fragment hien tai chua dialog
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false) // true or false to close dialog when touch in screen
        dialog.setContentView(R.layout.activity_dialog_add_new)
        // ánh xạ
        var btnAdd          : Button = dialog.findViewById(R.id.btnAdd)
        var btnCancel       : Button = dialog. findViewById(R.id.btnCancel)
        var edtDesc         : EditText  = dialog. findViewById(R.id.edtAdMission)
        // sự kiện dialog
        // thêm
        btnAdd. setOnClickListener{
            var missionDao = missionDB.callDao()
            var nameMison = edtDesc.text.toString()
            var mission = EntityMission(id = null, DescMisson = nameMison, stateStar = false, stateCheckDone = "false", dateRemove = "")
            missionDao.addMission(mission)
            if (diaalogInterfacee != null){
                diaalogInterfacee.reloadList(true)
                dialog.dismiss()
            }
            Toast.makeText(context, "Successs", Toast.LENGTH_SHORT).show()
        }
        // hủy
        btnCancel.setOnClickListener{
            Toast.makeText(context, "Cancel !", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }
        // hiển thị dialog
        dialog.show()
        val window: Window? = dialog.window
        window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

    }
}