//package com.everfittest.android.test
//
//import com.everfittest.android.di.modules.NavigatorModule
//import com.everfittest.android.ui.screens.MainNavigator
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.android.components.FragmentComponent
//
//@Module
//@TestInstallIn(
//    components = [FragmentComponent::class],
//    replaces = [NavigatorModule::class]
//)
//object TestNavigatorModule {
//    val mockMainNavigator = mockk<MainNavigator>()
//
//    @Provides
//    fun provideMainNavigator() = mockMainNavigator
//}
