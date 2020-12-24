package com.example.testapplication.widgets.headingtext

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import com.example.testapplication.R

class HeadingTextView : androidx.appcompat.widget.AppCompatTextView {
    /**
     * Constructor to create view.
     */
    constructor(context: Context) : this(context, null)

    /**
     * Constructor to create view.
     */
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /**
     * Constructor to create view.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @Override
    override fun setText(text: CharSequence?, type: BufferType?) {

        val spannableString = SpannableString(text);
        spannableString.setSpan(
            TextAppearanceSpan(context, R.style.TextLargest_White),
            0, 10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (text != null && text.length > 10) {
            spannableString.setSpan(
                TextAppearanceSpan(context, R.style.TextLargest_HighLight),
                11, text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        super.setText(spannableString, type)
    }
}