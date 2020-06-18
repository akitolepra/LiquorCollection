package com.example.liquorcollection

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.liquorcollection.fragment.EachLiquorRecordFragment
import kotlinx.android.synthetic.main.activity_add_new_liquor.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class AddNewLiquorActivity : AppCompatActivity(), DbTaskListener<List<LiquorRecordData>> {

    private val liquorRecordDao = MyApplication.database.LiquorRecordDao()
    private lateinit var mImagePass: String

    companion object {
        const val REQUEST_PICK_IMAGE: Int = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_liquor)
        setSupportActionBar(toolbar)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.add_liquor_record_inner_const,
            EachLiquorRecordFragment()
        )
        transaction.commit()

        add_liquor_image_btn.setOnClickListener {
            getLiquorImage()
        }

        name_btn.setOnClickListener {
            liquor_image.setImageBitmap(null)
            val dbTask = SaveLiquorRecord(liquorRecordDao, this)
            dbTask.execute(LiquorRecordData(1, liquor_name.text.toString(), mImagePass ?: ""))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun getLiquorImage() {
        Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            setType("image/*")
            startActivityForResult(this, REQUEST_PICK_IMAGE)
        }
    }

    private fun setText(name: String) {
        liquor_text.text = name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            mImagePass = uri?.toString() ?: ""
            uri?.let {
                try {
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                        liquor_image.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        liquor_image.setImageBitmap(bitmap)
                    }
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        }
    }

    override fun onResult(result: List<LiquorRecordData>) {
        liquor_text.text = result[0].name
        readUri(Uri.parse(result[0].imagePath))
    }

    private fun readUri(uri: Uri) {
        uri?.let {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    liquor_image.setImageBitmap(bitmap)
                } else {
                    val source = ImageDecoder.createSource(contentResolver, uri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    liquor_image.setImageBitmap(bitmap)
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    private fun readFile(path: String) {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                Uri.parse(path)
                val file = File(path)
                FileInputStream(file).use {
                    val bitmap = BitmapFactory.decodeStream(it)
                    liquor_image.setImageBitmap(bitmap)
                }
            } else {
                val source = ImageDecoder.createSource(File(path))
                val bitmap = ImageDecoder.decodeBitmap(source)
                liquor_image.setImageBitmap(bitmap)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}