package com.blacklenspub.rvlistadapter

import android.support.v7.util.DiffUtil

/**
 * [SimpleDiffCalculator] is good for primitives such as [String].
 * [getDiffResult] returns a [DiffUtil.DiffResult] which call [T.equals] in both [DiffUtil.Callback.areItemsTheSame] and [DiffUtil.Callback.areContentsTheSame].
 */
class SimpleDiffCalculator<T> : DiffCalculator<T> {
    override fun getDiffResult(oldList: List<T>, newList: List<T>): DiffUtil.DiffResult =
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

                override fun getOldListSize(): Int = oldList.size

                override fun getNewListSize(): Int = newList.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
            })
}