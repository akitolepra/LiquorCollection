package com.example.liquorcollection.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_big_category")
data class DrinkBigCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "big_category_name") val categoryName: String
)