package com.hb.introduction

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.RawRes
import com.hb.introduction.model.LottieAnimationSliderPage
import com.hb.introduction.model.SliderPage

@Suppress("LongParameterList")
class IntroductionLottieAnimationFragment : IntroductionLottieAnimationBaseFragment() {

    override val layoutId: Int get() = R.layout.fragment_introduction_lottie_animation

    companion object {

        /**
         * Generates a new instance for [IntroductionLottieAnimationFragment]
         *
         * @param title CharSequence which will be the slide title
         * @param description CharSequence which will be the slide description
         * @param lottieAnimationUrl CharSequence which will be the lottie animation url
         * @param lottieAnimationResource @RawRes (Integer) the animation that will be
         *                             displayed, obtained from Raw Resources
         * @param backgroundColor @ColorInt (Integer) custom background color
         * @param titleColor @ColorInt (Integer) custom title color
         * @param descriptionColor @ColorInt (Integer) custom description color
         * @param titleTypefaceFontRes @FontRes (Integer) custom title typeface obtained
         *                             from Resources
         * @param descriptionTypefaceFontRes @FontRes (Integer) custom description typeface obtained
         *                             from Resources
         * @param backgroundDrawable @DrawableRes (Integer) custom background drawable
         *
         * @return An [IntroductionLottieAnimationFragment] created instance
         */
        @JvmOverloads
        @JvmStatic
        fun newInstance(
            title: CharSequence? = null,
            description: CharSequence? = null,
            lottieAnimationUrl: CharSequence? = null,
            @RawRes lottieAnimationResource : Int =0,
            @ColorInt backgroundColor: Int = 0,
            @ColorInt titleColor: Int = 0,
            @ColorInt descriptionColor: Int = 0,
            @FontRes titleTypefaceFontRes: Int = 0,
            @FontRes descriptionTypefaceFontRes: Int = 0,
            @DrawableRes backgroundDrawable: Int = 0
        ): IntroductionLottieAnimationFragment {
            return newInstance(
                LottieAnimationSliderPage(
                    title = title,
                    description = description,
                    lottieAnimationUrl = lottieAnimationUrl,
                    lottieAnimationResource = lottieAnimationResource,
                    backgroundColor = backgroundColor,
                    titleColor = titleColor,
                    descriptionColor = descriptionColor,
                    titleTypefaceFontRes = titleTypefaceFontRes,
                    descriptionTypefaceFontRes = descriptionTypefaceFontRes,
                    backgroundDrawable = backgroundDrawable
                )
            )
        }

        @JvmStatic
        fun newInstance(sliderPage: LottieAnimationSliderPage): IntroductionLottieAnimationFragment {
            val slide = IntroductionLottieAnimationFragment()
            slide.arguments = sliderPage.toBundle()
            return slide
        }
    }
}
