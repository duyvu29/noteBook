package com.example.notebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemrcv.view.*
import kotlinx.android.synthetic.main.itemrcvcomplete.view.*
import org.w3c.dom.Text

class adaperRcv2 : RecyclerView.Adapter<adaperRcv2.itemholder> {
    var context : Context
    var list: ArrayList<EntityMission>
    constructor(context: Context, list: ArrayList<EntityMission>) : super() {
        this.context = context
        this.list = list
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
        return adaperRcv2.itemholder(view)
    }

    override fun onBindViewHolder(holder: itemholder, position: Int) {
        var item = list[holder.adapterPosition]
        holder.txt2.text = item.DescMisson
        holder.imgCheck.setImageResource(R.drawable.check)
    }

    override fun getItemCount(): Int {
       return list.size
    }

}