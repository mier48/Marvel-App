package com.albertomier.marveldemo.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.albertomier.marveldemo.R

class DescriptionTextView : AppCompatTextView {
    private val REGULAR = 0
    private val BOLD = 1
    private val ITALIC = 2

    constructor(context: Context) : super(context) {
        customize(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        customize(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        customize(context, attrs, defStyleAttr, 0)
    }

    private fun customize(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        if (attrs != null) {
            val attributes: TypedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.DescriptionTextView,
                defStyleAttr,
                defStyleRes
            )

            when (attributes.getInt(R.styleable.DescriptionTextView_typeFontStyle, REGULAR)) {
                REGULAR -> {
                    typeface = Typeface.createFromAsset(context.assets, "fonts/Exo2-Regular.ttf")
                }
                BOLD -> {
                    typeface = Typeface.createFromAsset(context.assets, "fonts/Exo2-Bold.ttf")
                }
                ITALIC -> {
                    typeface = Typeface.createFromAsset(context.assets, "fonts/Exo2-Italic.ttf")
                }
            }
        } else {
            typeface = Typeface.createFromAsset(context.assets, "fonts/Exo2-Regular.ttf")
        }
    }
}