package com.code.cancer.demos.ui.nested.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.NestedScrollView
import com.code.cancer.demos.R

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-13
 *  @What:
 */
class CustomNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {

    private val topImageView: AppCompatImageView by lazy { findViewById(R.id.imgView) }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val up = dy > 0 && scrollY < topImageView.height
        val down = dy < 0 && topImageView.x < 0
        if(up || down) {
            scrollBy(0, dy)
            consumed[1] = dy
        }
    }

}