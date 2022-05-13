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
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.airbnb.paris.annotations.Attr
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.annotations.StyleableChild
import com.airbnb.paris.extensions.style
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE

/**
 * Created by edwinmartinez on July 31, 2019
 */

@Styleable("AwesomeFormPasswordEditText")
class AwesomeFormPasswordEditText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }

    @StyleableChild(R2.styleable.AwesomeFormPasswordEditText_fieldStyle)
    internal val etField by lazy { findViewById<TextInputEditText>(R.id.etField) }

    private var isErrorEnabled = false
    private var assistiveText: String? = null

    init {
        isSaveEnabled = true
        View.inflate(context, R.layout.awesomeform_password_edittext, this)
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

    private fun handleChangeWhenInNotFocus(context: Context) {
        if (isErrorEnabled) {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_color_error
                )
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_color_error
                )
            )
        } else {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_hintColor
                )
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_hintColor
                )
            )
        }
    }

    private fun handleChangeWhenInFocus(context: Context) {
        if (isErrorEnabled) {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_color_error
                )
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_color_error
                )
            )
        } else {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_focused_color
                )
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_focused_color
                )
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

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_textAppearance)
    fun setTextAppearance(@StyleRes textAppearance: Int) {
        TextViewCompat.setTextAppearance(this.etField, textAppearance)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_startIconDrawable)
    fun setStartIconDrawable(drawable: Drawable) {
        this.tlField.startIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_endIconDrawable)
    fun setEndIconDrawable(drawable: Drawable) {
        this.tlField.endIconDrawable = drawable
        this.setEndIconMode(TextInputLayout.END_ICON_CUSTOM)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_endIconMode)
    fun setEndIconMode(mode: Int = END_ICON_PASSWORD_TOGGLE) {
        this.tlField.endIconMode = mode
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_passwordToggleDrawable)
    fun setPasswordDrawableToggle(drawable: Drawable) {
        this.tlField.endIconDrawable = drawable
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_passwordToggleEnabled)
    fun setPasswordDrawableToggle(enabled: Boolean) {
        this.tlField.endIconMode = if (enabled) END_ICON_PASSWORD_TOGGLE else END_ICON_NONE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_fieldLabel)
    fun setFieldLabel(fieldLabel: String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor: Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_assistiveText)
    fun setAssistiveText(assistiveText: String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText: String) {
        this.etField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor: Int) {
        this.etField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_imeOptions)
    fun setImeOptions(imeOptions: Int) {
        this.etField.imeOptions = imeOptions
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_inputType)
    fun setInputType(inputType: Int) {
        this.etField.inputType = inputType
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_maxLines)
    fun setMaxLines(maxLine: Int) {
        this.etField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_maxLength)
    fun setMaxLength(maxLength: Int) {
        this.etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_focusable)
    fun setIsFocusable(isFocusable: Boolean) {
        this.etField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode: Boolean) {
        this.etField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_clickable)
    fun setIsClickable(isClickable: Boolean) {
        this.etField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        if(etField.hasFocus()) {
            tvFieldLabel.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_focused_color
                )
            )
            tvAssistiveText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.AwesomeForm_focused_color
                )
            )
        }

        if (assistiveText != null) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = assistiveText
        } else {
            tvAssistiveText.visibility = View.GONE
        }
        tlField.error = null

    }

    fun setError(errorMessage: String) {
        isErrorEnabled = true
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.AwesomeForm_color_error
            )
        )
        tvAssistiveText.text = errorMessage
        tlField.error = " "
        tlField.getChildAt(1).visibility = View.GONE
        tlField.errorIconDrawable = null
    }

    fun getTextInputLayout() = tlField

    fun getEditText() = etField

    fun setText(text: String) = etField.setText(text)

    fun getText() = etField.text.toString()


    override fun onSaveInstanceState(): Parcelable? {
        return SavedState(super.onSaveInstanceState()).apply {
            childrenStates = saveChildViewStates()
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        when (state) {
            is SavedState -> {
                super.onRestoreInstanceState(state.superState)
                state.childrenStates?.let { restoreChildViewStates(it) }
            }
            else -> super.onRestoreInstanceState(state)
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

        constructor(superState: Parcelable?) : super(superState)

        constructor(source: Parcel) : super(source) {
            childrenStates = source.readSparseArray(javaClass.classLoader)
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeSparseArray(childrenStates)
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