package com.example.liquorcollection

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.liquorcollection.fragment.EachLiquorRecordFragment
import kotlinx.android.synthetic.main.activity_add_new_liquor.*
import kotlinx.android.synthetic.main.activity_each_liquor_record.toolbar

class EachLiquorRecordActivity : AppCompatActivity(),
    EachLiquorRecordFragment.EachLiquorOnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_each_liquor_record)
        setSupportActionBar(toolbar)

        val recordId = intent.getIntExtra(MainActivity.RECORD_ID, -1)
        val imagePath = intent.getStringExtra(MainActivity.IMAGE_PATH)
        val fragment = EachLiquorRecordFragment.newInstance(recordId, imagePath, this)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.appBarLayout, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onClassTouch() {
//        each_liquor_record_class_pad.visibility = View.VISIBLE
        val params = add_liquor_image_scroll.layoutParams
        params.height = 300
        add_liquor_image_scroll.layoutParams = params
    }
}