package com.example.notebook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity (tableName = "missionTable")
class EntityMission {
    @PrimaryKey(autoGenerate = true) var id: Int? = -1
    @ColumnInfo(name = "DescMisson") var DescMisson: String = ""
    @ColumnInfo(name = "stateStar") var stateStar: Boolean = false
    @ColumnInfo(name = "stateCheckDone") var stateCheckDone: String = "false"
    @ColumnInfo(name = "dateRemove") var dateRemove: String = ""

    constructor(
        id: Int?,
        DescMisson: String,
        stateStar: Boolean,
        stateCheckDone: String,
        dateRemove: String
    ) {
        this.id = id
        this.DescMisson = DescMisson
        this.stateStar = stateStar
        this.stateCheckDone = stateCheckDone
        this.dateRemove = dateRemove
    }
}