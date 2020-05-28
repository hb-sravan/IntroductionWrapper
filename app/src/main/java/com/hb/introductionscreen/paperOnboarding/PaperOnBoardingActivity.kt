package com.hb.introductionscreen.paperOnboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hb.introductionscreen.HomeActivity
import com.hb.introductionscreen.R
import com.hb.introductionscreen.databinding.ActivityPaperOnboardingBinding
import com.ramotion.paperonboarding.PaperOnboardingEngine
import com.ramotion.paperonboarding.PaperOnboardingPage
import java.util.*

class PaperOnBoardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaperOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_paper_onboarding
            ) as ActivityPaperOnboardingBinding

        val engine = PaperOnboardingEngine(
            findViewById(R.id.onboardingRootView),
            getDataForOnboarding(),
            applicationContext
        )

        engine.setOnChangeListener { oldElementIndex, newElementIndex ->

        }

        engine.setOnRightOutListener { // Probably here will be your exit action
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }


    }

    // Just example data for Onboarding
    private fun getDataForOnboarding(): ArrayList<PaperOnboardingPage>? {
        // prepare data
        val scr1 = PaperOnboardingPage(
            "Hotels",
            "All hotels and hostels are sorted by hospitality rating",
            Color.parseColor("#678FB4"),
            R.drawable.hotels,
            R.drawable.key
        )

        val scr2 = PaperOnboardingPage(
            "Banks",
            "We carefully verify all banks before add them into the app",
            Color.parseColor("#65B0B4"),
            R.drawable.banks,
            R.drawable.wallet
        )

        val scr3 = PaperOnboardingPage(
            "Stores",
            "All local stores are categorized for your convenience",
            Color.parseColor("#9B90BC"),
            R.drawable.stores,
            R.drawable.shopping_cart
        )

        val elements =
            ArrayList<PaperOnboardingPage>()
        elements.add(scr1)
        elements.add(scr2)
        elements.add(scr3)
        return elements
    }

}
