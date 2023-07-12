package com.code.cancer.demos.ui.recycler

import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.base.CaseItemAdapter
import com.code.cancer.demos.databinding.ActivityRecyclerBinding
import com.code.cancer.demos.ext.showFragment
import com.code.cancer.demos.ui.recycler.dialog.RecyclerItemAnimDialog

class RecyclerActivity : BaseActivity<ActivityRecyclerBinding>() {
    override fun onCreated() = binding.run {
        recyclerView.adapter = CaseItemAdapter(
            listOf(
                "移除和添加动画",
            )
        ) {
            when (it) {
                0 -> showFragment(supportFragmentManager, RecyclerItemAnimDialog::class.java)
            }
        }
    }
}