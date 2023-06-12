package com.code.cancer.demos.ui.nested

import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.base.CaseItemAdapter
import com.code.cancer.demos.databinding.ActivityNestedBinding
import com.code.cancer.demos.ext.showFragment
import com.code.cancer.demos.ui.nested.dialogs.NormalScrollDialog

class NestedActivity : BaseActivity<ActivityNestedBinding>() {

    override fun onCreated(): Unit = binding.run {
        recyclerView.adapter = CaseItemAdapter(
            listOf(
                "普通嵌套滑动"
            )
        ) {
            when (it) {
                0 -> showFragment(supportFragmentManager, NormalScrollDialog::class.java)
            }
        }
    }

}