# IntroductionWrapper - Library used to build application introduction/onboarding with in no time

# Overview

This is an Android Library that helps you build introduction for your App. With this help you create a great onboarding experience.

<img alt="Paper Onboarding" title="Paper Onboarding" src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/paper_onboard.gif" width = "300" height = "633"/>&nbsp;<img alt="Normal Introduction" title="Normal Introduction" src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/nomal_introduction.gif" width="300" height = "633"/>&nbsp;<img alt="Lottie Animation Introduction" title="Lottie Animation Introduction" src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/lottie_introduction.gif" width="300" height
="633">&nbsp;<img alt="Customized Introduction" title="Customized Introduction" src="https://github.com/hbgopikrishna/IntroductionWrapper/blob/master/custom.gif" width="300" height="633">

# Use Case
1. If you want to show some introduction with number of slides/pages in your application then this library is very useful
2. This library is providing you number of options to create app introduction or paper onboarding type

# How it works?

# Paper onboarding

# Dependency

`implementation 'com.ramotion.paperonboarding:paper-onboarding:1.1.3'`

In our Activity onCreate method initialize `PaperOnboardingEngine` and set listeners

```kotlin
val engine = PaperOnboardingEngine(
            findViewById(R.id.onboardingRootView),
            getDataForOnboarding(), // Set OnBoard Pages using PaperOnboardingPage class
            applicationContext
        )

        engine.setOnChangeListener { oldElementIndex, newElementIndex ->

        }

        engine.setOnRightOutListener { // This action will call when last pagr is slide
            
        }
  ```
  In `getDataForOnboarding` method will add data for pages and return elements to intiallization
  
  ```kotlin
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


        val elements =
            ArrayList<PaperOnboardingPage>()
        elements.add(scr1)
        elements.add(scr2)
        return elements
    }
  ```
  
  Use `PaperOnboardingPage` to create page for paper onboarding
  
  ```kotlin
 
        val scr1 = PaperOnboardingPage(
            "Hotels", // Title text
            "All hotels and hostels are sorted by hospitality rating", // Description text
            Color.parseColor("#678FB4"), // Background color
            R.drawable.hotels, // Main image drawable
            R.drawable.key // Indicator image
        )
    
  ```
  
# Normal Introduction

In our Activity (Which extends `IntroductionWithSkipText()` or `IntroductionWithSkipButton()` based upon our requirement) onCreate method we can add the introduction pages as slides like below 

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
 
Here each slide is one page in viewpager and `addSlide` is the method which comes from `IntroductionBaseActivity()` which is used to add our `IntroductionFragment` to the view pager

`IntroductionWithSkipText()` - Is a base class with skip ,next and done buttons will have Text
`IntroductionWithSkipButton()` - Is a base class with skip ,next and done buttons will have Button Drawables
 
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

In our Activity (Which extends `IntroductionWithSkipText()` or `IntroductionWithSkipButton()` based upon our requirement) onCreate method we can add the introduction pages as slides like below

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
     * Override skip text - Is used to update the skip button text
     *
     * @param text your text
     */
    fun setSkipText(text: CharSequence?) {
        
    }


    /**
     * Override skip text typeface - Is used to update the skip button text font
     *
     * @param typeface the typeface to apply to Skip button
     */
    fun setSkipTextTypeface(@FontRes typeface: Int) {
        
    }

    /**
     * Override skip button color - Is used to update the skip button text color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipTextColor(@ColorInt colorSkipButton: Int) {
        
    }






    /**
     * Override done text - Is used to update the Done button text
     *
     * @param text your text
     */
    fun setDoneText(text: CharSequence?) {
        
    }

    /**
     * Override done text typeface - Is used to update the Done button text font
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setDoneTextTypeface(@FontRes typeface: Int) {
        
    }

    /**
     * Override done button text color - Is used to update the Done button text color
     *
     * @param colorDoneText your color resource
     */
    fun setDoneTextColor(@ColorInt colorDoneText: Int) {
       
    }





    /**
     * Override next text - Is used to update the Next button text 
     *
     * @param text your text
     */
    fun setNextText(text: CharSequence?) {
        
    }

    /**
     * Override next text typeface - Is used to update the Next button text font
     *
     * @param typeface the typeface to apply to Done button
     */
    fun setNextTextTypeface(@FontRes typeface: Int) {
       
    }

    /**
     * Override done button text color - Is used to update the Next button text color
     *
     * @param colorNextText your color resource
     */
    fun setNextTextColor(@ColorInt colorNextText: Int) {
        
    }
```

And we can change the skip , next and button resources as well

```kotlin

/**
     * Override Skip button drawable - Is used to update the Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
    }

    /**
     * Override Next button drawable - Is used to update the Next button drawable
     * @param imageNextButton your drawable resource
     */
    fun setImageNextButton(imageNextButton: Drawable) {
    }


    /**
     * Override Done button drawable  - Is used to update the Done button drawable
     * @param imageDoneButton your drawable resource
     */
    fun setImageDoneButton(imageDoneButton: Drawable) {
    }
```

When user click on skip or done button following callbacks method will get invoke in Activity

```kotlin
   public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
    }
```
