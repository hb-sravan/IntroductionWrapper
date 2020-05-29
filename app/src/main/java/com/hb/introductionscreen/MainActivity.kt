package com.hb.introductionscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hb.introductionscreen.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            ) as ActivityMainBinding

        paperOnBoardingButton.setOnClickListener {
            navigateToPaperOnboardingScreen()
        }

        imageAndTitleIntroButton.setOnClickListener {
            navigateToNormalIntroductionScreen()
        }

        lottieAnimationIntroButton.setOnClickListener {
            navigateToLottieAnimationIntroductionScreen()
        }

        customIntroButton.setOnClickListener {
            navigateToCustomIntroductionScreen()
        }
    }

    private fun navigateToCustomIntroductionScreen() {
        startActivity(Intent(this, CustomIntroductionScreenActivity::class.java))
    }

    private fun navigateToLottieAnimationIntroductionScreen() {
        startActivity(Intent(this, LAIntroductionScreenActivity::class.java))
    }

    private fun navigateToNormalIntroductionScreen() {
        startActivity(Intent(this, IntroductionScreenActivity::class.java))
    }

    private fun navigateToPaperOnboardingScreen() {
        startActivity(Intent(this, PaperOnBoardingActivity::class.java))
    }
}
