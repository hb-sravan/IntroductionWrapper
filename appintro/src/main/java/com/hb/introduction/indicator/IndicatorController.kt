package com.hb.introduction.indicator

import android.content.Context
import android.view.View
import androidx.annotation.ColorInt

internal const val DEFAULT_COLOR = 1

interface IndicatorController {

    @get:ColorInt
    var selectedIndicatorColor: Int

    @get:ColorInt
    var unselectedIndicatorColor: Int

    fun newInstance(context: Context): View

    fun initialize(slideCount: Int)

    fun selectPosition(index: Int)
}
