package com.serj113.common.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.serj113.common.presentation.adapter.bindable.BindingCreator
import com.serj113.common.presentation.adapter.bindable.ItemView

class ItemViewAdapter : RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {
    private var mItemViews: MutableList<ItemView> = mutableListOf()
    private var mItemTypes: HashMap<Int, BindingCreator> = hashMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = mItemTypes[viewType]!!.createViewBinding(parent)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).bindView(holder.viewBinding)
    }

    override fun getItemViewType(position: Int): Int {
        val itemView = getItem(position)
        val itemViewType = itemView.viewType
        if (!mItemTypes.containsKey(itemViewType)) {
            mItemTypes[itemViewType] = itemView
        }
        return itemViewType
    }


    override fun getItemCount(): Int = mItemViews.size

    fun addItems(items: List<ItemView>) {
        mItemViews.addAll(items)
        notifyDataSetChanged()
    }

    fun setItems(items: List<ItemView>) {
        mItemViews.clear()
        addItems(items)
    }

    fun getItemView(position: Int): ItemView? {
        return mItemViews.getOrNull(position)
    }

    private fun getItem(position: Int) = mItemViews[position]

    inner class ViewHolder(val viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
