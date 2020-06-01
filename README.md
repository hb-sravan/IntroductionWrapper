# IntroductionWrapper - Library used to build application introduction/onboarding with in no time

# Overview

This is an Android Library that helps you build introduction for your App. With this help you create a great onboarding experience.

 
| Name | Preview |
| ---: | :-----: |
| Paper Onboarding | <img src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/paper_onboard.gif" alt="Paper Onboarding" width="50%"/> |
| Normal Introduction | <img src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/nomal_introduction.gif" alt="Normal Introduction" width="50%"/> |
| Lottie Animation Introduction | <img src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/lottie_introduction.gif" alt="Lottie Animation Introduction" width="50%"/> |
| Customized Introduction | <img src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/custom.gif" alt="Customized Introduction" width="50%"/> |


# Use Case
1. If you want to show some introduction with number of slides/pages in your application then this library is very useful
2. This library is providing you number of options to create app introduction or paper onboarding type

# How it works?

# Paper onboarding

In our Activity onCreate method 

```kotlin
val engine = PaperOnboardingEngine(
            findViewById(R.id.onboardingRootView),
            getDataForOnboarding(),
            applicationContext
        )

        engine.setOnChangeListener { oldElementIndex, newElementIndex ->

        }

        engine.setOnRightOutListener { // This action will call when last pagr is slide
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
  ```
  
  ```kotlin
  private fun getDataForOnboarding(): ArrayList<PaperOnboardingPage>? {
        // prepare data
        val scr1 = PaperOnboardingPage(
            "Hotels", // Title text
            "All hotels and hostels are sorted by hospitality rating", // Description text
            Color.parseColor("#678FB4"), // Background color
            R.drawable.hotels, // Main image drawable
            R.drawable.key // Indicator image
        )

        val elements =
            ArrayList<PaperOnboardingPage>()
        elements.add(scr1) // Add Scree1 to Paper Onboarding
        return elements
    }
  ```
 # Layout file will be like this
 
 ``` xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:background="@color/colorWhite"
    tools:context=".MainActivity">


    <RelativeLayout
        android:id="@+id/onboardingRootView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!-- BG COLORS CONTAINER -->
        <FrameLayout
            android:id="@+id/onboardingBackgroundContainer"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

        <!-- MAIN LAYOUT SECTION -->
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginBottom="65dp"
            android:animateLayoutChanges="true"
            android:clipChildren="false">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_centerHorizontal="true"
                android:layout_marginLeft="30dp"
                android:layout_marginRight="30dp"
                android:clipChildren="false"
                android:clipToPadding="false"
                android:orientation="vertical">

                <!-- ICON CONTAINER -->
                <FrameLayout
                    android:id="@+id/onboardingContentIconContainer"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_horizontal"
                    android:clipChildren="false"
                    android:clipToPadding="false" />

                <!-- TEXT CONTAINER -->
                <FrameLayout
                    android:id="@+id/onboardingContentTextContainer"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_horizontal"
                    android:layout_marginTop="30dp"
                    android:clipChildren="false"
                    android:clipToPadding="false" />

            </LinearLayout>

        </RelativeLayout>

        <!-- PAGER ICONS CONTAINER -->
        <LinearLayout
            android:id="@+id/onboardingPagerIconsContainer"
            android:layout_width="wrap_content"
            android:layout_height="40dp"
            android:layout_alignParentBottom="true"
            android:layout_gravity="center_horizontal|bottom"
            android:layout_marginBottom="25dp"
            android:baselineAligned="false"
            android:gravity="center_vertical"
            android:orientation="horizontal" />


    </RelativeLayout>


</layout>
```
  
# Normal Introduction

In our Activity onCreate method we can add the introduction pages as slides like below

```kotlin
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
 ```
 
 Here each slide is one page in viewpager
 
 # IntroductionFragment
 
You can use the IntroductionFragment if you just want to customize title, description, image and colors. That's the suggested  approach if you want to create a quick intro:

```kotlin

addSlide(IntroductionFragment.newInstance(
                "Hotels", // Title Text
                "All hotels and hostels are sorted by hospitality rating", // Description Text
                imageDrawable = R.drawable.hotels, // Main Image drawable
                backgroundColor = Color.parseColor("#678FB4"), // Background color
                titleColor = Color.WHITE, // Title text color
                descriptionColor = Color.WHITE, // Description text color
                titleTypefaceFontRes = R.font.hind_bold, // Title text font
                descriptionTypefaceFontRes = R.font.hind_light // Description text font
            ))
 ```
All the parameters are optional, so you're free to customize your slide as you wish.

If you need to programmatically create several slides you can also use the SliderPage class. This class can be passed to IntroductionFragment.newInstance(sliderPage: SliderPage) that will create a new slide starting from that instance.



# Lottie Animation Introduction

In our Activity onCreate method we can add the introduction pages as slides like below

```kotlin
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
               
 ```
 
 Here each slide is one page in viewpager
 
 # IntroductionLottieAnimationFragment
 
You can use the IntroductionLottieAnimationFragment if you just want to customize title, description, animation and colors. That's the suggested  approach if you want to create a quick intro:

```kotlin

addSlide(
            IntroductionLottieAnimationFragment.newInstance(
                "Hotels", // Title text
                "All hotels and hostels are sorted by hospitality rating", // Description text
                lottieAnimationUrl = "https://assets2.lottiefiles.com/packages/lf20_d2k0co.json", // lottie Animation url
                backgroundColor = Color.parseColor("#678FB4"), // Background color
                titleColor = Color.WHITE, // Title text color
                descriptionColor = Color.WHITE, // Description text color
                titleTypefaceFontRes = R.font.hind_bold, // Title text font
                descriptionTypefaceFontRes = R.font.hind_light // Description text font
            )
        )
 ```
All the parameters are optional, so you're free to customize your slide as you wish.

# General / common things in Library

# IntroductionCustomLayoutFragment

If you need further control on the customization of your slide, you can use the IntroductionCustomLayoutFragment. This will allow you pass your custom Layout Resource file:

```kotlin
IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout1)

```
Here *introduction_custom_layout1* is a layout which can created by developer by his/her own

This allows you to achieve complex layout and include your custom logic in the Introduction.

# Customized Introduction 

We can add any of the above slide - IntroductionLottieAnimationFragment ,IntroductionFragment, IntroductionCustomLayoutFragment or mix of all three like below

```kotlin
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
  ```

# Indicators

This lib supports two indicators to show the progress to the user:

* `DotIndicatorController` represented with a list of Dot (the default)
* `ProgressIndicatorController` represented with a progress bar.

Moreover, you can supply your own indicator by providing an implementation of the `IndicatorController` interface.

You can customize the indicator with the following API on the `AppIntro` class:

```kotlin
// Toggle Indicator Visibility                
isIndicatorEnabled = true

// Change Indicator Color 
setIndicatorColor(
    selectedIndicatorColor = getColor(R.color.red),
    unselectedIndicatorColor = getColor(R.color.blue)
)

// Switch from Dotted Indicator to Progress Indicator
setProgressIndicator()

// Supply your custom `IndicatorController` implementation
indicatorController = MyCustomIndicator(/* initialize me */)
```

If you don't specify any customization, a `DotIndicatorController` will be shown.

We can customize the skip , next and button options in the library

```kotlin

/**
     * Override skip text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        val skipText = findViewById<TextView>(R.id.skip)
        skipText.text = text
    }


    /**
     * Override skip text typeface
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.skip)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipTextColor(@ColorInt colorSkipButton: Int) {
        val skip = findViewById<TextView>(R.id.skip)
        skip.setTextColor(colorSkipButton)
    }






    /**
     * Override done text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setDoneTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.done)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    fun setDoneTextColor(@ColorInt colorDoneText: Int) {
        val doneText = findViewById<TextView>(R.id.done)
        doneText.setTextColor(colorDoneText)
    }





    /**
     * Override done text
     *
     * @param text your text
     */
    fun setNextText(text: CharSequence?) {
        val nextText = findViewById<TextView>(R.id.next)
        nextText.text = text
    }

    /**
     * Override done text typeface
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setNextTextTypeface(@FontRes typeface: Int) {
        val view = findViewById<TextView>(R.id.next)
        TypefaceContainer(null, typeface).applyTo(view)
    }

    /**
     * Override done button text color
     *
     * @param colorNextText your color resource
     */
    fun setNextTextColor(@ColorInt colorNextText: Int) {
        val nextText = findViewById<TextView>(R.id.next)
        nextText.setTextColor(colorNextText)
    }
```

And we can change the skip , next and button resources as well

```kotlin

/**
     * Override Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
    }

    /**
     * Override Next button drawable
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
        nextImageButton.setImageDrawable(imageNextButton)
    }


    /**
     * Override Done button drawable
     * @param imageDoneButton your drawable resource
     */
    fun setImageDoneButton(imageDoneButton: Drawable) {
        doneImageButton.setImageDrawable(imageDoneButton)
    }
```

When user click on skip or done button following callbacks method will get invoke in Activity

```kotlin
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
```
