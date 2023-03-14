package com.example.notebook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "completeTable")
class EntityComplete {
    @PrimaryKey(autoGenerate = true)        var id: Int? = -1
    @ColumnInfo(name = "DescComplete")      var DescComplete: String = ""
    @ColumnInfo(name = "stateStarComplete") var stateStarComple: Boolean = false

    constructor(id: Int?, DescComplete: String, stateStarComple: Boolean) {
        this.id = id
        this.DescComplete = DescComplete
        this.stateStarComple = stateStarComple
    }
}