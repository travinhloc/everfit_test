package com.everfittest.android.ui.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    open fun onEndlessAtTop() {}
    open fun onEndlessAtBottom(){}
    open fun onScrolledBottom(isScrolled: Boolean){}
    open fun onScrollUp(){}
    open fun onScrollDown(){}

    private var lastVisibleItemPosition = -1
    private var firstVisibleItemPosition = -1
    private var scrolledY = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            // Scrolling up
            onScrollUp()
        } else {
            // Scrolling down
            onScrollDown()
        }
        when (val layoutManager = recyclerView.layoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            }

        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val layoutManager = recyclerView.layoutManager
        val totalItemCount = layoutManager!!.itemCount
        val visibleItemCount = layoutManager.childCount
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (visibleItemCount > 0 && lastVisibleItemPosition >= totalItemCount - 1) {
                onEndlessAtBottom()
            }
            if (firstVisibleItemPosition == 0) {
                onEndlessAtTop()
            }

            if (totalItemCount > 10) {
                onScrolledBottom(lastVisibleItemPosition < totalItemCount - 5)
            }
        }
    }


    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

}