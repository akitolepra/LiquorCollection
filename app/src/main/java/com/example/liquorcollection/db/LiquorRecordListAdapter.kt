package com.example.liquorcollection.db

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.liquorcollection.LiquorRecordData
import com.example.liquorcollection.R

class LiquorRecordListAdapter(private val mContext: Context) : BaseAdapter() {
    private lateinit var mLiquorRecordList: List<LiquorRecordData>
    private var mLayoutInflater: LayoutInflater =
        mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    fun setLiquorRecordList(liquorRecordList: List<LiquorRecordData>) {
        mLiquorRecordList = liquorRecordList
    }

    override fun getCount(): Int {
        return mLiquorRecordList.size
    }

    override fun getItem(position: Int): Any {
        return mLiquorRecordList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, argConvertView: View?, parent: ViewGroup?): View {
        var convertView = argConvertView
        if (convertView == null) {
            convertView =
                mLayoutInflater.inflate(R.layout.liquor_record_list, parent, false) as View
        }
        convertView.findViewById<TextView>(R.id.drink_name)?.text = mLiquorRecordList[position].name
        return convertView
    }
}