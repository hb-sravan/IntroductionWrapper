# IntroductionWrapper - Library used to build application introduction/onboarding with in no time

# Overview

This is an Android Library that helps you build introduction for your App. With this help you create a great onboarding experience.

# Use Case
1. If you want to show some introduction with number of slides/pages in your application then this library is very useful
2. This library is providing you number of options to create app introduction or paper onboarding type

# How it works?

This library supports the application introduction for Activity and Fragment.

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
        
 ```
 
 Here each slide is one page in viewpager
 
 # IntroductionFragment
 
 You can use the IntroductionFragment if you just want to customize title, description, image and colors. That's the suggested  approach if you want to create a quick intro:

```kotlin

addSlide(IntroductionFragment.newInstance(
                "Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                imageDrawable = R.drawable.hotels,
                backgroundColor = Color.parseColor("#678FB4"),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                titleTypefaceFontRes = R.font.hind_bold,
                descriptionTypefaceFontRes = R.font.hind_light
            ))
 ```
All the parameters are optional, so you're free to customize your slide as you wish.

If you need to programmatically create several slides you can also use the SliderPage class. This class can be passed to IntroductionFragment.newInstance(sliderPage: SliderPage) that will create a new slide starting from that instance.

# IntroductionCustomLayoutFragment

If you need further control on the customization of your slide, you can use the IntroductionCustomLayoutFragment. This will allow you pass your custom Layout Resource file:

```kotlin
IntroductionCustomLayoutFragment.newInstance(R.layout.introduction_custom_layout1)

```

This allows you to achieve complex layout and include your custom logic in the Introduction.
