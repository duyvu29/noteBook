package com.example.notebook

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

/** phần này cũng note lại nha, hay vl đó thằng ngu **/
@Dao
interface DaoMission {
    @Insert
    fun addMission(mission: EntityMission)

    @Query("SELECT * FROM missionTable")
    fun getAllMission(): List<EntityMission>
    /**--------------------------------------**/
    @Query("SELECT * FROM missionTable WHERE stateCheckDone = 'false'")
    fun getAllMissionNotDone(): List<EntityMission>

    /**----------------------------------------**/
    @Query("SELECT * FROM missionTable WHERE stateCheckDone = 'true'")
    fun getAllMissionDone(): List<EntityMission>

    /**----------------------------------------**/
    @Query("UPDATE missionTable SET dateRemove = :dateRemove WHERE id = :id")
    fun updateDateRemove(dateRemove: String, id: Int)

    @Query("UPDATE missionTable SET DescMisson = :descMisson WHERE id = :id")
    fun updateMission(descMisson: String, id: Int)

    @Query("DELETE FROM missionTable WHERE id = :missionId")
    fun deleteMission(missionId: Int)

    @Query("DELETE FROM missionTable")
    fun deleteAllMission()

    @Query("UPDATE missionTable SET stateStar = :stateStar WHERE id = :id")
    fun updateStateStar(stateStar: Boolean, id: Int)

    /**----------------------------------------**/
    @Query("UPDATE missionTable SET stateCheckDone = :stateCheckDone WHERE id = :id")
    fun updateStateCheckDone(stateCheckDone: String, id: Int)

    @Query("SELECT stateStar FROM missionTable WHERE id = :id")
    fun getStateByID(id: Int): Boolean

    /**----------------------------------------**/
    @Query("SELECT stateCheckDone FROM missionTable WHERE id = :id")
    fun getStateDoneCheckByID(id: Int): Boolean
}