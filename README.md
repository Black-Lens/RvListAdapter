# RvListAdapter
This mini-library is an another attempt to simplify how you create a RecyclerView.Adapter.
It is written in Kotlin with Java in mind.
I know there are tons of RecyclerView.Adpater out there solving different problems.
But sometimes you don't need all those functionalties they offer and you are tired of writing all the boilerplate code again and again. Let RvListAdapter helps you.

##Features
- Built-in `List<T>`
- Built-in `onItemClickListener<T,VH>`
- `DiffUtil` support

##Download
soon

##Guide

###ViewHolder
A view holder must be extended from `ItemViewHolder<T,VH>`.
`ItemViewHolder<T,VH>` has a field `item` holding your item of type T.
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

###Adapter
You override `onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater)` like you always do but you also get a LayoutInflater for easy inflating.
`onBindViewHolder(StudentHolder holder, int position, T item)` is the same and you also get your item T.
Now, the constructor is a little bit confusing, it accepts 3 parameters a `List<T>`, an `OnItemClickListener<T,VH>` and a `DiffCalculator`.
A `List<T>` is an initial list of your items.
An `OnItemClickListener<T,VH>` is how you listen for click events, leave it null if you don't want to.
A `DiffCalculator` is an interface which has only one method `getDiffResult(List<T> oldList, List<T> newList)`.
Basically you have to return a DiffUtil.DiffResult based on oldList and newList and the RecyclerView will animate the view for you. Leave it null if you just want a simple `notifyDatasetChanged()`.

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

###How to use
