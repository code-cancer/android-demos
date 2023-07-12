package com.code.cancer.demos.ui.recycler.dialog

import androidx.recyclerview.widget.DefaultItemAnimator
import com.code.cancer.demos.base.BaseDialog
import com.code.cancer.demos.common.adapter.NormalAdapter
import com.code.cancer.demos.databinding.DialogRecyclerItemAnimBinding

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-11
 *  @What:
 */
class RecyclerItemAnimDialog : BaseDialog<DialogRecyclerItemAnimBinding>() {

    private val adapter = NormalAdapter<String>()

    override fun onCreated() = binding.run {
        recyclerView.adapter = adapter
        val itemAnimator = DefaultItemAnimator().apply {
            addDuration = 1000
            removeDuration = 1000
        }
        recyclerView.itemAnimator = itemAnimator
        add.setOnClickListener {
            val itemCount = adapter.itemCount
            adapter.add(0, "$itemCount")
        }
        remove.setOnClickListener {
            adapter.remove(0)
        }
    }


}