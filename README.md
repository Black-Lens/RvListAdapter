# RvListAdapter
This mini-library is an another attempt to simplify how you create a RecyclerView.Adapter.
It is written in Kotlin with Java in mind.
I know there are tons of RecyclerView.Adpater out there solving different problems.
But sometimes you don't need all those functionalties they offer and you are tired of writing all the boilerplate code again and again. Let RvListAdapter helps you.

##Features
- Built-in `List<T>`
- Built-in `onItemClickListener`
- `DiffUtil` support

##Download
soon

##Guide

###Creating ViewHolder
You would extend your ViewHolder from `ItemViewHolder<T,VH>`.
`ItemViewHolder<T,VH>` has a field `item` holding your item of type `T`.
`VH` is the type of your concrete implementation of ItemViewHolder.
There is a handy method `callOnClick(View)` to notify your OnItemClickListener of any child view clicked.

```java
public class Student {
    public long id;
    public String name;
    public String house;

    public Student(long id, String name, String house) {
        this.id = id;
        this.name = name;
        this.house = house;
    }
}

class StudentHolder extends ItemViewHolder<Student, StudentHolder> {
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
```

###Creating Adapter
You override `onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater)` and `onBindViewHolder(StudentHolder holder, int position, T item)` like you always do but with extra handy parameters.

```java
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
}
```

Now, the constructor is a little bit confusing, it accepts 3 parameters a `List<T>`, an `OnItemClickListener<T,VH>` and a `DiffCalculator`.
- A `List<T>` is an initial list of your items.
- An `OnItemClickListener<T,VH>` is how you listen for click events, leave it null if you don't want to.
- A `DiffCalculator` is an interface which has only one method `getDiffResult(List<T> oldList, List<T> newList)`.
Basically you have to return a `DiffUtil.DiffResult` based on oldList and newList and the RecyclerView will animate the view for you. Leave it null if you just want a simple `notifyDatasetChanged()`.

###How to use
Let's put all of them together.

```java
class StudentDiffCalculator implements DiffCalculator<Student> {
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

StudentAdapter adapter = new StudentAdapter(
    getInitialStudents(),
    new OnItemClickListener<Student, StudentAdapter.StudentHolder>() {
        @Override
        public void onItemClick(@NotNull View view, int position, @NotNull StudentAdapter.StudentHolder holder, @NotNull Student item) {
            String msg = "";
            if (view == holder.tvName) { //identify a clicked view
                msg = item.name;
            } else if (view == holder.tvHouse) {
                msg = item.house;
            } else if (view == holder.itemView) {
                msg = "Student ID: " + item.id;
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    },
    new StudentDiffCalculator()
);
```

###Add items
There are 3 methods for this. Call any of them and it will automatically call `notifyDatasetChanged()` or `DiffCalculator.getDiffResult()` for you.
```java
adapter.add(getAStudent()); //add an item

adapter.addAll(getMoreStudent()); //add a collection of items

adapter.setList(getAWholeNewListOfStudent()); //replace a list
```

Take a look at the sample project if this guide confuses you (sorry for that).
