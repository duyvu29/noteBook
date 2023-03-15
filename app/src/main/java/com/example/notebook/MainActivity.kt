package com.example.notebook

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var db: DataBaseApp

    lateinit var rcvMisson: RecyclerView
    lateinit var rcvComplete: RecyclerView

    lateinit var linearPress: LinearLayout

    var listMission: ArrayList<EntityMission> = ArrayList()
    var listDone: ArrayList<EntityMission> = ArrayList()

    lateinit var MainAdapter: AdapterMisson
    lateinit var missionDao: DaoMission

    lateinit var doneAdapter: adaperRcv2

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // ánh xạ
        mapping()
        //init db
        initRoomDB()
        // thêm sự kiện
        addEvent()

        if (getAllData().isNotEmpty()) {
            getAllMissionNotDone();
            getAllMissionDone();
        }
        setRcvMisson();
        setRcvComplete()
    }

    // ham2m thêm sự kiện
    private fun addEvent() {
        // sự kiện thêm tác vụ
        linearPress.setOnClickListener {
            val dialogAddNewStaff = DialogAddNew()
            dialogAddNewStaff.showDialog(this@MainActivity, db!!)
            dialogAddNewStaff.setDialogInterface(object : DialogAddNew.DialogInterface {
                override fun reloadList(isReload: Boolean) {
                    if (isReload) {
                        listMission.clear()
                        getAllMissionNotDone()
                        MainAdapter.reloadListt(listMission)
                    }
                }
            })
        }
        //  getAllData()
        //setRcvMisson()
        // recyclerView thứ 2
    }
    private fun mapping() {
        rcvMisson = findViewById(R.id.rcvMisson)
        rcvComplete = findViewById(R.id.rcvComplete)
        linearPress = findViewById(R.id.linearLyoutAdd)
    }
    private fun setRcvMisson() {
        MainAdapter = AdapterMisson(listMission, this, db)
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvMisson.setHasFixedSize(true)
        rcvMisson.layoutManager = manager
        rcvMisson.adapter = MainAdapter

       /* missionDao = db!!.callDao()
        missionDao.getAllMission()
        listMission.clear()
        MainAdapter.reloadListt(listMission)*/


        MainAdapter.setOnReloadListListener(object : AdapterMisson.ReloadListListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onReload(state: Boolean) {
                if (state) {
                    listDone.clear()
                    getAllMissionDone()
                    doneAdapter.reloadList(listDone)
                }
            }
        })

        /**MainAdapter.setOnItemSate(object : AdapterMisson.ClickListenerSate{
        override fun onItemClickSate(position: Int, v: View) {
        var img : ImageView = findViewById(R.id.imgStarButton)
        count++
        if (count == 1) {
        img.setImageResource(R.drawable.ic_baseline_star_24)
        }
        if (count == 2) {
        img.setImageResource(R.drawable.ic_baseline_star_border_24)
        count =0
        }}
        })**/
    }

    private fun setRcvComplete() {
//        list2 = ArrayList()
//        list2.add(dataMission(R.drawable.ic_baseline_check_circle_24,"",R.drawable.ic_baseline_star_24))
        doneAdapter = adaperRcv2(this, listDone,db)
        rcvComplete.adapter = doneAdapter
        rcvComplete.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /*listDone.clear()
        getAllData()
        doneAdapter.reloadList(listDone)*/


    }


    private fun initRoomDB() {
        // StaffDatabase duoc khoi tao 1 lan va dung o moi class trong app
        db = Room.databaseBuilder(applicationContext, DataBaseApp::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    fun getAllData(): ArrayList<EntityMission> {
        missionDao = db!!.callDao()
        listMission = missionDao.getAllMission() as ArrayList<EntityMission>
        return listMission
    }

    fun getAllMissionNotDone() {
//        listMission = ArrayList()
        missionDao = db!!.callDao()
        listMission = missionDao.getAllMissionNotDone() as ArrayList<EntityMission>
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMissionDone() {
//        listDone = ArrayList()
         missionDao = db!!.callDao()
        //var missionDao = missionDao
        listDone = missionDao.getAllMissionDone() as ArrayList<EntityMission>
        if (listDone.size >= 2) {
            bubbleSort(listDone)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bubbleSort(arr: ArrayList<EntityMission>) {
        for (i in arr.size - 1 downTo 1) { // Giới hạn cho phần tử cuối cùng
            for (j in 0 until i) {
                val date1: Date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(arr[j].dateRemove)
                val date2: Date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(arr[j + 1].dateRemove)
                if (date1 < date2) {
                    val temp: EntityMission = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }

    }


}