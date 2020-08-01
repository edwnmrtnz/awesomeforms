# awesomeforms
Awesome forms using material component!

[![](https://jitpack.io/v/edwnmrtnz/awesomeforms.svg)](https://jitpack.io/#edwnmrtnz/awesomeforms)

### Sample
![Sample](https://github.com/edwnmrtnz/awesomeforms/blob/develop/docs/sample1.jpg)


### Prerequisites
Add this in your root build.gradle file (not your module build.gradle file):

```gradle
allprojects {
    repositories {
	    ...
	    maven { url 'https://jitpack.io' }
	}
}
```
### Dependency
Add this to your module's build.gradle file (make sure the version matches the JitPack badge above):
```gradle
dependencies {
	implementation 'com.github.edwnmrtnz:awesomeforms:Tag'
}
```
### Usage
You can use the following extra attributes for styling aside from the defaults offered by standard edittext.
```xml
<attr name="fieldLabel"/>
<attr name="assistiveText"/>
<attr name="fieldLabelTextColor"/>
<attr name="assistiveTextColor"/>
<attr name="placeholderText"/>
<attr name="placeholderTextColor"/>
<attr name="fieldStyle"/>
```
There are four types of edit text you can use
```kotlin
AwesomeFormNormalEditText
AwesomeFormPasswordEditText
AwesomeFormPhonePrefixEditText
AwesomeFormSpinnerEditText
```

Using theme is pretty simple. You can see the sample inside app module.


### Note
You may override the following colors

```xml
<color name="AwesomeForm_textColor">#333333</color>
<color name="AwesomeForm_color_cursor">#333333</color>
<color name="AwesomeForm_color_error">#ff2a4e</color>
<color name="AwesomeForm_focus_color">@color/colorPrimary</color>
```



