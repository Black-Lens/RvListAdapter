package com.blacklenspub.rvlistadapter;

import android.view.View;

import org.jetbrains.annotations.NotNull;

public interface OnItemClickListener<T, VH> {
    void onItemClick(@NotNull View view, int position, @NotNull VH holder, @NotNull T item);
}
