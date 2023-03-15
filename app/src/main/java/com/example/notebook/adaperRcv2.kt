package com.example.notebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemrcv.view.*
import kotlinx.android.synthetic.main.itemrcvcomplete.view.*
import org.w3c.dom.Text

class adaperRcv2 : RecyclerView.Adapter<adaperRcv2.itemholder> {
    var context : Context
    var list: ArrayList<EntityMission>
    var db : DataBaseApp? = null
     lateinit  var missionDao : DaoMission


    constructor(context: Context, list: ArrayList<EntityMission>, missionDB : DataBaseApp) : super() {
        this.context = context
        this.list = list
        this.db = missionDB
       // this.missionDao = db!!.callDao()


    }

    fun reloadList(list: ArrayList<EntityMission>) {
        this.list = list
        notifyDataSetChanged()
    }


    class itemholder: RecyclerView.ViewHolder{
        var toggle_star : ToggleButton
        var txt2     : TextView
        var imgCheck : ImageView

        constructor(itemView: View) : super(itemView) {
            this.toggle_star   =itemView.toggle_star
            this.txt2       =  itemView.txtInsertMisson
            this.imgCheck   = itemView.imgRadioButton
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemholder {
       var view : View = LayoutInflater.from(parent.context).inflate( R.layout.itemrcv,parent,false)
        return  itemholder(view)
    }

    override fun onBindViewHolder(holder: itemholder, position: Int) {
        var item = list[holder.adapterPosition]
        holder.txt2.text = item.DescMisson
        holder.imgCheck.setImageResource(R.drawable.check)
        //var missionDB= missionDB.callDao()

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            val aler = AlertDialog.Builder(context)
            aler.setTitle("Thông báo")
            aler.setIcon(R.mipmap.ic_launcher)
            aler.setMessage("bạn muốn xóa hay không ?")
            aler.setPositiveButton("có") { dialog, which ->
                var mission =  db!!.callDao()

                mission.deleteMission(list[position].id!!)

                list.remove(list[position])

                reloadListt(list)
            }
            aler.setNegativeButton(
                "Không"
            ) { dialog, which -> }
            aler.show()
            true
        })
    }

    override fun getItemCount(): Int {
       return list.size
    }
    fun reloadListt(list: ArrayList<EntityMission>) {
        this.list = list
        notifyDataSetChanged()

    }

}