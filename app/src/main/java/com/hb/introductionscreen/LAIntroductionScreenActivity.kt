package com.hb.introductionscreen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hb.introduction.IntroductionWithSkipText
import com.hb.introduction.IntroductionLottieAnimationFragment

class LAIntroductionScreenActivity : IntroductionWithSkipText() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            IntroductionLottieAnimationFragment.newInstance(
                "Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                lottieAnimationUrl = "https://assets2.lottiefiles.com/packages/lf20_d2k0co.json",
                backgroundColor = Color.parseColor("#678FB4"),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                titleTypefaceFontRes = R.font.hind_bold,
                descriptionTypefaceFontRes = R.font.hind_light
            )
        )

        addSlide(
            IntroductionLottieAnimationFragment.newInstance(
                "Loading",
                lottieAnimationUrl = "https://assets10.lottiefiles.com/packages/lf20_gwBIWJ.json",
                backgroundColor = Color.parseColor("#678FB4")
            )
        )

        addSlide(
            IntroductionLottieAnimationFragment.newInstance(
                "Mountains",
                lottieAnimationResource = R.raw.mountain,
                backgroundColor = Color.parseColor("#678FB4")
            )
        )


        // Change the color of the dot indicator.
        setIndicatorColor(Color.WHITE, Color.BLACK)

        setSkipTextTypeface(R.font.hind_light)
        setNextTextTypeface(R.font.hind_light)
        setDoneTextTypeface(R.font.hind_bold)
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}


