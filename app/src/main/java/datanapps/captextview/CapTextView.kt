package datanapps.captextview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import datanapps.captextview.databinding.ViewCapTextViewBinding

import java.util.*


@SuppressLint("Recycle")
class CapTextView constructor(
    context: Context,
    readArrayAttributes: AttributeSet?,
    defStyleAttr: Int
) : ConstraintLayout(context, readArrayAttributes, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)


    private var binding = ViewCapTextViewBinding.bind(
        LinearLayout.inflate(
            context,
            R.layout.view_cap_text_view,
            this
        )
    )

    private var isCapText: Boolean = false
    private var capDropNo : Int = 0

    val colorText = TypedValue().also {
        context.theme.resolveAttribute(android.R.attr.textColorPrimary, it, true)
    }.data

    init {
        readArrayAttributes?.let {
            val attrs = context.obtainStyledAttributes(
                readArrayAttributes,
                R.styleable.CapTextView
            )
            val capFont =
                attrs.getResourceId(R.styleable.CapTextView_capFont, R.font.eb_garamond_bold)
            val capTextColor =
                R.color.purple_200



            val bodyFont = attrs.getResourceId(
                R.styleable.CapTextView_bodyTextFont,
                R.font.eb_garamond_regular
            )

            val bodyTextColor = R.color.purple_200




            isCapText = attrs.getBoolean(R.styleable.CapTextView_isCapText, false)
            val text = attrs.getString(R.styleable.CapTextView_android_text)
            capDropNo = attrs.getInt(R.styleable.CapTextView_capsDropNumber, 2)

            binding.tvCap.typeface = ResourcesCompat.getFont(context, capFont)
            binding.tvCap.setTextColor(ContextCompat.getColor(context, capTextColor))

            binding.tvBody.typeface = ResourcesCompat.getFont(context, bodyFont)
            binding.tvBody.setTextColor(ContextCompat.getColor(context, bodyTextColor))


            // set default text with cap text trur
            text?.let {
                isCapText = true
                setHtmlText(it)
            }

        }

    }
     fun setCapTextFont(@NonNull fontId:Int){
        binding.tvCap.typeface = ResourcesCompat.getFont(context, fontId)
    }
     fun setCapTextAttrColor(@NonNull color:Int){
        binding.tvCap.setTextColor(color)
    }

    fun setBodyTextFont(@NonNull fontId:Int){
        binding.tvBody.typeface = ResourcesCompat.getFont(context, fontId)
        binding.tvBody2.typeface = ResourcesCompat.getFont(context, fontId)
    }



    fun setHtmlText(text: String, isCapText:Boolean) {

        if(text.isNotEmpty()) {
            if (isCapText) {
                binding.tvCap.visibility = VISIBLE
                setCapText(text)

            } else {
                binding.tvCap.visibility = GONE
                binding.tvBody.text = text

            }
        }
        else binding.root.visibility = GONE

    }

    fun setHtmlText(text: String) {
        if(text.isNotEmpty()) {
            if (isCapText) {
                binding.tvCap.visibility = VISIBLE
                setCapText(text)
            } else {
                binding.tvCap.visibility = GONE
                binding.tvBody.text = text
            }
        }

    }

    private fun setCapText(formatText: String) {


        if(formatText.isNotEmpty()) {
            val capText = formatText.substring(0, 1).uppercase(Locale.ROOT)
            binding.tvCap.text = capText
            val bounds = Rect()
            val textPaint: Paint = binding.tvCap.getPaint()
            textPaint.getTextBounds(capText, 0, 1, bounds)

            val leftMargin = bounds.width() + 55

            // get all paragraph
            val paragraph = formatText.split("\n")

            val finalSpannable = SpannableStringBuilder("")

            // first paragraph will take space from start rest will be same
            paragraph.filter { it.isNotBlank() }.forEachIndexed { index, str ->

                when(index) {
                    0 -> {
                        val spannableString = SpannableString(formatText.substring(1, str.length))
                        spannableString.setSpan(
                            CapLeadingMarginSpan2(capDropNo, leftMargin),
                            0,
                            spannableString.length,
                            0
                        )

                        binding.tvBody.text = spannableString
                    }
                    1 -> if (str.isNotBlank()) finalSpannable.append("\n${str.trim()}")
                    else -> {
                        if (str.isNotBlank()) finalSpannable.append("\n\n${str.trim()}")
                    }
                }
            }
            binding.tvBody2.visibility = visibility
            binding.tvBody2.text = finalSpannable
        }

    }

    fun setTextScale(sizeInPx: Int) {
        binding.tvBody.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(sizeInPx))
        binding.tvBody2.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(sizeInPx))
    }
}