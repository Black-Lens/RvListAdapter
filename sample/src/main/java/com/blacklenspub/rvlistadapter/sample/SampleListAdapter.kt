package com.blacklenspub.rvlistadapter.sample

import android.R
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blacklenspub.rvlistadapter.DiffCalculator
import com.blacklenspub.rvlistadapter.ItemViewHolder
import com.blacklenspub.rvlistadapter.OnItemClickListener
import com.blacklenspub.rvlistadapter.RvListAdapter

class StringHolder(itemView: View) : ItemViewHolder<String, StringHolder>(itemView) {
    val text1: TextView = itemView.findViewById(R.id.text1) as TextView
    val text2: TextView = itemView.findViewById(R.id.text2) as TextView

    init {
        text1.setOnClickListener {
            callOnClick(it)
        }
        text2.setOnClickListener {
            callOnClick(it)
        }
    }
}

class StringAdapter : RvListAdapter<String, StringHolder> {
    constructor(list: List<String>? = null, onItemClick: OnItemClickListener<String, StringHolder>? = null, diff: DiffCalculator<String>? = null) : super(list, onItemClick, diff)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, inflater: LayoutInflater): StringHolder {
        return StringHolder(inflater.inflate(R.layout.simple_list_item_2, parent, false))
    }

    override fun onBindViewHolder(holder: StringHolder, position: Int, item: String) {
        holder.apply {
            text1.text = item
            text2.text = item
        }
    }

}

fun sample() {
    val adapter = StringAdapter(
            listOf("A", "B", "C"),
            OnItemClickListener { view, i, vh, t -> },
            DiffCalculator { oleList, newList ->
                DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oleList[oldItemPosition] == newList[newItemPosition]

                    override fun getOldListSize(): Int = oleList.size

                    override fun getNewListSize(): Int = newList.size

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = areItemsTheSame(oldItemPosition, newItemPosition)
                })
            }
    )
    val ada = StringAdapter(emptyList(), null, SimpleDiffCalculator())
}