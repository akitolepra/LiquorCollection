package com.example.liquorcollection

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class Image(private val imagePath: String) {

    fun getBitmap(): Bitmap? {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                val file = File(imagePath)
                FileInputStream(file).use {
                    return BitmapFactory.decodeStream(it)
                }
            } else {
                val source = ImageDecoder.createSource(File(imagePath))
                return ImageDecoder.decodeBitmap(source)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return null
    }

    fun getBitmap(context: Context?): Bitmap? {
        context?:return null
        val uri = Uri.parse(imagePath)
        uri?.let {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                } else {
                    ImageDecoder.createSource(context.contentResolver, uri).also {
                        return ImageDecoder.decodeBitmap(it)
                    }
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        return null
    }

}