package com.example.imageloadingexp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bumptech.glide.Glide
import com.example.imageloadingexp.ScalingUtilities.ScalingLogic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_jpeg.*


class MainActivity : AppCompatActivity() {

    /** Id of image resource to decode  */
    private var mSourceId = 0

    /** Wanted width of decoded image  */
    private val mDstWidth = 200

    /** Wanted height of decoded image  */
    private val mDstHeight = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initJPEGRow()
        initNewRow()
    }

    private fun initNewRow() {
        mSourceId = R.drawable.dummy1

        button_scaling_bad.setOnClickListener {
            badButtonPressed()
        }
        button_scaling_crop.setOnClickListener {
            cropButtonPressed()
        }
        button_scaling_fit.setOnClickListener {
            fitButtonPressed()
        }
    }

    private fun badButtonPressed() {
        val startTime: Long = SystemClock.uptimeMillis()

        // Part 1: Decode image
        val unscaledBitmap = BitmapFactory.decodeResource(resources, mSourceId)

        // Part 2: Scale image
        val scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap, mDstWidth, mDstHeight, true)
        unscaledBitmap.recycle()

        // Calculate memory usage and performance statistics
        val memUsageKb = unscaledBitmap.rowBytes * unscaledBitmap.height / 1024
        val stopTime: Long = SystemClock.uptimeMillis()

        // Publish results
        text_result.text = ("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.")
        image.setImageBitmap(scaledBitmap)
    }

    private fun cropButtonPressed() {
        val startTime = SystemClock.uptimeMillis()

        // Part 1: Decode image
        val unscaledBitmap = ScalingUtilities.decodeResource(resources, mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.CROP)

        // Part 2: Scale image
        val scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.CROP)
        unscaledBitmap.recycle()

        // Calculate memory usage and performance statistics
        val memUsageKb = unscaledBitmap.rowBytes * unscaledBitmap.height / 1024
        val stopTime = SystemClock.uptimeMillis()

        // Publish results
        text_result.text = ("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.")
        image.setImageBitmap(scaledBitmap)
    }

    private fun fitButtonPressed() {
        val startTime = SystemClock.uptimeMillis()

        // Part 1: Decode image
        val unscaledBitmap = ScalingUtilities.decodeResource(resources, mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.FIT)

        // Part 2: Scale image
        val scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.FIT)
        unscaledBitmap.recycle()

        // Calculate memory usage and performance statistics
        val memUsageKb = unscaledBitmap.rowBytes * unscaledBitmap.height / 1024
        val stopTime = SystemClock.uptimeMillis()

        // Publish results
        text_result.text = ("Time taken: " + (stopTime - startTime)
                + " ms. Memory used for scaling: " + memUsageKb + " kb.")
        image.setImageBitmap(scaledBitmap)
    }

    private fun initJPEGRow() {
        Glide.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .override(500, 500)
            .into(c2)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .resize(1000, 1000)
            .into(c3)

        c4.load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")

        val uri = Uri.parse("android.resource://com.harman.audiofocus1/drawable/dummy1")
        c5.setImageURI("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
    }
}