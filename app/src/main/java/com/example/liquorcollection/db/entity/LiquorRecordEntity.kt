package com.example.liquorcollection.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liquor_record")
data class LiquorRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "liquor_name") val liquorName: String,
    @ColumnInfo(name = "liquor_image_path") val liquorImagePath: String
)

