package com.hb.introduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.hb.introduction.internal.LogHelper
import com.hb.introduction.internal.TypefaceContainer

internal const val ARG_LA_TITLE = "title"
internal const val ARG_LA_TITLE_TYPEFACE = "title_typeface"
internal const val ARG_LA_TITLE_TYPEFACE_RES = "title_typeface_res"
internal const val ARG_LA_DESC = "desc"
internal const val ARG_LA_DESC_TYPEFACE = "desc_typeface"
internal const val ARG_LA_DESC_TYPEFACE_RES = "desc_typeface_res"
internal const val ARG_LA_ANIMATION_URL = "la_animation_url"
internal const val ARG_LA_ANIMATION_RESOURCE = "la_animation_resource"
internal const val ARG_LA_BG_COLOR = "bg_color"
internal const val ARG_LA_TITLE_COLOR = "title_color"
internal const val ARG_LA_DESC_COLOR = "desc_color"
internal const val ARG_LA_BG_DRAWABLE = "bg_drawable"

abstract class IntroductionLottieAnimationBaseFragment : Fragment(), SlideSelectionListener,
    SlideBackgroundColorHolder {

    private val logTAG = LogHelper.makeLogTag(IntroductionLottieAnimationBaseFragment::class.java)

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var animationResource: Int = 0
    private var bgDrawable: Int = 0

    private var titleColor: Int = 0
    private var descColor: Int = 0
    final override var defaultBackgroundColor: Int = 0
        private set

    private var title: String? = null
    private var description: String? = null
    private var lottieAnimationUrl: String? = null
    private var titleTypeface: TypefaceContainer? = null
    private var descTypeface: TypefaceContainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        val args = arguments
        if (args != null && args.size() != 0) {
            title = args.getString(ARG_LA_TITLE)
            description = args.getString(ARG_LA_DESC)
            lottieAnimationUrl = args.getString(ARG_LA_ANIMATION_URL)
            animationResource = args.getInt(ARG_LA_ANIMATION_RESOURCE)
            bgDrawable = args.getInt(ARG_LA_BG_DRAWABLE)

            val argsTitleTypeface = args.getString(ARG_LA_TITLE_TYPEFACE)
            val argsDescTypeface = args.getString(ARG_LA_DESC_TYPEFACE)
            val argsTitleTypefaceRes = args.getInt(ARG_LA_TITLE_TYPEFACE_RES)
            val argsDescTypefaceRes = args.getInt(ARG_LA_DESC_TYPEFACE_RES)
            titleTypeface = TypefaceContainer(argsTitleTypeface, argsTitleTypefaceRes)
            descTypeface = TypefaceContainer(argsDescTypeface, argsDescTypefaceRes)

            defaultBackgroundColor = args.getInt(ARG_LA_BG_COLOR)
            titleColor = args.getInt(ARG_LA_TITLE_COLOR, 0)
            descColor = args.getInt(ARG_LA_DESC_COLOR, 0)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            title = savedInstanceState.getString(ARG_LA_TITLE)
            description = savedInstanceState.getString(ARG_LA_DESC)
            lottieAnimationUrl = savedInstanceState.getString(ARG_LA_ANIMATION_URL)
            animationResource = savedInstanceState.getInt(ARG_LA_ANIMATION_RESOURCE)

            titleTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_LA_TITLE_TYPEFACE),
                savedInstanceState.getInt(ARG_LA_TITLE_TYPEFACE_RES, 0)
            )
            descTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_LA_DESC_TYPEFACE),
                savedInstanceState.getInt(ARG_LA_DESC_TYPEFACE_RES, 0)
            )

            defaultBackgroundColor = savedInstanceState.getInt(ARG_LA_BG_COLOR)
            bgDrawable = savedInstanceState.getInt(ARG_LA_BG_DRAWABLE)
            titleColor = savedInstanceState.getInt(ARG_LA_TITLE_COLOR)
            descColor = savedInstanceState.getInt(ARG_LA_DESC_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        val titleText = view.findViewById<TextView>(R.id.title)
        val descriptionText = view.findViewById<TextView>(R.id.description)
        val lottieAnimationView = view.findViewById<LottieAnimationView>(R.id.animation_view)
        val mainLayout = view.findViewById<ConstraintLayout>(R.id.main)

        titleText.text = title
        descriptionText.text = description
        if (titleColor != 0) {
            titleText.setTextColor(titleColor)
        }
        if (descColor != 0) {
            descriptionText.setTextColor(descColor)
        }
        titleTypeface?.applyTo(titleText)
        descTypeface?.applyTo(descriptionText)



        if (lottieAnimationUrl != null && !lottieAnimationUrl!!.isEmpty()) {
            lottieAnimationView.setAnimationFromUrl(lottieAnimationUrl)
        } else {
            lottieAnimationView.setAnimation(animationResource)
        }


        lottieAnimationView.playAnimation()
        lottieAnimationView.repeatCount = LottieDrawable.INFINITE
        if (bgDrawable != 0) {
            mainLayout?.setBackgroundResource(bgDrawable)
        } else {
            mainLayout?.setBackgroundColor(defaultBackgroundColor)
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_LA_BG_DRAWABLE, bgDrawable)
        outState.putString(ARG_LA_TITLE, title)
        outState.putString(ARG_LA_DESC, description)
        outState.putString(ARG_LA_ANIMATION_URL, lottieAnimationUrl)
        outState.putInt(ARG_LA_ANIMATION_RESOURCE, animationResource)
        outState.putInt(ARG_LA_BG_COLOR, defaultBackgroundColor)
        outState.putInt(ARG_LA_TITLE_COLOR, titleColor)
        outState.putInt(ARG_LA_DESC_COLOR, descColor)
        if (titleTypeface != null) {
            outState.putString(ARG_LA_TITLE_TYPEFACE, titleTypeface?.typeFaceUrl)
            outState.putInt(ARG_LA_TITLE_TYPEFACE_RES, titleTypeface?.typeFaceResource ?: 0)
        }
        if (descTypeface != null) {
            outState.putString(ARG_LA_DESC_TYPEFACE, descTypeface?.typeFaceUrl)
            outState.putInt(ARG_LA_DESC_TYPEFACE_RES, descTypeface?.typeFaceResource ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onSlideDeselected() {
        LogHelper.d(logTAG, "Slide $title has been deselected.")
    }

    override fun onSlideSelected() {
        LogHelper.d(logTAG, "Slide $title has been selected.")
    }

    override fun setBackgroundColor(@ColorInt backgroundColor: Int) {
        view?.findViewById<ConstraintLayout>(R.id.main)?.setBackgroundColor(backgroundColor)
    }
}
