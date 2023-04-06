package com.everfittest.android.ui.base

/**
 * An interface provide abstract commitments for the implemented class
 * from [BaseActivity], with the [BaseViewModel]
 *
 * These methods are set to go well with the Lifecycle of the [BaseActivity],
 * so developers don't have to worry about the basic setups,
 * which could produce conflicts with the Activity's lifecycle
 *
 * See more detail in each function.
 */
interface BaseActivityCallbacks {

    /**
     * The initial callback where you want to place your initialize functions that trigger
     * the setup block for the ViewModel.
     *
     * This method usually get called only ONCE during the time the Activity is created.
     * Ideally, you would want to place the network calls, api requests in here.
     *
     * This is called right after [BaseActivity.onCreate] so we should NOT implement or place
     * view events functions here.
     */
    fun initViewModel()

    /**
     * The initial callback where you want to place your setup view components functions.
     *
     * This method usually get called multiple times, whenever the Activity view is being created/re-created.
     * Ideally, you would want to setup your RecyclerView, ViewPager here (without the data involvement).
     *
     * This is called right after [BaseActivity.onCreate]
     */
    fun setupView()

    /**
     * The initial callback where you want to place your view events functions.
     *
     * This method usually get called multiple times, whenever the Activity view is being created/re-created.
     * Ideally, you would want to setup your input events like:
     * onClick, onPageChanged, onTextChanged here.
     *
     * This is called right after [BaseActivity.onCreate]
     */
    fun bindViewEvents()

    /**
     * The initial callback where you want to place your view events functions.
     *
     * This method usually get called multiple times, whenever the Activity view is being created/re-created.
     * Ideally, you would want to setup the data binding from ViewModel to View here.
     *
     * This is called right after [BaseActivity.onCreate]
     */
    fun bindViewModel()
}
