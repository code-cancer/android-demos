package com.code.cancer.demos

import android.provider.Settings
import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.base.CaseItemAdapter
import com.code.cancer.demos.common.utils.Permissions
import com.code.cancer.demos.databinding.ActivityMainBinding
import com.code.cancer.demos.ext.context
import com.code.cancer.demos.ext.runActivity
import com.code.cancer.demos.ui.down.FloatCountDownView
import com.code.cancer.demos.ui.nested.NestedActivity
import com.code.cancer.demos.ui.recycler.RecyclerActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var floatCountDownView: FloatCountDownView? = null

    override fun onCreated() = binding.run {
        initFeatureList()
    }

    private fun initFeatureList() = binding.run {
        recyclerView.adapter = CaseItemAdapter(
            listOf(
                "Coordinator",
                "Recycler",
                "(显示/隐藏)悬浮时钟"
            )
        ) {
            when (it) {
                0 -> runActivity(context, NestedActivity::class.java)
                1 -> runActivity(context, RecyclerActivity::class.java)
                2 -> tryShowOrRemoveCountDownView()
            }
        }
    }

    private fun tryShowOrRemoveCountDownView() {
        val countDownView = floatCountDownView
        if (countDownView != null && countDownView.isInWindow) {
            countDownView.remove()
        } else if (Permissions.canOverlay()) {
            realShowCountDownView()
        } else {
            Permissions.requestOverlay(this)
        }

    }

    private fun realShowCountDownView() {
        val countDownView = floatCountDownView
        if (countDownView != null) {
            countDownView.show()
        } else {
            floatCountDownView = FloatCountDownView(this)
            floatCountDownView?.show()
        }

    }

}