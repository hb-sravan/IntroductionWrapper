package com.hb.introduction.model

import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.RawRes
import com.hb.introduction.*

data class LottieAnimationSliderPage @JvmOverloads constructor(
    var title: CharSequence? = null,
    var description: CharSequence? = null,
    var lottieAnimationUrl: CharSequence? = null,
    @RawRes var lottieAnimationResource: Int = 0,
    @ColorInt var backgroundColor: Int = 0,
    @ColorInt var titleColor: Int = 0,
    @ColorInt var descriptionColor: Int = 0,
    @FontRes var titleTypefaceFontRes: Int = 0,
    @FontRes var descriptionTypefaceFontRes: Int = 0,
    var titleTypeface: String? = null,
    var descriptionTypeface: String? = null,
    @DrawableRes var backgroundDrawable: Int = 0
) {
    val titleString: String? get() = title?.toString()
    val descriptionString: String? get() = description?.toString()
    val lottieAnimationUrlString: String? get() = lottieAnimationUrl?.toString()


    /**
     * Util method to convert a [LottieAnimationSliderPage] into an Android [Bundle].
     * This method will be used to pass the [LottieAnimationSliderPage] to [AppIntroBaseFragment] implementations.
     */
    fun toBundle(): Bundle {
        val newBundle = Bundle()
        newBundle.putString(ARG_LA_TITLE, this.titleString)
        newBundle.putString(ARG_LA_TITLE_TYPEFACE, this.titleTypeface)
        newBundle.putInt(ARG_LA_TITLE_TYPEFACE_RES, this.titleTypefaceFontRes)
        newBundle.putInt(ARG_LA_TITLE_COLOR, this.titleColor)
        newBundle.putString(ARG_LA_DESC, this.descriptionString)
        newBundle.putString(ARG_LA_DESC_TYPEFACE, this.descriptionTypeface)
        newBundle.putInt(ARG_LA_DESC_TYPEFACE_RES, this.descriptionTypefaceFontRes)
        newBundle.putInt(ARG_LA_DESC_COLOR, this.descriptionColor)
        newBundle.putString(ARG_LA_ANIMATION_URL, this.lottieAnimationUrlString)
        newBundle.putInt(ARG_LA_ANIMATION_RESOURCE, this.lottieAnimationResource)
        newBundle.putInt(ARG_LA_BG_COLOR, this.backgroundColor)
        newBundle.putInt(ARG_LA_BG_DRAWABLE, this.backgroundDrawable)
        return newBundle
    }
}
