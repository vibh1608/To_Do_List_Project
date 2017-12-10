package com.example.user.mid_project;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//Class created by extending the AppCompatActivity class.
public class Completed_task extends AppCompatActivity {
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    //Creaing References of Elements used in the Layout.
    Toolbar mToolbar;
    ImageView add,complete;
    ListView mList1;
    ArrayList<Task> arrayList2 ;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_task);

        //Setting toolbar with its ID and settinng it as Support ActionBar.
        mToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        //Setting references with their IDs.
        add = findViewById(R.id.add_tool_btn2);
        complete = findViewById(R.id.complete_tool_btn2);
        mList1 = findViewById(R.id.list2);

        //creating taskRepository object access databse
        TaskRepository taskRepository = new TaskRepository(Completed_task.this);

        //getting arraylist of all completed tasks
        if(taskRepository.getrowcount()>0 && !taskRepository.getCompletedTskList().isEmpty()) {
            arrayList2 = taskRepository.getCompletedTskList();
        }
        else {
            arrayList2 = new ArrayList<>();
        }

        //creating customadapter object and passing array to customadapter class
        CustomAdapter customAdapter = new CustomAdapter(Completed_task.this,arrayList2);

        //setting adapter to arrayList
        mList1.setAdapter(customAdapter);

        //displaying toast message
        Toast.makeText(Completed_task.this,"This is completed task screen",Toast.LENGTH_SHORT).show();

        //onClick of add(+) symbol of toolbar
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //displaying toast message
                Toast.makeText(Completed_task.this,"Please press back to add task in list",Toast.LENGTH_SHORT).show();
            }
        });

        //onClick of complete symbol of toolbar
        complete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //displaying toast message
                Toast.makeText(Completed_task.this,"You are already watching completed tasks list",Toast.LENGTH_SHORT).show();
            }
        });

        //if arrayList is empty
        if(arrayList2.isEmpty())
        {
            //displaying toast message
            Toast.makeText(Completed_task.this,"List is empty",Toast.LENGTH_SHORT).show();
        }

        //method when particular item of listView is clicked
        mList1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //displaying toast message
                Toast.makeText(Completed_task.this,"Press long to delete the task",Toast.LENGTH_SHORT).show();
            }
        });

        //method when particular item of listview is long pressed
        mList1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //creating taskRepository1 object to access
                TaskRepository taskRepository1 = new TaskRepository(Completed_task.this);

                //deleting task of completed task that is long pressed
                taskRepository1.delete(arrayList2.get(i).getMid());

                //creating taskRepository2 object to access updated database
                TaskRepository taskRepository2 = new TaskRepository(Completed_task.this);

                //creating arrayList object
                ArrayList<Task> updatedList ;

                //storing all updated completed tasks in updated arraylist
                if(taskRepository2.getrowcount()>0 && !taskRepository2.getCompletedTskList().isEmpty()) {
                    updatedList = taskRepository2.getCompletedTskList();
                }
                else {
                    updatedList = new ArrayList<>();
                }

                //displaying toast message
                Toast.makeText(Completed_task.this,"Task deleted",Toast.LENGTH_SHORT).show();

                //creating customadapter object and passing array to customadapter class
                CustomAdapter customAdapter1 = new CustomAdapter(Completed_task.this,updatedList);

                //setting adapter to arrayList
                mList1.setAdapter(customAdapter1);

                //if updated arrayList is empty
                if(updatedList.isEmpty())
                {
                    //displaying toast message
                    Toast.makeText(Completed_task.this,"No completed task left",Toast.LENGTH_SHORT).show();

                    //intent to shift to mainActivity
                    Intent intent1 = new Intent(Completed_task.this,MainActivity.class);
                    startActivity(intent1);
                }

                //intent to shift to mainActivity
                Intent intent1 = new Intent(Completed_task.this,MainActivity.class);
                startActivity(intent1);

                //returning true
                return true;
            }
        });

    }
}
