package com.example.liquorcollection.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.liquorcollection.db.entity.LiquorRecordEntity

@Dao
interface LiquorRecordDao {

    @Query("SELECT * FROM liquor_record")
    fun loadAllLiquorRecord():List<LiquorRecordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLiquorRecord(entity: LiquorRecordEntity)
}