package com.hb.introduction

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout

abstract class IntroductionWithSkipButton : IntroductionBaseActivity() {

    override val layoutId = R.layout.introduction_with_skip_button

    @IdRes
    var backgroundResource: Int? = null
        set(value) {
            field = value
            if (field != null) {
                field?.let { backgroundFrame.setBackgroundResource(it) }
            }
        }

    var backgroundDrawable: Drawable? = null
        set(value) {
            field = value
            if (field != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    backgroundFrame.background = field
                }
            }
        }

    private lateinit var backgroundFrame: ConstraintLayout
    private lateinit var bottomBar: View
    private lateinit var skipImageButton: ImageButton
    private lateinit var nextImageButton: ImageButton
    private lateinit var doneImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = findViewById(R.id.skip)
        nextImageButton = findViewById(R.id.next)
        doneImageButton = findViewById(R.id.done)
        if (isRtl) {
            skipImageButton.scaleX = -1F
        }
    }

    /**
     * Override viewpager bar color
     * @param color your color resourcebdev
     *
     */
    fun setBarColor(@ColorInt color: Int) {
        bottomBar.setBackgroundColor(color)
    }

    /**
     * Override Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
    }

    /**
     * Override Next button drawable
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
        nextImageButton.setImageDrawable(imageNextButton)
    }


    /**
     * Override Done button drawable
     * @param imageDoneButton your drawable resource
     */
    fun setImageDoneButton(imageDoneButton: Drawable) {
        doneImageButton.setImageDrawable(imageDoneButton)
    }
}
