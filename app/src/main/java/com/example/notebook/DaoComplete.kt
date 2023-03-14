package com.example.notebook

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
/** phần này cũng note lại nha, hay vl đó thằng ngu **/
@Dao
interface DaoComplete {
    @Insert
    fun addComplete(complete: EntityComplete)

    @Query("SELECT * FROM completeTable")
    fun getAllcomplete(): List<EntityComplete>

    @Query("UPDATE completeTable SET DescComplete = :descComplete WHERE id = :id")
    fun updateComplete(descComplete: String, id: Int)

    @Query("DELETE FROM completeTable WHERE id = :id")
    fun deleteComplete(id: Int)

    @Query("DELETE FROM completeTable")
    fun deleteAllcomplete()

    @Query("UPDATE completeTable SET stateStarComplete = :stateStarComplete WHERE id = :id")
    fun updateStateStarComplete(stateStarComplete: Boolean, id: Int)

    @Query("SELECT stateStarComplete FROM completeTable WHERE id = :id")
    fun getStateCompleteByID(id: Int): Boolean
}