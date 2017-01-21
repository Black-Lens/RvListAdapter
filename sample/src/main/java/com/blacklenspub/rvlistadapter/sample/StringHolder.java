package com.blacklenspub.rvlistadapter.sample;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.blacklenspub.rvlistadapter.ItemViewHolder;

public class StringHolder extends ItemViewHolder<String, StringHolder> {
    public TextView text1 = (TextView) itemView.findViewById(android.R.id.text1);
    public TextView text2 = (TextView) itemView.findViewById(android.R.id.text2);

    public StringHolder(@NonNull View itemView) {
        super(itemView);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOnClick(view);
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOnClick(view);
            }
        });
    }
}
