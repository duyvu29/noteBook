package com.example.notebook

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(entities = arrayOf(EntityMission::class), version = 1, exportSchema = true)

abstract class DataBaseApp : RoomDatabase() {
    abstract fun callDao(): DaoMission


}