package com.hb.introductionscreen.normalIntroduction

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hb.introduction.IntroductionCustomLayoutFragment
import com.hb.introduction.IntroductionWithSkipButton
import com.hb.introductionscreen.HomeActivity
import com.hb.introductionscreen.R

class CustomIntroductionScreenActivity : IntroductionWithSkipButton() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout1))
        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout2))
        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout3))
        addSlide(IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout4))

        setProgressIndicator()
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
