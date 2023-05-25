package com.code.cancer.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.base.RecyclerViewAdapter
import com.code.cancer.demos.databinding.ActivityMainBinding
import com.code.cancer.demos.ext.*
import com.code.cancer.demos.ui.coordinator.CoordinatorActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val itemArr = arrayOf(
        "Coordinator"
    )

    override fun onCreated() = binding.run {
        recyclerView.adapter = ItemAdapter()
    }

    private inner class ItemAdapter : RecyclerViewAdapter<Button>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Button> {
            val button = Button(parent.context)
            button.layoutParams = viewGroupLayoutParams(MATCH_PARENT, WRAP_CONTENT)
            return ViewHolder(button)
        }

        override fun getItemCount(): Int = itemArr.size

        override fun onBindViewHolder(holder: ViewHolder<Button>, position: Int) {
            holder.view.text = itemArr[position]
            holder.view.setOnClickListener {
                when (position) {
                    0 -> runActivity(context, CoordinatorActivity::class.java)
                }
            }
        }

    }
}