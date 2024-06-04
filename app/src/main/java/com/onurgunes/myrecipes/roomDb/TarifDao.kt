package com.onurgunes.myrecipes.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.onurgunes.myrecipes.Models.Tarif

@Dao
interface TarifDao {


    @Query("SELECT * FROM Tarif")
    fun getALL() : List<Tarif>


    @Insert
    fun insert (tarif: Tarif)



    @Delete
    fun delete (tarif: Tarif)
}