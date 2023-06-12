package com.code.cancer.demos

import android.view.ViewGroup
import android.widget.Button
import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.base.CaseItemAdapter
import com.code.cancer.demos.base.RecyclerViewAdapter
import com.code.cancer.demos.databinding.ActivityMainBinding
import com.code.cancer.demos.ext.*
import com.code.cancer.demos.ui.nested.NestedActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreated() = binding.run {
        recyclerView.adapter = CaseItemAdapter(
            listOf(
                "Coordinator"
            )
        ) {
            when (it) {
                0 -> runActivity(context, NestedActivity::class.java)
            }
        }
    }

}