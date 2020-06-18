package com.example.liquorcollection.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.liquorcollection.*

class KindPadFragment : Fragment(), DbTaskListener<List<DrinkBigCategoryData>> {

    lateinit var mDrinkBigCategory: List<DrinkBigCategoryData>
    lateinit var mView: View
    lateinit var mCategoryList: List<DrinkBigCategoryData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mView = inflater.inflate(R.layout.layout_kind_pad, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategory()
    }

    private fun getCategory() {
        if (mDrinkBigCategory == null) {
            val dao = MyApplication.database.DrinkBigCategoryDao()
            LoadBigDrinkCategoryList(dao, this).execute()
        }
    }

    override fun onResult(result: List<DrinkBigCategoryData>) {
        for (data in result) {
        }
    }

    //コメントを追加
    fun doNothing(){
    }
}