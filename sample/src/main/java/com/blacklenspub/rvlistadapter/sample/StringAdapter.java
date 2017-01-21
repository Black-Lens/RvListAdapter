package com.blacklenspub.rvlistadapter.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blacklenspub.rvlistadapter.DiffCalculator;
import com.blacklenspub.rvlistadapter.OnItemClickListener;
import com.blacklenspub.rvlistadapter.RvListAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StringAdapter extends RvListAdapter<String, StringHolder> {

    public StringAdapter(@Nullable List<? extends String> list, @Nullable OnItemClickListener<String, StringHolder> onItemClick, @Nullable DiffCalculator<String> diff) {
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
