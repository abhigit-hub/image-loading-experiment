package com.example.imageloadingexp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.annotation.DimenRes
import com.squareup.picasso.Transformation
import kotlin.system.measureTimeMillis

/**
 * Android has a very poor filtering algorithm when taking large images and turning them into
 * thumbnails, leaving jagged edges and ugly artifacting. A work-around is to only scale down an
 * image a little bit at a time until you have the needed image size.
 *
 * This algorithm isn't very efficient, but still only takes ~100 millis to turn a 4096x4096 image
 * into a 210x210. This is acceptable if you aren't working with a large number of downsized images,
 * where CPU may become a bottleneck.
 */
class SmoothingResizeTransformation(
        private val widthPx: Int,
        private val heightPx: Int,
) : Transformation {
    constructor(
            context: Context,
            @DimenRes widthRes: Int,
            @DimenRes heightRes: Int,
    ) : this(
            widthPx = context.resources.getDimensionPixelOffset(widthRes),
            heightPx = context.resources.getDimensionPixelOffset(heightRes)
    )

    companion object {
        //private val logger = Logger<SmoothingResizeTransformation>()

        private const val SMOOTH_SCALE_RATIO = 0.5f
        private val SMOOTH_SCALE_MATRIX = Matrix().apply {
            setScale(SMOOTH_SCALE_RATIO, SMOOTH_SCALE_RATIO)
        }
    }

    override fun key(): String = "SmoothingResizeTransformation-$widthPx-$heightPx"

    override fun transform(source: Bitmap): Bitmap {
        val r: Bitmap
        val millis = measureTimeMillis {
            r = doTransform(source)
        }
        //logger.v { "Transformation took $millis millis" }
        return r
    }

    private fun doTransform(source: Bitmap): Bitmap {
        var bitmapIt: Bitmap = source
        while (true) {
            val widthRatio = widthPx / bitmapIt.width.toFloat()
            val heightRatio = heightPx / bitmapIt.height.toFloat()

            //logger.v { "Width Ratio: $widthRatio, Height Ratio: $heightRatio" }

            var isScalingDone = false
            val matrix: Matrix =
                    if (widthRatio < SMOOTH_SCALE_RATIO && heightRatio < SMOOTH_SCALE_RATIO) {
                        SMOOTH_SCALE_MATRIX
                    } else {
                        isScalingDone = true
                        Matrix().apply { setScale(widthRatio, heightRatio) }
                    }

            val newBitmap: Bitmap =
                    Bitmap.createBitmap(bitmapIt, 0, 0, bitmapIt.width, bitmapIt.height, matrix, true)
            bitmapIt.recycle()
            bitmapIt = newBitmap

            if (isScalingDone) {
                return bitmapIt
            }
        }
    }
}