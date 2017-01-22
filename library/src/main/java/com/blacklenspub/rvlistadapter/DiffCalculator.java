package com.blacklenspub.rvlistadapter;

import android.support.v7.util.DiffUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DiffCalculator<T> {
    DiffUtil.DiffResult getDiffResult(@NotNull List<T> oldList, @NotNull List<T> newList);
}
