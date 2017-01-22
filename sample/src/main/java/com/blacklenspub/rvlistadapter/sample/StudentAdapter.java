package com.blacklenspub.rvlistadapter.sample;

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

public class StudentAdapter extends RvListAdapter<Student, StudentAdapter.StudentHolder> {

    public StudentAdapter(@Nullable List<? extends Student> list, @Nullable OnItemClickListener<Student, StudentHolder> onItemClick, @Nullable DiffCalculator<Student> diff) {
        super(list, onItemClick, diff);
    }

    @NotNull
    @Override
    public StudentHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType, @NotNull LayoutInflater inflater) {
        return new StudentHolder(inflater.inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull StudentHolder holder, int position, @NotNull Student item) {
        holder.tvName.setText(item.name);
        holder.tvHouse.setText(item.house);
    }

    static class StudentHolder extends ItemViewHolder<Student, StudentAdapter.StudentHolder> {
        TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
        TextView tvHouse = (TextView) itemView.findViewById(R.id.tvHouse);

        StudentHolder(@NotNull View itemView) {
            super(itemView);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callOnClick(view);
                }
            });
            tvHouse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callOnClick(view);
                }
            });
        }
    }
}
