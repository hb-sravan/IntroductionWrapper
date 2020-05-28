package com.hb.introductionscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hb.introductionscreen.databinding.ActivityMainBinding
import com.hb.introductionscreen.paperOnboarding.PaperOnBoardingActivity
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
    }

    private fun navigateToNormalIntroductionScreen() {
        startActivity(Intent(this, NormalIntroductionActivity::class.java))
        finish()
    }

    private fun navigateToPaperOnboardingScreen() {

        startActivity(Intent(this, PaperOnBoardingActivity::class.java))
        finish()

    }
}
