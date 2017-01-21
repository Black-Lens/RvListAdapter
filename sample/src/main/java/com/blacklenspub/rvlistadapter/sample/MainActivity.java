package com.blacklenspub.rvlistadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

public class MainActivity extends AppCompatActivity {
    StringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new StringAdapter(
                Arrays.asList("Harry Potter", "Ron Weasley"),
                new Function4<View, Integer, StringHolder, String, Unit>() {
                    @Override
                    public Unit invoke(View view, Integer integer, StringHolder stringHolder, String s) {
                        if (view == stringHolder.text1) {
                            Toast.makeText(MainActivity.this, "text1", Toast.LENGTH_SHORT).show();
                        } else if (view == stringHolder.text2) {
                            Toast.makeText(MainActivity.this, "text4", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                },
                new Function2<List<? extends String>, List<? extends String>, DiffUtil.DiffResult>() {
                    @Override
                    public DiffUtil.DiffResult invoke(final List<? extends String> strings, final List<? extends String> strings2) {
                        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                            @Override
                            public int getOldListSize() {
                                return strings.size();
                            }

                            @Override
                            public int getNewListSize() {
                                return strings2.size();
                            }

                            @Override
                            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                                return strings.get(oldItemPosition).equals(strings2.get(newItemPosition));
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
                        Collections.sort(strings);
                        adapter.setList(strings);
                    }
                },
                5000L);
    }
}
