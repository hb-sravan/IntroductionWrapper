package com.hb.introduction

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import com.hb.introduction.internal.TypefaceContainer

abstract class IntroductionWithSkipText : IntroductionBaseActivity() {

    override val layoutId = R.layout.introduction_with_skip_text

    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        val bottomBar = findViewById<View>(R.id.bottom)
        bottomBar.setBackgroundColor(color)
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    fun setSeparatorColor(@ColorInt color: Int) {
        val separator = findViewById<View>(R.id.bottom_separator)
        separator.setBackgroundColor(color)
    }



    /**
     * Override skip text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        val skipText = findViewById<TextView>(R.id.skip)
        skipText.text = text
    }


    /**
     * Override skip text typeface
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.skip)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipTextColor(@ColorInt colorSkipButton: Int) {
        val skip = findViewById<TextView>(R.id.skip)
        skip.setTextColor(colorSkipButton)
    }






    /**
     * Override done text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setDoneTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.done)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    fun setDoneTextColor(@ColorInt colorDoneText: Int) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.setTextColor(colorDoneText)
    }





    /**
     * Override done text
     *
     * @param text your text
     */
    fun setNextText(text: CharSequence?) {
        val nextText = findViewById<TextView>(R.id.next)
        nextText.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setNextTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.next)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override done button text color
     *
     * @param colorNextText your color resource
     */
    fun setNextTextColor(@ColorInt colorNextText: Int) {
        val nextText = findViewById<TextView>(R.id.next)
        nextText.setTextColor(colorNextText)
    }


    /**
     * Show or hide the Separator line.
     * This is a static setting and Separator state is maintained across slides
     * until explicitly changed.
     *
     * @param showSeparator Set : true to display. false to hide.
     */
    fun showSeparator(showSeparator: Boolean) {
        val bottomSeparator = findViewById<View>(R.id.bottom_separator)
        if (showSeparator) {
            bottomSeparator.visibility = View.VISIBLE
        } else {
            bottomSeparator.visibility = View.INVISIBLE
        }
    }
}
