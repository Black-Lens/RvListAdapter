package com.blacklenspub.rvlistadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.properties.Delegates

abstract class ItemViewHolder<T : Any, VH>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var item: T
    var onClick: OnItemClickListener<T, VH>? = null

    fun callOnClick(view: View) {
        onClick?.onItemClick(view, adapterPosition, this@ItemViewHolder as VH, item)
    }
}

abstract class RvListAdapter<T : Any, VH : ItemViewHolder<T, VH>> @JvmOverloads constructor(
        list: List<T>? = null,
        val onItemClick: OnItemClickListener<T, VH>? = null,
        val diff: DiffCalculator<T>? = null
) : RecyclerView.Adapter<VH>() {

    var list: List<T> by Delegates.observable(list ?: emptyList(), { kProperty, oldList, newList ->
        if (diff != null) {
            diff.getDiffResult(oldList, newList).dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    })

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            onCreateViewHolder(parent, viewType, LayoutInflater.from(parent.context)).apply {
                onItemClick?.let {
                    onClick = onItemClick
                    itemView.setOnClickListener { callOnClick(it) }
                }
            }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, inflater: LayoutInflater): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item = list[position]
        onBindViewHolder(holder, position, holder.item)
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T)

    fun add(item: T) {
        list += item
    }

    fun addAll(items: Collection<T>) {
        list += items
    }

    fun addAll(vararg items: T) {
        list += items
    }
}