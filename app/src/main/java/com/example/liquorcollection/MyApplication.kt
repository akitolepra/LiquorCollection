package com.example.liquorcollection

import android.app.Application
import androidx.room.Room
import com.example.liquorcollection.db.database.AppDataBase
import com.example.liquorcollection.db.entity.DrinkBigCategoryEntity

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "app_database"
        ).build()
        createBasicCategory()
    }

    private fun createBasicCategory(){
        val drinkBigCategoryDao = database.DrinkBigCategoryDao()
        val entityList = ArrayList<DrinkBigCategoryEntity>()
        entityList.add(DrinkBigCategoryEntity(0, "お酒"))
        entityList.add(DrinkBigCategoryEntity(0, "ソフトドリンク"))
        entityList.add(DrinkBigCategoryEntity(0, "カレー"))
        val dbTask = SaveBigDrinkCategoryList(drinkBigCategoryDao)
        dbTask.execute(entityList)
    }
}