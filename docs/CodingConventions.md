# Coding conventions
Describe the coding conventions in android

# Principle
As a general rule, it shall comply with the [Android Official Kotlin Style Guide] (https://developer.android.com/kotlin/style-guide?hl=ja).
However, the coding convention described below has the highest priority.

# Conventions

# Package Name
--All the package name should be use alphabets lowercase.

# ViewModel
--All ViewModel must Inherit baseViewModel --ObservableField should be prohibited --we use MVVM data binding --one way binding use OneWayLiveData, for value just need display. example:TextView --two way binding use MutableLiveData, for value need display and edit.example:EditText

# View
--Basically, do not write any business logic in view side(both Fragment and layout.mxl) --If you need business logic, write it in ViewModel or domain layer(basically write business logic in domain layer) --Only display logic such as style change can be write it in View side --if have same layout appeared twice must abstract as a CustomView

# View (Fragment)
--In principle, inherit baseFragment --Basically, do not write business logic --Basically not to keep stored property

# View (layout)
--In principle, use androidx.constraintlayout.widget.ConstraintLayout --don’t nesting as much as possible --Basically, do not write business logic --If you need business logic, write it in ViewModel or domain layer(basically write business logic in domain layer) --Only display logic such as style change can be described


