package com.github.edwnmrtnz.awesomeforms.library

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.airbnb.paris.annotations.Attr
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.annotations.StyleableChild
import com.airbnb.paris.extensions.style
import com.github.edwnmrtnz.awesomeforms.library.R2.styleable.AwesomeFormNormalEditText_fieldStyle
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by edwinmartinez on July 31, 2019
 */

@Styleable("AwesomeFormNormalEditText")
class AwesomeFormNormalEditText(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }

    @StyleableChild(R2.styleable.AwesomeFormNormalEditText_fieldStyle)
    internal val etField by lazy { findViewById<AppCompatEditText>(R.id.etField) }

    private var isErrorEnabled = false
    private var assistiveText: String? = null
    private var errorText : String? = null

    init {
        isSaveEnabled = true
        View.inflate(context, R.layout.awesomeform_normal_edittext, this)
        style(attrs)
        setTextAppearance(R.style.AwesomeForm_EditText)
        textChangeListener()
        etField.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                handleChangeWhenInFocus(context)
            } else {
                handleChangeWhenInNotFocus(context)
            }
        }
    }

    private fun handleChangeWhenInFocus(context: Context) {
        if (isErrorEnabled) {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
            )
        } else {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_focused_color)
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_focused_color)
            )
        }
    }

    private fun handleChangeWhenInNotFocus(context: Context) {
        if (isErrorEnabled) {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
            )
        } else {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_hintColor)
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(context, R.color.AwesomeForm_hintColor)
            )
        }
    }

    private fun textChangeListener() {
        etField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (isErrorEnabled) {
                    removeError()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Ignore
            }
        })
    }
    @Attr(R2.styleable.AwesomeFormNormalEditText_android_textAppearance)
    fun setTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(etField, textAppearance)
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_disableEmoji)
    fun disableEmoji(disableEmoji: Boolean = false) {
        if(disableEmoji) this.etField.filters = EmojiFilter.filter
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_startIconDrawable)
    fun setStartIconDrawable(drawable: Drawable) {
        this.tlField.startIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_endIconDrawable)
    fun setEndIconDrawable(drawable: Drawable) {
        this.tlField.setEndIconMode(TextInputLayout.END_ICON_CUSTOM)
        this.tlField.endIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_endIconMode)
    fun setEndIconMode(mode: Int = TextInputLayout.END_ICON_NONE) {
        this.tlField.endIconMode = mode
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_fieldLabel)
    fun setFieldLabel(fieldLabel: String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor: Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_assistiveText)
    fun setAssistiveText(assistiveText: String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText: String) {
        this.etField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor: Int) {
        this.etField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_imeOptions)
    fun setImeOptions(imeOptions: Int) {
        this.etField.imeOptions = imeOptions
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_inputType)
    fun setInputType(inputType: Int) {
        this.etField.inputType = inputType
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_maxLines)
    fun setMaxLines(maxLine: Int) {
        this.etField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_maxLength)
    fun setMaxLength(maxLength: Int) {
        this.etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_focusable)
    fun setIsFocusable(isFocusable: Boolean) {
        this.etField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode: Boolean) {
        this.etField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormNormalEditText_android_clickable)
    fun setIsClickable(isClickable: Boolean) {
        this.etField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        errorText = null

        if(etField.hasFocus()) {
            tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
            tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_focused_color))
        } else {
            tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
            tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        }

        setAssistiveTextBasedOnCurrentState()

        tlField.error = null

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

        tlField.error = " "
        tlField.getChildAt(1).visibility = View.GONE
        tlField.errorIconDrawable = null
    }

    fun getEditText() = etField

    fun getTextInputLayout() = tlField

    fun setText(text: String) = etField.setText(text)

    fun getText() = etField.text.toString()

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            childrenStates = saveChildViewStates()
            isErrorEnabled = if(this@AwesomeFormNormalEditText.isErrorEnabled) 1 else 0
            assistiveText = this@AwesomeFormNormalEditText.assistiveText
            errorText = this@AwesomeFormNormalEditText.errorText
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
}