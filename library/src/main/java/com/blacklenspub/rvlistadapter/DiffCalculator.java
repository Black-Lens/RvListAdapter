package com.blacklenspub.rvlistadapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

public interface DiffCalculator<T> {
    DiffUtil.DiffResult getDiffResult(@NonNull List<T> oldList, @NonNull List<T> newList);
}
