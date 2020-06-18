package com.example.liquorcollection.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.liquorcollection.db.entity.DrinkBigCategoryEntity

@Dao
interface DrinkBigCategoryDao {

    @Query("SELECT * FROM drink_big_category")
    fun loadAllBigDrinkCategory():List<DrinkBigCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBigDrinkCategory(entity: DrinkBigCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBigDrinkCategoryList(entities: List<DrinkBigCategoryEntity>)
}