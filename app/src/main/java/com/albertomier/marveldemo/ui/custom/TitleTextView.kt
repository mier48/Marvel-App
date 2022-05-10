package com.albertomier.marveldemo.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TitleTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        customize(context)
    }

    private fun customize(context: Context) {
        typeface = Typeface.createFromAsset(context.assets, "fonts/FjallaOne-Regular.ttf")
    }
}