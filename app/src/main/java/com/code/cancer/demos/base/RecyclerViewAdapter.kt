package com.code.cancer.demos.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<V: View>: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder<V>>() {

    class ViewHolder<V: View>(val view: V): RecyclerView.ViewHolder(view)

}