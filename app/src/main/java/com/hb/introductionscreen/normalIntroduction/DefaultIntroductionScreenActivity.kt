package com.hb.introductionscreen.normalIntroduction

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hb.introduction.IntroductionWithSkipText
import com.hb.introduction.IntroductionFragment
import com.hb.introductionscreen.HomeActivity
import com.hb.introductionscreen.R

class DefaultIntroductionScreenActivity : IntroductionWithSkipText() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            IntroductionFragment.newInstance(
                "Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                imageDrawable = R.drawable.hotels,
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

        addSlide(
            IntroductionFragment.newInstance(
                "Stores",
                "All local stores are categorized for your convenience",
                imageDrawable = R.drawable.stores,
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


