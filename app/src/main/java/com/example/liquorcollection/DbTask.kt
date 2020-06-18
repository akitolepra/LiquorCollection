package com.example.liquorcollection

import android.os.AsyncTask
import com.example.liquorcollection.db.dao.DrinkBigCategoryDao
import com.example.liquorcollection.db.dao.LiquorRecordDao
import com.example.liquorcollection.db.entity.DrinkBigCategoryEntity
import com.example.liquorcollection.db.entity.LiquorRecordEntity
import java.lang.ref.WeakReference

class LoadLiquorRecord(private val dao: LiquorRecordDao, listener: DbTaskListener<List<LiquorRecordData>?>) :
    AsyncTask<Void, Void, List<LiquorRecordData>>() {
    private val mListener = WeakReference(listener)

    override fun doInBackground(vararg params: Void?): List<LiquorRecordData> {
        val list = mutableListOf<LiquorRecordData>()
        dao.loadAllLiquorRecord().forEach {
            list.add(LiquorRecordData(it.id ?: -1, it.liquorName, it.liquorImagePath))
        }
        return list
    }

    override fun onPostExecute(result: List<LiquorRecordData>?) {
        super.onPostExecute(result)
        result?.let {
            mListener.get()?.onResult(result)
        }
    }
}

class SaveLiquorRecord(private val dao: LiquorRecordDao, listener: DbTaskListener<List<LiquorRecordData>>) :
    AsyncTask<LiquorRecordData, String, Void>() {
    val weakReference = WeakReference(listener)

    override fun doInBackground(vararg params: LiquorRecordData): Void? {
        val record = params[0]
        dao.saveLiquorRecord(LiquorRecordEntity(null, record.name, record.imagePath))
        return null
    }

    override fun onPostExecute(result: Void?) {
//        val load = LoadLiquorRecord(dao)
//        load.execute()
    }
}

class SaveBigDrinkCategory(private val dao: DrinkBigCategoryDao) :
    AsyncTask<DrinkBigCategoryEntity, String, Int>() {
    override fun doInBackground(vararg params: DrinkBigCategoryEntity?): Int {
        val entity = params[0] as DrinkBigCategoryEntity
        dao.saveBigDrinkCategory(entity)
        return -1
    }
}

class SaveBigDrinkCategoryList(private val dao: DrinkBigCategoryDao) :
    AsyncTask<List<DrinkBigCategoryEntity>, String, Int>() {
    override fun doInBackground(vararg params: List<DrinkBigCategoryEntity>?): Int {
        val list = params[0] as List<DrinkBigCategoryEntity>
        dao.saveBigDrinkCategoryList(list)
        return -1
    }
}

class LoadBigDrinkCategoryList(private val dao: DrinkBigCategoryDao, listener: DbTaskListener<List<DrinkBigCategoryData>>) :
    AsyncTask<Void, Void, List<DrinkBigCategoryData>>() {
    private val mListener = WeakReference(listener)

    override fun doInBackground(vararg params: Void?): List<DrinkBigCategoryData> {
        val list = mutableListOf<DrinkBigCategoryData>()
        dao.loadAllBigDrinkCategory().forEach {
            list.add(DrinkBigCategoryData(it.id ?: -1, it.categoryName))
        }
        return list
    }

    override fun onPostExecute(result: List<DrinkBigCategoryData>?) {
        super.onPostExecute(result)
        result?.let {
            mListener.get()?.onResult(result)
        }
    }
}

interface DbTaskListener<E> {
    abstract fun onResult(result: E)
}
