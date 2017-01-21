package com.blacklenspub.rvlistadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.blacklenspub.rvlistadapter.DiffCalculator;
import com.blacklenspub.rvlistadapter.OnItemClickListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    StringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new StringAdapter(
                Arrays.asList("Harry Potter", "Ron Weasley"),
                new OnItemClickListener<String, StringHolder>() {
                    @Override
                    public void onItemClick(View view, int position, StringHolder holder, String item) {
                        if (view == holder.text1) {
                            Toast.makeText(MainActivity.this, "text1", Toast.LENGTH_SHORT).show();
                        } else if (view == holder.text2) {
                            Toast.makeText(MainActivity.this, "text2", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new DiffCalculator<String>() {
                    @Override
                    public DiffUtil.DiffResult getDiffResult(final List<String> oldList, final List<String> newList) {
                        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                            @Override
                            public int getOldListSize() {
                                return oldList.size();
                            }

                            @Override
                            public int getNewListSize() {
                                return newList.size();
                            }

                            @Override
                            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                                return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
                            }

                            @Override
                            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                                return areItemsTheSame(oldItemPosition, newItemPosition);
                            }
                        });
                    }
                }
        );
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        List<String> strings = Arrays.asList("Harry Potter", "Ron Weasley", "Hermione Granger", "Rubeus Hagrid");
                        Collections.shuffle(strings);
                        adapter.setList(strings);
                    }
                },
                5000L);
    }
}
