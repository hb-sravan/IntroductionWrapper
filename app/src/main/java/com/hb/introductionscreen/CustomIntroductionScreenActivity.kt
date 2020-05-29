package com.hb.introductionscreen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hb.introduction.*

class CustomIntroductionScreenActivity : IntroductionWithSkipButton() {


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
            IntroductionFragment.newInstance(
                "Banks",
                "We carefully verify all banks before add them into the app",
                imageDrawable = R.drawable.banks,
                backgroundColor = Color.parseColor("#678FB4")
            )
        )

        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout1))
        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout2))
        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout3))



        // Change the color of the dot indicator.
        setIndicatorColor(Color.WHITE, Color.BLACK)

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


