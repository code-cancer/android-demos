package com.code.cancer.demos.ui.nested.dialogs

import android.graphics.Color
import com.code.cancer.demos.base.BaseDialog
import com.code.cancer.demos.base.RecyclerBindingAdapter
import com.code.cancer.demos.common.adapter.RandomColorTextAdapter
import com.code.cancer.demos.databinding.DialogBehaviorBinding
import com.code.cancer.demos.databinding.NestedItemViewBinding
import com.code.cancer.demos.ext.modifyParams
import java.util.*

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-12
 *  @What:
 */
class BehaviorDialog : BaseDialog<DialogBehaviorBinding>() {


    override fun onCreated(): Unit = binding.run {
        root.post {
            recyclerView.modifyParams {
                height = root.height - textView.height
            }
            recyclerView.adapter = RandomColorTextAdapter()
        }
    }

}