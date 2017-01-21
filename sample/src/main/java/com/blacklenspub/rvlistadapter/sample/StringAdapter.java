package com.blacklenspub.rvlistadapter.sample;

import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blacklenspub.rvlistadapter.RvListAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

public class StringAdapter extends RvListAdapter<String, StringHolder> {
    public StringAdapter(@Nullable List<? extends String> list, @Nullable Function4<? super View, ? super Integer, ? super StringHolder, ? super String, Unit> onItemClick, @Nullable Function2<? super List<? extends String>, ? super List<? extends String>, ? extends DiffUtil.DiffResult> diff) {
        super(list, onItemClick, diff);
    }

    @NotNull
    @Override
    public StringHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType, @NotNull LayoutInflater inflater) {
        View view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        return new StringHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull StringHolder holder, int position, @NotNull String item) {
        holder.text1.setText(item);
        holder.text2.setText(item);
    }
}
