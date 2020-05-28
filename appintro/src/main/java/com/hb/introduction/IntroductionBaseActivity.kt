@file:Suppress("TooManyFunctions")

package com.hb.introduction

import android.animation.ArgbEvaluator
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.*
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.hb.introduction.indicator.DotIndicatorController
import com.hb.introduction.indicator.IndicatorController
import com.hb.introduction.indicator.ProgressIndicatorController
import com.hb.introduction.internal.IntroductionViewPager
import com.hb.introduction.internal.LayoutUtil
import com.hb.introduction.internal.LogHelper
import com.hb.introduction.internal.PermissionWrapper
import com.hb.introduction.internal.viewpager.PagerAdapter

abstract class IntroductionBaseActivity : AppCompatActivity(), IntroductionViewPagerListener {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected var indicatorController: IndicatorController? = null

    protected var isButtonsEnabled: Boolean = true
        set(value) {
            field = value
            updateButtonsVisibility()
        }

    protected var isSkipButtonEnabled = true
        set(value) {
            field = value
            updateButtonsVisibility()
        }


    protected var isIndicatorEnabled = true
        set(value) {
            field = value
            indicatorContainer.isVisible = value
        }

    protected var isSystemBackButtonLocked = false

    protected var isColorTransitionsEnabled = false



    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var pager: IntroductionViewPager
    private var slidesNumber: Int = 0
    private var savedCurrentItem: Int = 0
    private var currentlySelectedItem = -1
    private val fragments: MutableList<Fragment> = mutableListOf()

    private lateinit var nextButton: View
    private lateinit var doneButton: View
    private lateinit var skipButton: View
    private lateinit var backButton: View
    private lateinit var indicatorContainer: ViewGroup

    private val currentSlideNumber: Int
        get() = pager.getCurrentSlideNumber(fragments.size)

    private var permissionsMap = HashMap<Int, PermissionWrapper>()

    private var retainIsButtonEnabled = true

    private lateinit var vibrator: Vibrator
    private val argbEvaluator = ArgbEvaluator()

    internal val isRtl: Boolean
        get() = LayoutUtil.isRtl(applicationContext)


    protected fun addSlide(fragment: Fragment) {
        if (isRtl) {
            fragments.add(0, fragment)
        } else {
            fragments.add(fragment)
        }
        pagerAdapter.notifyDataSetChanged()
    }

    @JvmOverloads
    protected fun askForPermissions(
        permissions: Array<String>,
        slideNumber: Int,
        required: Boolean = true
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (slideNumber <= 0) {
                error("Invalid Slide Number: $slideNumber")
            } else {
                permissionsMap[slideNumber] = PermissionWrapper(permissions, slideNumber, required)
            }
        }
    }

    private fun goToPreviousSlide() {
        pager.goToPreviousSlide()
    }

    protected fun goToNextSlide(isLastSlide: Boolean = pager.currentItem + 1 == slidesNumber) {
        if (isLastSlide) {
            onIntroFinished()
        } else {
            pager.goToNextSlide()
            onNextSlide()
        }
    }

    protected fun setImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }

    protected fun setStatusBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    protected fun setStatusBarColorRes(@ColorRes color: Int) {
        setStatusBarColor(ContextCompat.getColor(this, color))
    }

    protected fun setNavBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = color
        }
    }

    protected fun setNavBarColorRes(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = ContextCompat.getColor(this, color)
        }
    }

    protected fun showStatusBar(show: Boolean) {
        if (show) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    protected fun setNextPageSwipeLock(lock: Boolean) {
        if (lock) {
            retainIsButtonEnabled = this.isButtonsEnabled
            this.isButtonsEnabled = true
        } else {
            this.isButtonsEnabled = retainIsButtonEnabled
        }
        pager.isNextPagingEnabled = !lock
    }

    protected fun setSwipeLock(lock: Boolean) {
        if (lock) {
            retainIsButtonEnabled = this.isButtonsEnabled
            this.isButtonsEnabled = true
        } else {
            this.isButtonsEnabled = retainIsButtonEnabled
        }
        pager.isFullPagingEnabled = !lock
    }

    protected fun setProgressIndicator() {
        indicatorController = ProgressIndicatorController(this)
    }

    protected fun setIndicatorColor(
        @ColorInt selectedIndicatorColor: Int,
        @ColorInt unselectedIndicatorColor: Int
    ) {
        indicatorController?.selectedIndicatorColor = selectedIndicatorColor
        indicatorController?.unselectedIndicatorColor = unselectedIndicatorColor
    }

    protected fun setScrollDurationFactor(factor: Int) {
        pager.setScrollDurationFactor(factor.toDouble())
    }

    protected fun setTransformer(introductionTransformer: IntroductionPageTransformerType) {
        pager.setAppIntroPageTransformer(introductionTransformer)
    }

    protected fun setCustomTransformer(transformer: ViewPager.PageTransformer?) {
        pager.setPageTransformer(true, transformer)
    }

    override fun onUserRequestedPermissionsDialog() {
        LogHelper.d(TAG, "Requesting Permissions on $currentSlideNumber")
        requestPermissions()
    }

    protected open fun onUserDisabledPermission(permissionName: String) {}

    protected open fun onUserDeniedPermission(permissionName: String) {}

    protected open fun onPageSelected(position: Int) {}

    protected open fun onDonePressed(currentFragment: Fragment?) {}

    protected open fun onNextPressed(currentFragment: Fragment?) {}

    protected open fun onSkipPressed(currentFragment: Fragment?) {}

    protected open fun onNextSlide() {}

    protected open fun onIntroFinished() {}

    protected open fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)

        indicatorController = DotIndicatorController(this)

        showStatusBar(false)

        setContentView(layoutId)

        indicatorContainer = findViewById(R.id.indicator_container)
            ?: error("Missing Indicator Container: R.id.indicator_container")
        nextButton = findViewById(R.id.next) ?: error("Missing Next button: R.id.next")
        doneButton = findViewById(R.id.done) ?: error("Missing Done button: R.id.done")
        skipButton = findViewById(R.id.skip) ?: error("Missing Skip button: R.id.skip")
        backButton = findViewById(R.id.back) ?: error("Missing Back button: R.id.back")

        setTooltipText(nextButton, getString(R.string.app_intro_next_button))
        if (skipButton is ImageButton) {
            setTooltipText(skipButton, getString(R.string.app_intro_skip_button))
        }
        if (doneButton is ImageButton) {
            setTooltipText(doneButton, getString(R.string.app_intro_done_button))
        }
        if (backButton is ImageButton) {
            setTooltipText(backButton, getString(R.string.app_intro_back_button))
        }

        if (isRtl) {
            nextButton.scaleX = -1f
            backButton.scaleX = -1f
        }

        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        pagerAdapter = PagerAdapter(supportFragmentManager, fragments)
        pager = findViewById(R.id.view_pager)

        doneButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = true))
        nextButton.setOnClickListener(NextSlideOnClickListener(isLastSlide = false))
        backButton.setOnClickListener { pager.goToPreviousSlide() }
        skipButton.setOnClickListener {
            onSkipPressed(pagerAdapter.getItem(pager.currentItem))
        }

        pager.adapter = this.pagerAdapter
        pager.addOnPageChangeListener(OnPageChangeListener())
        pager.onNextPageRequestedListener = this

        setScrollDurationFactor(DEFAULT_SCROLL_DURATION_FACTOR)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        slidesNumber = fragments.size
        initializeIndicator()

        if (isRtl) {
            pager.currentItem = fragments.size - savedCurrentItem
        } else {
            pager.currentItem = savedCurrentItem
        }

        pager.post {
            val fragment = pagerAdapter.getItem(pager.currentItem)
            if (fragment != null) {
                dispatchSlideChangedCallbacks(
                    null,
                    pagerAdapter
                        .getItem(pager.currentItem)
                )
            } else {
                finish()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putInt(ARG_BUNDLE_SLIDES_NUMBER, slidesNumber)
            putBoolean(ARG_BUNDLE_RETAIN_IS_BUTTON_ENABLED, retainIsButtonEnabled)
            putBoolean(ARG_BUNDLE_IS_BUTTON_ENABLED, isButtonsEnabled)
            putBoolean(ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED, isSkipButtonEnabled)
            putBoolean(ARG_BUNDLE_IS_INDICATOR_ENABLED, isIndicatorEnabled)

            putInt(ARG_BUNDLE_LOCK_PAGE, pager.lockPage)
            putInt(ARG_BUNDLE_CURRENT_ITEM, pager.currentItem)
            putBoolean(ARG_BUNDLE_IS_FULL_PAGING_ENABLED, pager.isFullPagingEnabled)
            putBoolean(ARG_BUNDLE_IS_NEXT_PAGING_ENABLED, pager.isNextPagingEnabled)

            putSerializable(ARG_BUNDLE_PERMISSION_MAP, permissionsMap)

            putBoolean(ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED, isColorTransitionsEnabled)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(savedInstanceState) {
            slidesNumber = getInt(ARG_BUNDLE_SLIDES_NUMBER)
            retainIsButtonEnabled = getBoolean(ARG_BUNDLE_RETAIN_IS_BUTTON_ENABLED)
            isButtonsEnabled = getBoolean(ARG_BUNDLE_IS_BUTTON_ENABLED)
            isSkipButtonEnabled = getBoolean(ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED)
            isIndicatorEnabled = getBoolean(ARG_BUNDLE_IS_INDICATOR_ENABLED)

            pager.lockPage = getInt(ARG_BUNDLE_LOCK_PAGE)
            savedCurrentItem = getInt(ARG_BUNDLE_CURRENT_ITEM)
            pager.isFullPagingEnabled = getBoolean(ARG_BUNDLE_IS_FULL_PAGING_ENABLED)
            pager.isNextPagingEnabled = getBoolean(ARG_BUNDLE_IS_NEXT_PAGING_ENABLED)

            permissionsMap = (
                    (getSerializable(ARG_BUNDLE_PERMISSION_MAP) as HashMap<Int, PermissionWrapper>?)
                        ?: hashMapOf()
                    )
            isColorTransitionsEnabled = getBoolean(ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED)
        }
    }

    private fun initializeIndicator() {
        indicatorContainer.addView(indicatorController?.newInstance(this))
        indicatorController?.initialize(slidesNumber)
        indicatorController?.selectPosition(currentlySelectedItem)
    }

    override fun onKeyDown(code: Int, event: KeyEvent): Boolean {
        if (code == KeyEvent.KEYCODE_ENTER ||
            code == KeyEvent.KEYCODE_BUTTON_A ||
            code == KeyEvent.KEYCODE_DPAD_CENTER
        ) {
            val isLastSlide = pager.currentItem == pagerAdapter.count - 1
            goToNextSlide(isLastSlide)
            if (isLastSlide) {
                onDonePressed(pagerAdapter.getItem(pager.currentItem))
            }
            return false
        }
        return super.onKeyDown(code, event)
    }

    override fun onBackPressed() {
        if (isSystemBackButtonLocked) {
            return
        }
        if (pager.isFirstSlide(fragments.size)) {
            super.onBackPressed()
        } else {
            pager.goToPreviousSlide()
        }
    }


    private fun updateButtonsVisibility() {
        if (isButtonsEnabled) {
            val isLastSlide =
                !isRtl && pager.currentItem == slidesNumber - 1 ||
                        isRtl && pager.currentItem == 0
            nextButton.isVisible = !isLastSlide
            doneButton.isVisible = isLastSlide
            skipButton.isVisible = isSkipButtonEnabled && !isLastSlide
        } else {
            nextButton.isVisible = false
            doneButton.isVisible = false
            backButton.isVisible = false
            skipButton.isVisible = false
        }
    }

    override fun onCanRequestNextPage(): Boolean {
        val currentFragment = pagerAdapter.getItem(pager.currentItem)

        return if (currentFragment is SlidePolicy && !currentFragment.isPolicyRespected) {
            LogHelper.d(TAG, "Slide policy not respected, denying change request.")
            false
        } else {
            LogHelper.d(TAG, "Change request will be allowed.")
            true
        }
    }

    override fun onIllegallyRequestedNextPage() {
        val currentFragment = pagerAdapter.getItem(pager.currentItem)
        if (currentFragment is SlidePolicy) {
            if (!currentFragment.isPolicyRespected) {
                currentFragment.onUserIllegallyRequestedNextPage()
            }
        }
    }


    private fun shouldRequestPermission() = permissionsMap.containsKey(currentSlideNumber)

    private fun requestPermissions() {
        setSwipeLock(true)
        val permissionToRequest = permissionsMap[currentSlideNumber]
        permissionToRequest?.let {
            ActivityCompat.requestPermissions(
                this,
                it.permissions,
                PERMISSIONS_REQUEST_ALL_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        setSwipeLock(false)

        if (requestCode != PERMISSIONS_REQUEST_ALL_PERMISSIONS) {
            return
        }

        val deniedPermissions = grantResults
            .mapIndexed { index, result -> (permissions[index] to result) }
            .filter { (_, result) -> result == PackageManager.PERMISSION_DENIED }
            .map { (permission, _) -> permission }

        if (deniedPermissions.isEmpty()) {
            permissionsMap.remove(currentSlideNumber)
            goToNextSlide()
        } else {
            deniedPermissions.forEach(::handleDeniedPermission)
            pager.reCenterCurrentSlide()
        }
    }

    private fun handleDeniedPermission(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            onUserDeniedPermission(permission)
            permissionsMap[currentSlideNumber]?.let { requestedPermission ->
                if (!requestedPermission.required) {
                    permissionsMap.remove(requestedPermission.position)
                    goToNextSlide()
                }
            }
        } else {
            onUserDisabledPermission(permission)
        }
    }


    private fun setPermissionSlide() {
        if (pager.getCurrentSlideNumber(fragments.size) in permissionsMap) {
            pager.isPermissionSlide = true
        } else {
            pager.isPermissionSlide = false
            setSwipeLock(false)
        }
    }

    private fun dispatchSlideChangedCallbacks(oldFragment: Fragment?, newFragment: Fragment?) {
        if (oldFragment is SlideSelectionListener) {
            oldFragment.onSlideDeselected()
        }
        if (newFragment is SlideSelectionListener) {
            newFragment.onSlideSelected()
        }
        onSlideChanged(oldFragment, newFragment)
    }

    private fun performColorTransition(
        currentSlide: Fragment?,
        nextSlide: Fragment?,
        positionOffset: Float
    ) {
        if (currentSlide is SlideBackgroundColorHolder &&
            nextSlide is SlideBackgroundColorHolder
        ) {
            if (currentSlide.isAdded && nextSlide.isAdded) {
                val newColor = argbEvaluator.evaluate(
                    positionOffset,
                    currentSlide.defaultBackgroundColor,
                    nextSlide.defaultBackgroundColor
                ) as Int
                currentSlide.setBackgroundColor(newColor)
                nextSlide.setBackgroundColor(newColor)
            }
        } else {
            error("Color transitions are only available if all slides implement SlideBackgroundColorHolder.")
        }
    }

    private inner class NextSlideOnClickListener(var isLastSlide: Boolean) : View.OnClickListener {
        override fun onClick(view: View) {
            if (!onCanRequestNextPage()) {
                onIllegallyRequestedNextPage()
                return
            }
            if (shouldRequestPermission()) {
                requestPermissions()
                return
            }

            val currentFragment = pagerAdapter.getItem(pager.currentItem)
            if (isLastSlide) {
                onDonePressed(currentFragment)
            } else {
                onNextPressed(currentFragment)
            }
            goToNextSlide(isLastSlide)
        }
    }

    internal inner class OnPageChangeListener : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (isColorTransitionsEnabled && position < pagerAdapter.count - 1) {
                val currentSlide = pagerAdapter.getItem(position)
                val nextSlide = pagerAdapter.getItem(position + 1)
                performColorTransition(currentSlide, nextSlide, positionOffset)
            }
        }

        override fun onPageSelected(position: Int) {
            if (slidesNumber >= 1) {
                indicatorController?.selectPosition(position)
            }

            if (!pager.isNextPagingEnabled) {
                if (pager.currentItem != pager.lockPage) {
                    isButtonsEnabled = retainIsButtonEnabled
                    pager.isNextPagingEnabled = true
                }
            }
            updateButtonsVisibility()

            setPermissionSlide()

            this@IntroductionBaseActivity.onPageSelected(position)
            if (slidesNumber > 0) {
                if (currentlySelectedItem == -1) {
                    dispatchSlideChangedCallbacks(null, pagerAdapter.getItem(position))
                } else {
                    dispatchSlideChangedCallbacks(
                        pagerAdapter.getItem(currentlySelectedItem),
                        pagerAdapter.getItem(pager.currentItem)
                    )
                }
            }
            currentlySelectedItem = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private companion object {
        private val TAG = LogHelper.makeLogTag(IntroductionBaseActivity::class.java)

        private const val DEFAULT_SCROLL_DURATION_FACTOR = 1
        private const val DEFAULT_VIBRATE_DURATION = 20L
        private const val PERMISSIONS_REQUEST_ALL_PERMISSIONS = 1

        private const val ARG_BUNDLE_COLOR_TRANSITIONS_ENABLED = "colorTransitionEnabled"
        private const val ARG_BUNDLE_CURRENT_ITEM = "currentItem"
        private const val ARG_BUNDLE_IS_BUTTON_ENABLED = "isButtonsEnabled"
        private const val ARG_BUNDLE_IS_FULL_PAGING_ENABLED = "isFullPagingEnabled"
        private const val ARG_BUNDLE_IS_INDICATOR_ENABLED = "isIndicatorEnabled"
        private const val ARG_BUNDLE_IS_NEXT_PAGING_ENABLED = "isNextPagingEnabled"
        private const val ARG_BUNDLE_IS_SKIP_BUTTON_ENABLED = "isSkipButtonsEnabled"
        private const val ARG_BUNDLE_LOCK_PAGE = "lockPage"
        private const val ARG_BUNDLE_PERMISSION_MAP = "permissionMap"
        private const val ARG_BUNDLE_RETAIN_IS_BUTTON_ENABLED = "retainIsButtonEnabled"
        private const val ARG_BUNDLE_SLIDES_NUMBER = "slidesNumber"
    }
}

private var View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.INVISIBLE
    }
