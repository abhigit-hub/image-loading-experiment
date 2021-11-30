package com.example.imageloadingexp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.squareup.picasso.Picasso
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

        val unscaledBitmap = ScalingUtilities.decodeResource(resources, mSourceId,
            mDstWidth, mDstHeight, ScalingUtilities.ScalingLogic.FIT)

        // Part 2: Scale image
        val scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, mDstWidth,
            mDstHeight, ScalingUtilities.ScalingLogic.FIT)
        unscaledBitmap.recycle()


        c5.setImageBitmap(scaledBitmap)
    }

    private fun initJPEGRow() {
        Glide.with(this)
                .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
                .override(500, 500)
                .into(c1)

        Glide.with(this)
                .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
                .override(500, 500)
                .into(c2)

        Glide.with(this)
                .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
                .downsample(DownsampleStrategy.AT_LEAST)
                .override(500, 500)
                .into(c3)

        Picasso.with(this)
                .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
                .resize(1000, 1000)
                .transform(SmoothingResizeTransformation(this, R.dimen.widthRes, R.dimen.heightRes))
                .resize(500, 500)
                .into(c4)
    }
}