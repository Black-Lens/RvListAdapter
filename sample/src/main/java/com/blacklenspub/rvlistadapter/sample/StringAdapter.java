package com.blacklenspub.rvlistadapter.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blacklenspub.rvlistadapter.DiffCalculator;
import com.blacklenspub.rvlistadapter.ItemViewHolder;
import com.blacklenspub.rvlistadapter.OnItemClickListener;
import com.blacklenspub.rvlistadapter.RvListAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StringAdapter extends RvListAdapter<String, StringAdapter.StringHolder> {

    public StringAdapter(@Nullable List<? extends String> list, @Nullable OnItemClickListener<String, StringHolder> onItemClick, @Nullable DiffCalculator<String> diff) {
        super(list, onItemClick, diff);
    }

    @NotNull
    @Override
    public StringHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType, @NotNull LayoutInflater inflater) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new StringHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull StringHolder holder, int position, @NotNull String item) {
        holder.text1.setText(item);
    }

    static class StringHolder extends ItemViewHolder<String, StringHolder> {
        TextView text1 = (TextView) itemView.findViewById(android.R.id.text1);

        StringHolder(@NonNull View itemView) {
            super(itemView);
            text1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callOnClick(view);
                }
            });
        }
    }
}
