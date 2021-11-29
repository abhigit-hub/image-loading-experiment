package com.example.imageloadingexp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_jpeg.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initJPEGRow()
    }

    private fun initJPEGRow() {
        Glide.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .override(500, 500)
            .into(c2)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .transform(SmoothingResizeTransformation(this, R.dimen.widthRes, R.dimen.heightRes))
            .resize(500, 500)
            .into(c3)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .transform(SmoothingResizeTransformation(this, R.dimen.widthRes, R.dimen.heightRes))
            .resize(750, 750)
            .into(c4)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .transform(SmoothingResizeTransformation(this, R.dimen.widthRes, R.dimen.heightRes))
            .resize(1000, 1000)
            .into(c5)

/*        c4.load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")

        val uri = Uri.parse("android.resource://com.harman.audiofocus1/drawable/dummy1")
        c5.setImageURI("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")*/
    }
}