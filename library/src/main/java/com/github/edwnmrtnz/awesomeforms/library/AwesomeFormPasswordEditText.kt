package com.github.edwnmrtnz.awesomeforms.library

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE

/**
 * Created by edwinmartinez on July 31, 2019
 */

@Styleable("AwesomeFormPasswordEditText")
class AwesomeFormPasswordEditText (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val tvFieldLabel by lazy { findViewById<AppCompatTextView>(R.id.tvFieldLabelTitle) }
    private val tlField by lazy { findViewById<TextInputLayout>(R.id.tlField) }
    private val tvAssistiveText by lazy { findViewById<AppCompatTextView>(R.id.tvAssistiveText) }

    @StyleableChild(R2.styleable.AwesomeFormPasswordEditText_fieldStyle)
    internal val etField by lazy { findViewById<TextInputEditText>(R.id.etField) }

    private var isErrorEnabled = false
    private var assistiveText : String? = null

    init {
        View.inflate(context, R.layout.awesomeform_password_edittext, this)
        style(attrs)
        setTextAppearance(R.style.AwesomeForm_EditText)
        textChangeListener()
    }

    private fun textChangeListener() {
        etField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(isErrorEnabled) {
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
        this.tlField.endIconMode = if(enabled) END_ICON_PASSWORD_TOGGLE else END_ICON_NONE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_fieldLabel)
    fun setFieldLabel(fieldLabel : String) {
        this.tvFieldLabel.text = fieldLabel
        this.tvFieldLabel.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_fieldLabelTextColor)
    fun setFieldLabelTextColor(fieldLabelTextColor : Int) {
        this.tvFieldLabel.setTextColor(fieldLabelTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_assistiveText)
    fun setAssistiveText(assistiveText : String) {
        this.assistiveText = assistiveText
        this.tvAssistiveText.text = assistiveText
        this.tvAssistiveText.visibility = View.VISIBLE
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_assistiveTextColor)
    fun setAssistiveTextColor(assistiveTextColor: Int) {
        this.tvAssistiveText.setTextColor(assistiveTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_placeholderText)
    fun setPlaceHolderText(placeHolderText : String) {
        this.etField.hint = placeHolderText
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_placeholderTextColor)
    fun setPlaceHolderTextColor(placeHolderTextColor : Int) {
        this.etField.setHintTextColor(placeHolderTextColor)
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_imeOptions)
    fun setImeOptions(imeOptions : Int) {
        this.etField.imeOptions = imeOptions
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_inputType)
    fun setInputType(inputType : Int) {
        this.etField.inputType = inputType
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_maxLines)
    fun setMaxLines(maxLine : Int) {
        this.etField.maxLines = maxLine
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_maxLength)
    fun setMaxLength(maxLength : Int) {
        this.etField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_focusable)
    fun setIsFocusable(isFocusable : Boolean) {
        this.etField.isFocusable = isFocusable
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_focusableInTouchMode)
    fun setIsFocusableInTouchMode(isFocusableInTouchMode : Boolean) {
        this.etField.isFocusableInTouchMode = isFocusableInTouchMode
    }

    @Attr(R2.styleable.AwesomeFormPasswordEditText_android_clickable)
    fun setIsClickable(isClickable : Boolean) {
        this.etField.isClickable = isClickable
    }

    fun removeError() {
        isErrorEnabled = false
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_hintColor))
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.material_textinputlayout_box_color)

        if(assistiveText != null) {
            tvAssistiveText.visibility = View.VISIBLE
            tvAssistiveText.text = assistiveText
        } else {
            tvAssistiveText.visibility = View.GONE
        }
        tlField.error = null

    }

    fun setError(errorMessage : String) {
        isErrorEnabled = true
        tvFieldLabel.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
        tvAssistiveText.visibility = View.VISIBLE
        tvAssistiveText.setTextColor(ContextCompat.getColor(context, R.color.AwesomeForm_color_error))
        tvAssistiveText.text = errorMessage
        tlField.boxStrokeColor = ContextCompat.getColor(context, R.color.AwesomeForm_color_error)
        tlField.error = " "
        tlField.getChildAt(1).visibility = View.GONE
        tlField.errorIconDrawable = null
    }

    fun getTextInputLayout() = tlField

    fun getEditText() = etField

    fun setText(text : String) = etField.setText(text)

    fun getText() = etField.text.toString()


    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()!!
        return  SavedState(superState, getText())
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(state.superState)
        etField.setText(savedState.text)
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        super.dispatchThawSelfOnly(container)
    }

    internal class SavedState : BaseSavedState {

        var text : String = ""

        constructor(source : Parcel) : super(source) {
            text = source.readByte().toString()
        }

        constructor(superState: Parcelable, text : String) : super(superState) {
            this.text = text
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeString(text)
        }


        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(parcel: Parcel): SavedState {
                    return SavedState(parcel)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }

    }
}