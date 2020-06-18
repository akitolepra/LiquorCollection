package com.example.liquorcollection.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.liquorcollection.db.dao.DrinkBigCategoryDao
import com.example.liquorcollection.db.dao.LiquorRecordDao
import com.example.liquorcollection.db.entity.DrinkBigCategoryEntity
import com.example.liquorcollection.db.entity.LiquorRecordEntity

@Database(entities = [LiquorRecordEntity::class, DrinkBigCategoryEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun LiquorRecordDao(): LiquorRecordDao
    abstract fun DrinkBigCategoryDao(): DrinkBigCategoryDao
}