package com.code.cancer.demos.ui.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-08-09
 *  @What: 自定义LayoutManager，支持无限循环，旋转效果
 */
class CustomLayoutManager(
    private val loop: Boolean = true
) : RecyclerView.LayoutManager() {

    @RecyclerView.Orientation
    var mOrientation = RecyclerView.HORIZONTAL
    //旋转角度
    val maxDegree = 20f

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        val wrapContent = RecyclerView.LayoutParams.WRAP_CONTENT
        return RecyclerView.LayoutParams(wrapContent, wrapContent)
    }

    override fun canScrollHorizontally(): Boolean {
        return mOrientation == RecyclerView.HORIZONTAL
    }

    override fun canScrollVertically(): Boolean {
        return mOrientation == RecyclerView.VERTICAL
    }

    //
    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        //如果没有数据或是预布局(首次布局)则不处理
        if (itemCount <= 0 || state.isPreLayout) return
        //将布局内所有ItemView分离至scrap
        detachAndScrapAttachedViews(recycler)
        //填充
        layoutFill(recycler)
    }

    //先只考虑横向情况
    private fun layoutFill(recycler: RecyclerView.Recycler) {
        when (mOrientation) {
            RecyclerView.HORIZONTAL -> {
                val itemView = recycler.getViewForPosition(0)
                addView(itemView)
                measureChildWithMargins(itemView, 0, 0)
                val childHeight = getDecoratedMeasuredHeight(itemView)
                val childWidth = getDecoratedMeasuredWidth(itemView)
                val left = (width - childWidth) / 2
                val right = left + childWidth
                val top = (height - childHeight) / 2
                val bottom = top + childHeight
                layoutDecorated(itemView, left, top, right, bottom)
                if (left > 0) {
                    fillHorizontal(recycler, true)
                    fillHorizontal(recycler, false)
                }
                modifyItemViewsParams()
            }
            RecyclerView.VERTICAL -> {

            }
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        if (dx == 0 || itemCount <= 0) return 0
        //填充
        fillHorizontal(recycler, dx > 0)
        //移动
        val consumeX = scrollSafely(dx)
        //回收
        recyclerChildView(dx > 0, recycler)
        //返回-consumeX则产生惯性, 反之无惯性
        return consumeX
    }

    private fun scrollSafely(dx: Int): Int {
        val consumeX: Int = if (dx > 0) {
            val lastView = getChildAt(childCount - 1)
            if (lastView != null && getPosition(lastView) == itemCount - 1) {
                val childWidth = getDecoratedMeasuredWidth(lastView)
                val halfOffWidth = (width - childWidth) / 2
                val offset = lastView.right - width + halfOffWidth
                if (offset > 0) {
                    if (lastView.right - dx >= width - halfOffWidth) -dx else -offset
                } else 0
            } else -dx
        } else {
            val firstView = getChildAt(0)
            if (firstView != null && getPosition(firstView) == 0) {
                val childWidth = getDecoratedMeasuredWidth(firstView)
                val halfOffWidth = (width - childWidth) / 2
                if (firstView.left - dx < halfOffWidth) -dx else halfOffWidth - firstView.left
            } else -dx
        }
        offsetChildrenHorizontal(consumeX)
        modifyItemViewsParams()
        return consumeX
    }

    private fun modifyItemViewsParams() {
        //根据中心偏移计算旋转角度
        for (i in (0 until childCount)) {
            val child = getChildAt(i) ?: continue
            val centerX = width / 2
            val childWidth = getDecoratedMeasuredWidth(child)
            val childCenterX = child.left + childWidth / 2
            val maxOffset = (centerX + childWidth / 2).toFloat()
            val offset = centerX - childCenterX
            val degree = offset / maxOffset * maxDegree
            child.rotationY = degree
        }
    }

    private fun fillHorizontal(recycler: RecyclerView.Recycler, fillLast: Boolean) {
        if (fillLast) { //填充后面
            val lastView = getChildAt(childCount - 1) ?: return
            val position = getPosition(lastView)
            if (lastView.right < width) {
                val needAddView = if (loop) {
                    recycler.getViewForPosition((position + 1) % itemCount)
                } else if (position + 1 < itemCount) {
                    recycler.getViewForPosition(position + 1)
                } else {
                    null
                } ?: return
                addView(needAddView)
                Log.d("DebugTest", "addBack: ${position + 1}")
                //测量
                measureChildWithMargins(needAddView, 0, 0)
                val startLeft = lastView.right
                val childHeight = getDecoratedMeasuredHeight(needAddView)
                val top = (height - childHeight) / 2
                val right = startLeft + getDecoratedMeasuredWidth(needAddView)
                val bottom = top + childHeight
                //布局
                layoutDecorated(needAddView, startLeft, top, right, bottom)
            }
        } else { //填充前面
            val child = getChildAt(0) ?: return
            val position = getPosition(child)
            if (child.left > 0) {
                val needAddView = if (loop) {
                    val realPos = if (position - 1 < 0) itemCount - 1 else position - 1
                    recycler.getViewForPosition(realPos)
                } else if (position - 1 >= 0) {
                    recycler.getViewForPosition(position - 1)
                } else {
                    null
                } ?: return
                addView(needAddView, 0)
                //测量
                measureChildWithMargins(needAddView, 0, 0)
                val childHeight = getDecoratedMeasuredHeight(needAddView)
                val top = (height - childHeight) / 2
                val right = child.left
                val left = right - getDecoratedMeasuredWidth(needAddView)
                val bottom = top + childHeight
                //布局
                layoutDecorated(needAddView, left, top, right, bottom)
            }
        }
    }


    private fun recyclerChildView(fillEnd: Boolean, recycler: RecyclerView.Recycler) {
        if (fillEnd) { //回收前面
            for (i in (0 until childCount)) {
                val child = getChildAt(i) ?: break
                if (child.right < 0) {
                    removeAndRecycleView(child, recycler)
                } else {
                    break
                }
            }
        } else { //回收后面
            for (i in (childCount - 1 downTo 0)) {
                val child = getChildAt(i) ?: break
                if (child.left > width) {
                    removeAndRecycleView(child, recycler)
                } else {
                    break
                }
            }
        }
    }

}