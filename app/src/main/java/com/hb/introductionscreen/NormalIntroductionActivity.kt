package com.hb.introductionscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hb.introductionscreen.databinding.ActivityNormalIntroductionBinding
import com.hb.introductionscreen.normalIntroduction.CustomIntroductionScreenActivity
import com.hb.introductionscreen.normalIntroduction.DefaultIntroductionScreenActivity
import kotlinx.android.synthetic.main.activity_normal_introduction.*

class NormalIntroductionActivity : AppCompatActivity() {

    lateinit var binding: ActivityNormalIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_normal_introduction
            ) as ActivityNormalIntroductionBinding

        defaultIntroduction.setOnClickListener {
            navigateToDefaultIntroductionScreen()
        }

        customIntroduction.setOnClickListener {
            navigateToCustomIntroductionScreen()
        }

    }

    private fun navigateToDefaultIntroductionScreen() {
        startActivity(Intent(this, DefaultIntroductionScreenActivity::class.java))
    }

    private fun navigateToCustomIntroductionScreen() {
        startActivity(Intent(this, CustomIntroductionScreenActivity::class.java))
    }
}
