package com.example.liquorcollection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListAdapter
import com.example.liquorcollection.db.LiquorRecordListAdapter
import kotlinx.android.synthetic.main.activity_add_new_liquor.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), DbTaskListener {
    companion object{
        val RECORD_ID = "record_id"
        val IMAGE_PATH = "image_path"
    }

    private lateinit var mListAdapter: LiquorRecordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            invokeAddNewLiquorActivity()
        }

        liquor_record_list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EachLiquorRecordActivity::class.java)
            val recordData = mListAdapter.getItem(position) as LiquorRecordData
            intent.putExtra(RECORD_ID, recordData.id)
            intent.putExtra(IMAGE_PATH, recordData.imagePath)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        loadAllLiquorRecord()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun invokeAddNewLiquorActivity() {
        val intent = Intent(this, AddNewLiquorActivity::class.java)
        startActivity(intent)
    }

    private fun loadAllLiquorRecord() {
        LoadLiquorRecord(MyApplication.database.LiquorRecordDao(), this).execute()
    }

    override fun onResult(result: List<LiquorRecordData>) {
        mListAdapter = LiquorRecordListAdapter(this)
        mListAdapter.setLiquorRecordList(result)
        liquor_record_list.adapter = mListAdapter
    }
}
