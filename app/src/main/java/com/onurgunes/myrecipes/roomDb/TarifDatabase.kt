package com.onurgunes.myrecipes.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurgunes.myrecipes.Models.Tarif

@Database(entities = [Tarif::class], version = 1)
abstract class TarifDatabase: RoomDatabase() {
    abstract fun tarifDao(): TarifDao
}