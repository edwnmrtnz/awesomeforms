package com.github.edwnmrtnz.awesomeforms.library

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.text.InputFilter
import android.util.*
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.core.widget.doAfterTextChanged
import com.airbnb.paris.annotations.Attr
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.annotations.StyleableChild
import com.airbnb.paris.extensions.style
import com.google.android.material.textfield.TextInputLayout


/**
 * Created by edwinmartinez on July 31, 2019
 *
 * Note: This EditText is using the isHovered state to manipulate the colors of borders around
 * prefix. This is a hack for now as the implementation doesn't support custom color state yet.
 * See here: https://github.com/material-components/material-components-android/blob/master/lib/java/com/google/android/material/textfield/TextInputLayout.java
 */

@Styleable("AwesomeFormPrefixedEditText")
class AwesomeFormPrefixedEditText(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }
    @StyleableChild(R2.styleable.AwesomeFormPrefixedEditText_fieldStyle)
    internal val etField by lazy { findViewById<AppCompatEditText>(R.id.etField) }

    private val tlPrefix by lazy { findViewById<TextInputLayout>(R.id.tlPrefix) }
    private val tvPrefix by lazy { findViewById<AppCompatEditText>(R.id.tvPrefix) }

    private var isErrorEnabled = false
    private var assistiveText: String? = null
    private var errorText : String? = null


    init {
        isSaveEnabled = true
        View.inflate(context, R.layout.awesomeform_prefixed_edittext, this)
        style(attrs)
        setTextAppearance(R.style.AwesomeForm_EditText)

        etField.doAfterTextChanged {
            if (isErrorEnabled) {
                removeError()
            }
        }

        etField.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                if(isErrorEnabled) {
                    tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
                    tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
                } else {
                    tlPrefix.isHovered = true
                    tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
                    tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
                }
                tlPrefix.boxStrokeWidth = tlField.boxStrokeWidthFocused
            } else {
                if(isErrorEnabled) {
                    tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
                    tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
                } else {
                    tlPrefix.isHovered = false
                    tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
                    tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
                }
                tlPrefix.boxStrokeWidth = tlField.boxStrokeWidth
            }

        }
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_prefix)
    fun setPrefix(prefix : String) {
        tvPrefix.setText(prefix)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_textAppearance)
    fun setTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(this.etField, textAppearance)
        TextViewCompat.setTextAppearance(this.tvPrefix, textAppearance)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_drawablePadding)
    fun setDrawablePadding(@Px padding: Float) {
        val dp = convertPixelsToDp(
            padding, context
        ).toInt()
        this.etField.compoundDrawablePadding = dp
    }

    private fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_endIconDrawable)
    fun setEndIconDrawable(drawable: Drawable) {
        this.tlField.setEndIconMode(TextInputLayout.END_ICON_CUSTOM)
        this.tlField.endIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_endIconMode)
    fun setEndIconMode(mode: Int = TextInputLayout.END_ICON_NONE) {
        this.tlField.endIconMode = mode
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_fieldLabel)
    fun setFieldLabel(fieldLabel: String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor: Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_assistiveText)
    fun setAssistiveText(assistiveText: String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText: String) {
        this.etField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor: Int) {
        this.etField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_imeOptions)
    fun setImeOptions(imeOptions: Int) {
        this.etField.imeOptions = imeOptions
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_inputType)
    fun setInputType(inputType: Int) {
        this.etField.inputType = inputType
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_maxLines)
    fun setMaxLines(maxLine: Int) {
        this.etField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_maxLength)
    fun setMaxLength(maxLength: Int) {
        this.etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_focusable)
    fun setIsFocusable(isFocusable: Boolean) {
        this.etField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode: Boolean) {
        this.etField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormPrefixedEditText_android_clickable)
    fun setIsClickable(isClickable: Boolean) {
        this.etField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        errorText = null

        if(etField.hasFocus()) {
            tlPrefix.isHovered = true
            tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
            tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
        } else {
            tlPrefix.isHovered = false
            tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
            tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        }

        setAssistiveTextBasedOnCurrentState()

        tlField.error = null
        tlPrefix.error = null
    }

    private fun setAssistiveTextBasedOnCurrentState() {
        if (!errorText.isNullOrBlank()) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = errorText
            val errorColor = ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
            tvAssistiveText.setTextColor(errorColor)
        } else {
            if (!assistiveText.isNullOrBlank()) {
                tvAssistiveText.visibility = View.VISIBLE
                tvAssistiveText.text = assistiveText
            } else {
                tvAssistiveText.visibility = View.GONE
            }
        }
    }

    fun setError(errorMessage: String) {
        isErrorEnabled = true

        errorText = errorMessage

        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))

        setAssistiveTextBasedOnCurrentState()

        listOf<TextInputLayout>(tlField, tlPrefix).forEach { tl ->
            tl.error = " "
            tl.getChildAt(1).visibility = View.GONE
            tl.errorIconDrawable = null
        }
    }


    fun getEditText() = etField

    fun getTextInputLayout() = tlField

    fun setText(text: String) = etField.setText(text)

    fun getPrefix() = tvPrefix

    fun getText() = etField.text.toString()

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            childrenStates = saveChildViewStates()
            isErrorEnabled = if(this@AwesomeFormPrefixedEditText.isErrorEnabled) 1 else 0
            assistiveText = this@AwesomeFormPrefixedEditText.assistiveText
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        when (state) {
            is SavedState -> {
                super.onRestoreInstanceState(state.superState)
                state.childrenStates?.let { restoreChildViewStates(it) }
                this.isErrorEnabled = true.takeIf { state.isErrorEnabled == 1 } ?: false
                this.assistiveText = state.assistiveText
                this.errorText = state.errorText
            }
            else -> super.onRestoreInstanceState(state)
        }
        restore()
    }

    private fun restore() {
        setAssistiveTextBasedOnCurrentState()
        if(isErrorEnabled) {
            setError(errorText ?: "")
        } else {
            removeError()
        }
    }
    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchThawSelfOnly(container)
    }

    internal class SavedState : BaseSavedState {

        internal var childrenStates: SparseArray<Parcelable>? = null
        internal var isErrorEnabled = 0 // 0 false, 1 true
        internal var assistiveText : String? = null
        internal var errorText : String? = null

        constructor(superState: Parcelable?) : super(superState)

        constructor(source: Parcel) : super(source) {
            childrenStates = source.readSparseArray(javaClass.classLoader)
            isErrorEnabled = source.readInt()
            assistiveText = source.readString()
            errorText = source.readString()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeSparseArray(childrenStates)
            out.writeInt(isErrorEnabled)
            out.writeString(assistiveText)
            out.writeString(errorText)
        }

        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(p0: Parcel): SavedState {
                    return SavedState(p0)
                }

                override fun newArray(p0: Int): Array<SavedState?> {
                    return arrayOfNulls(p0)
                }

            }
        }
    }

    @ColorInt
    fun getThemeColor(
        context: Context,
        @AttrRes attributeColor: Int
    ): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(attributeColor, value, true)
        return value.data
    }
}