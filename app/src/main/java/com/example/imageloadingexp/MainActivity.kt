package com.example.imageloadingexp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            .into(c2)

        Glide.with(this)
                .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
                .override(600, 600)
                .into(c3)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .into(c4)

        Picasso.with(this)
            .load("https://demo-web-harman.demandware.net/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw37446e91/Astra_Hero_Black-1605x1605px.png")
            .resize(600, 600)
            .into(c5)
    }
}