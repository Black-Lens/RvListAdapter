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
import com.blacklenspub.rvlistadapter.SimpleDiffCalculator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setupStringAdapter();
        setupStudentAdapter();
    }

    private void setupStringAdapter() {
        final StringAdapter adapter = new StringAdapter(
                Arrays.asList("Harry Potter", "Ron Weasley", "Hermione Granger", "Albus Dumbledore"),
                new OnItemClickListener<String, StringAdapter.StringHolder>() {
                    @Override
                    public void onItemClick(View view, int position, StringAdapter.StringHolder holder, String item) {
                        Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                    }
                },
                new SimpleDiffCalculator<String>()
        );

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        adapter.add("Luna Lovegood");
                    }
                },
                2000L
        );
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        List<String> strings = new ArrayList<>(adapter.getList());
                        Collections.sort(strings);
                        adapter.setList(strings);
                    }
                },
                4000L
        );
    }

    private void setupStudentAdapter() {
        final StudentAdapter adapter = new StudentAdapter(
                null,
                new OnItemClickListener<Student, StudentAdapter.StudentHolder>() {
                    @Override
                    public void onItemClick(@NotNull View view, int position, @NotNull StudentAdapter.StudentHolder holder, @NotNull Student item) {
                        String msg = "";
                        if (view == holder.tvName) {
                            msg = item.name;
                        } else if (view == holder.tvHouse) {
                            msg = item.house;
                        } else if (view == holder.itemView) {
                            msg = "Student ID: " + item.id;
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                },
                new DiffCalculator<Student>() {
                    @Override
                    public DiffUtil.DiffResult getDiffResult(@NotNull final List<Student> oldList, @NotNull final List<Student> newList) {
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
                                return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
                            }

                            @Override
                            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                                Student oldStudent = oldList.get(oldItemPosition);
                                Student newStudent = newList.get(newItemPosition);
                                if (oldStudent.name.equals(newStudent.name)) {
                                    return false;
                                }
                                if (oldStudent.house.equals(newStudent.house)) {
                                    return false;
                                }
                                return true;
                            }
                        });
                    }
                }
        );

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(initialStudents());
                    }
                },
                2000L
        );
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        adapter.setList(modifiedStudents());
                    }
                },
                4000L
        );
    }

    private List<Student> initialStudents() {
        return Arrays.asList(
                new Student(3, "Harry Potter", "Slytherin"),
                new Student(2, "Cho Chang", "Ravenclaw"),
                new Student(4, "Hannah Abbott", "Hufflepuff"),
                new Student(1, "Tom Riddle", "Slytherin")
        );
    }

    private List<Student> modifiedStudents() {
        return Arrays.asList(
                new Student(1, "Voldemort", "Slytherin"),
                new Student(2, "Cho Chang", "Ravenclaw"),
                new Student(3, "Harry Potter", "Gryffindor"),
                new Student(4, "Hannah Abbott", "Hufflepuff")
        );
    }
}
