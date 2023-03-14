package com.example.notebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemrcv.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AdapterMisson : RecyclerView.Adapter<AdapterMisson.ItemHolder> {



    var list : ArrayList<EntityMission>
    var context: Context
    private  var missionDB : DataBaseApp
    private  var missionDao: DaoMission

    companion object {
        var reloadListListener: ReloadListListener? = null
    }
    interface ReloadListListener {
        fun onReload(state: Boolean)
    }

    fun setOnReloadListListener(reloadListListener: ReloadListListener?) {
        AdapterMisson.reloadListListener = reloadListListener!!
    }

    constructor(list: ArrayList<EntityMission>, context: Context, missionDB : DataBaseApp) : super() {
        this.list = list
        this.context = context
        this.missionDB = missionDB;
        this.missionDao = missionDB.callDao()
    }

    class ItemHolder: RecyclerView.ViewHolder{
        var imgCheck : ImageView
        var txtDescMisson: TextView
        var toggle_star : ToggleButton


        constructor(itemView: View ) : super(itemView) {

            this.imgCheck = itemView.imgRadioButton
            this.txtDescMisson =itemView. txtInsertMisson
            this.toggle_star = itemView.toggle_star

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var layoutView: View = LayoutInflater.from(parent.context).inflate(R.layout.itemrcv,parent,false)
        return  ItemHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        /** note lại nhá, phần hay vl**/
        var item = list[holder.adapterPosition]

        holder.txtDescMisson.text = item.DescMisson
        var stateStart = missionDao.getStateByID(item.id!!)
        holder.toggle_star.isChecked = stateStart

        holder.toggle_star.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    missionDao.updateStateStar(true, item.id!!)
                } else {
                    missionDao.updateStateStar(false, item.id!!)
                }
            }
        })


        holder.imgCheck.setOnClickListener {
            var missionDao = missionDB.callDao()
            missionDao.updateStateCheckDone("true", item.id!!)

            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val date = Date()
            missionDao.updateDateRemove(formatter.format(date), item.id!!)

            list.removeAt(position)
            reloadListt(list)

            reloadListListener!!.onReload(true)





            /**missionDao = missionDB!!.callDao()
            missionDao.deleteMission(list[position].id!!)
            list.clear()
            getAllData()
            reloadListt(list)
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT ).show()**/

        }

    }
    /** t note lại cho m đó dime m**/

    override fun getItemCount(): Int {
        return  list.size
    }
    fun reloadListt(list: ArrayList<EntityMission>) {
            this.list = list
            notifyDataSetChanged()

    }

}
