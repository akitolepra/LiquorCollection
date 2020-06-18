package com.example.liquorcollection.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.DatePicker
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.liquorcollection.Image
import com.example.liquorcollection.MyDate
import com.example.liquorcollection.R
import kotlinx.android.synthetic.main.activity_add_new_liquor.*
import kotlinx.android.synthetic.main.fragment_each_liquor_record.*
import kotlinx.android.synthetic.main.fragment_each_liquor_record.each_record_date
import kotlinx.android.synthetic.main.fragment_each_liquor_record.each_record_time
import kotlinx.android.synthetic.main.layout_kind_pad.*

class EachLiquorRecordFragment : Fragment(), DatePickerDialog.OnDateSetListener{

    companion object {
        private const val RECORD_ID = "record_id"
        private const val IMAGE_PATH = "image_path"

        fun newInstance(recordId: Int, imagePath: String, listener: EachLiquorOnTouchListener): Fragment {
            return EachLiquorRecordFragment().apply {
                val bundle = Bundle().apply {
                    putInt(RECORD_ID, recordId)
                    putString(IMAGE_PATH, imagePath)
                    mListener = listener
                }
                arguments = bundle
            }
        }

        fun newInstance(listener: EachLiquorOnTouchListener): Fragment {
            return EachLiquorRecordFragment().apply {
                val bundle = Bundle().apply {
                    mListener = listener
                }
                arguments = bundle
            }
        }
    }

    private lateinit var mListener: EachLiquorOnTouchListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_each_liquor_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        MyDate().also {
            each_record_date.text = it.getCurrentDateInEightDigitWithSlash()
            each_record_time.text = it.getCurrentTime()
        }
        arguments?.let {
            each_record_date.text = it.getInt(RECORD_ID, -1).toString()
            val imageBitmap = Image(it.getString(IMAGE_PATH, "")).getBitmap(activity)
            each_record_liquor_image.setImageBitmap(imageBitmap)
        }

        val activity = activity
        activity?.let {
            each_record_date.setOnClickListener {
                DatePickDialogFragment.getInstance(this)
                    .show(activity.supportFragmentManager, "datePicker")
            }

            each_record_liquor_image.setOnClickListener {
                showImageDialog(it)
            }
        }

        each_record_category.setOnClickListener{
            each_liquor_record_class_pad.visibility = View.VISIBLE
            val params = each_liquor_record_scroll.layoutParams
            params.height = 800
            each_liquor_record_scroll.layoutParams = params
        }

        kind_tab_close.setOnClickListener{
            each_liquor_record_class_pad.visibility = View.GONE
            val params = each_liquor_record_scroll.layoutParams
            params.height = -2
            each_liquor_record_scroll.layoutParams = params
        }

    }

    private fun showImageDialog(view: View) {
        val activity = activity ?: return
        val imageView = ImageView(activity)
        val bitmap = ((view as ImageView).drawable as BitmapDrawable).bitmap
        imageView.setImageBitmap(bitmap)
        val width: Double
        activity.windowManager?.defaultDisplay?.apply {
            val size = Point()
            getSize(size)
            width = size.x.toDouble()
            val factor = width / bitmap.width
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            Dialog(activity).apply {
                this.window?.requestFeature(Window.FEATURE_NO_TITLE)
                this.setContentView(imageView)
                this.window?.setLayout(
                    (bitmap.width * factor).toInt(),
                    (bitmap.height * factor).toInt()
                )
                this.show()
            }
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        each_record_date.text = "$year/$month/$dayOfMonth"
    }

    interface EachLiquorOnTouchListener{
        fun onClassTouch()
    }

}