package com.blacklenspub.rvlistadapter;

import android.support.annotation.NonNull;
import android.view.View;

public interface OnItemClickListener<T, VH> {
    void onItemClick(@NonNull View view, int position, @NonNull VH holder, @NonNull T item);
}
