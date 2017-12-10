package com.example.user.mid_project;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//Creating class by extending MainActivity.
public class MainActivity extends AppCompatActivity  {

    //creating reference of Toolbar to make custom toolbar
    Toolbar mToolbar;

    //creating reference of ImageView to put in custom toolbar
    ImageView add,complete;

    //creating reference of Edittext of dialog box
    EditText mTitle , mDescription;

    //creating reference of buttons of dialog box
    Button msave , mcancel;

    //creating reference of datepicker of dialog box
    DatePicker mDatepicker;

    //creating reference of ListView
    public ListView mList;

    public ArrayList<Task> mArrayList;

    @Override
    //onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting toolbar with its id
        mToolbar =  findViewById(R.id.toolbar);

        //setting toolbar to support actionBar
        setSupportActionBar(mToolbar);

        //setting add reference with its ID
        add = findViewById(R.id.plus);

        //setting complete reference with its ID
        complete = findViewById(R.id.complete);

        //setting mList reference with its ID
        mList = findViewById(R.id.list);

        //Creating object of TaskRepository class .
        TaskRepository taskRepository = new TaskRepository(MainActivity.this);

        //Getting arrayList from Database and storing it into arrayList
        if(taskRepository.getrowcount()!=0) {
            mArrayList = taskRepository.getAllTask();
        }
        else {
            mArrayList = new ArrayList<>();
        }

        //creating customadapter object and passing arrayList to customAdapter class
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,mArrayList);

        //setting adapter to arraylist
        mList.setAdapter(customAdapter);

        Toast.makeText(MainActivity.this,"This is main screen",Toast.LENGTH_SHORT).show();

        //onClick of add(+) symbol of Toolbar
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //inflating dialog box
                final Dialog dialog = new Dialog(MainActivity.this);

                //setting contentView
                dialog.setContentView(R.layout.dialog);

                //setting reference with their ID
                mTitle = dialog.findViewById(R.id.title);
                mDescription = dialog.findViewById(R.id.description);
                mDatepicker = dialog.findViewById(R.id.datepicker);
                msave= dialog.findViewById(R.id.save);
                mcancel = dialog.findViewById(R.id.cancel);

                //onClick of save button of dialog box
                msave.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //validation checking , if all information is correctly filled
                        if(!mTitle.getText().toString().isEmpty() && !mDescription.getText().toString().isEmpty())
                        {
                            //creating taskRepository1 object
                            TaskRepository taskRepository1 = new TaskRepository(MainActivity.this);

                            //creating object of ContentValues
                            ContentValues contentValues = new ContentValues();

                            //putting values in contentValues
                            contentValues.put(Constants.KEY_TITLE,mTitle.getText().toString());
                            contentValues.put(Constants.KEY_DESCRIPTION,mDescription.getText().toString());
                            contentValues.put(Constants.KEY_DATE,String.valueOf(mDatepicker.getDayOfMonth())+"/"+String.valueOf(mDatepicker.getMonth())+"/"+String.valueOf(mDatepicker.getYear()));
                            contentValues.put(Constants.KEY_STATUS,0);

                            //inserting data in database through taskRepository1 object
                            taskRepository1.insertTask(contentValues);

                            //toast message
                            Toast.makeText(MainActivity.this,"Task saved",Toast.LENGTH_SHORT).show();

                            //getting all data in arraylist from database
                            mArrayList = taskRepository1.getAllTask();

                            //creating customadapter object and passing array to customadapter class
                            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,mArrayList);

                            //setting adapter to arraylist
                            mList.setAdapter(customAdapter);

                            Toast.makeText(MainActivity.this,"This is main screen",Toast.LENGTH_SHORT).show();

                            //dismissing dialog box
                            dialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Please fill the information correctly",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mcancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //dismissing dialog box
                        dialog.dismiss();
                    }
                });

                //showing dialog box
                dialog.show();
            }
        });

        //onClick of complete button
        complete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Completed items opened",Toast.LENGTH_SHORT).show();

                //intent to opening completed_task activity
                Intent intent = new Intent(MainActivity.this,Completed_task.class);
                startActivity(intent);
            }
        });

        //onClick of particular item of listView
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //displaying dialog box
                final Dialog dialog = new Dialog(MainActivity.this);

                //setting contentView
                dialog.setContentView(R.layout.dialog);

                //setting reference with their ID
                mTitle = dialog.findViewById(R.id.title);
                mDescription = dialog.findViewById(R.id.description);
                mDatepicker = dialog.findViewById(R.id.datepicker);
                msave = dialog.findViewById(R.id.save);
                mcancel = dialog.findViewById(R.id.cancel);

                //creating taskRepository1 object
                 final TaskRepository taskRepository1 = new TaskRepository(MainActivity.this);

                 //getting arrayList from database through taskRepository1 object
                ArrayList<Task> arrayList = taskRepository1.getAllTask();

                //getting particular clicked task at that position from arrayList
                final Task task = arrayList.get(i);

                //setting presaved values to the editText of dialog box
                mTitle.setText(task.getMtitle());
                mDescription.setText(task.getMdescription());

                //onClick of save button of dialog box
                msave.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //validation checking , if all information is correctly filled
                        if(!mTitle.getText().toString().isEmpty() && !mDescription.getText().toString().isEmpty())
                        {
                            //creating arrayList1 object to store updated arrayList
                            ArrayList<Task> arrayList1 ;

                            //creating updateTask object to store updated task of database
                            Task updateTask = new Task();

                            //setting values to updatedTask object with default status value 0
                            updateTask.setMid(task.getMid());
                            updateTask.setMtitle(mTitle.getText().toString());
                            updateTask.setMdescription(mDescription.getText().toString());
                            updateTask.setMdate(String.valueOf(mDatepicker.getDayOfMonth())+"/"+String.valueOf(mDatepicker.getMonth())+"/"+String.valueOf(mDatepicker.getYear()));
                            updateTask.setMstatus(task.getMstatus());

                            //creating taskRepository2 object to update databse with updated values
                            TaskRepository taskRepository2 = new TaskRepository(MainActivity.this);

                            //storing updated values in database through taskRepository2 object
                            taskRepository2.update(updateTask);

                            //getting arrayList of updated tasks of database
                            arrayList1 = taskRepository2.getAllTask();

                            //creating customadapter object and passing array to customadapter class
                            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,arrayList1);

                            //setting adapter to arraylist
                            mList.setAdapter(customAdapter);

                            Toast.makeText(MainActivity.this,"List updated",Toast.LENGTH_SHORT).show();

                            //dismissing dialog box
                            dialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Please fill the information correctly",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mcancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //dismissing dialog box
                        dialog.dismiss();
                    }
                });

                //showing dialog box
                dialog.show();

            }
        });

        //on long click of particular task of listview
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //creating taskRepository1 object access database
                TaskRepository taskRepository1 = new TaskRepository(MainActivity.this);

                //getting arraylist of database
                ArrayList<Task> arrayList = taskRepository1.getAllTask();

                //getting prticular task that is long pressed
                Task completed_task = arrayList.get(i);

                //creating object to store updated task
                Task updateTask = new Task();

                //copying presaved values to updated task
                updateTask.setMid(completed_task.getMid());
                updateTask.setMtitle(completed_task.getMtitle());
                updateTask.setMdescription(completed_task.getMdescription());
                updateTask.setMdate(completed_task.getMdate());

                //updating status of task to 1
                updateTask.setMstatus(1);

                //updating database with updated task through taskRepository1 object
                taskRepository1.update(updateTask);

                //creating taskRepository2 object to show list with updated tasks
                TaskRepository taskRepository2 = new TaskRepository(MainActivity.this);

                //getting arraylist from database
                ArrayList<Task> arrayList1 = taskRepository2.getAllTask();

                //creating customadapter object and passing array to customadapter class
                CustomAdapter customAdapter1 = new CustomAdapter(MainActivity.this,arrayList1);

                //setting adapted to arraylist
                mList.setAdapter(customAdapter1);

                Toast.makeText(MainActivity.this,"Item updated",Toast.LENGTH_SHORT).show();

                return true;
            }
        });//end of method

    }//end of onCreate method

}
//end of class
