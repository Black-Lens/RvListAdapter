package com.blacklenspub.rvlistadapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.properties.Delegates

abstract class ItemViewHolder<T : Any, VH>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var item: T
    var onClick: ((view: View, position: Int, holder: VH, item: T) -> Unit)? = null

    init {
        itemView.setOnClickListener { callOnClick(it) }
    }

    fun callOnClick(view: View) {
        onClick?.let {
            it.invoke(view, adapterPosition, this@ItemViewHolder as VH, item)
        }
    }
}

abstract class RvListAdapter<T : Any, VH : ItemViewHolder<T, VH>> @JvmOverloads constructor(
        list: List<T>? = null,
        val onItemClick: ((view: View, position: Int, holder: VH, item: T) -> Unit)? = null,
        val diff: ((oldList: List <T>, newList: List<T>) -> DiffUtil.DiffResult)? = null
) : RecyclerView.Adapter<VH>() {

    var list: List<T> by Delegates.observable(list ?: emptyList(), { kProperty, oldList, newList ->
        if (diff != null) {
            diff.invoke(oldList, newList).dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    })

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = onCreateViewHolder(parent, viewType, LayoutInflater.from(parent.context)).apply {
        onClick = onItemClick
    }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, inflater: LayoutInflater): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item = list[position]
        onBindViewHolder(holder, position, holder.item)
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T)
}