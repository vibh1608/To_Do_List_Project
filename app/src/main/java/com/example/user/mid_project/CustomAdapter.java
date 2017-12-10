package com.example.user.mid_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 07-12-2017.
 */

//creating class by extending BaseAdapter to locate data into ListView.
public class CustomAdapter extends BaseAdapter {

    //creating context of current activity
    public Context mcontext;

    //ArrayList of Entry objects which will be stored in ListView.
    public ArrayList<Task> mlist;

    //creating mlayoutinflator object to show layout file
    public LayoutInflater mlayoutinflator;

    //construoctor
    public CustomAdapter(Context context , ArrayList<Task> tasks)
    {
        //getting context of current activity in mcontext
        mcontext = context;

        //getting list of tasks in mList of current activity
        mlist = tasks;

        //getting systemServices to inflate the layout
        mlayoutinflator = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Method to get number of elements in the ArrayList.
    @Override
    public int getCount() {
        return mlist.size();
    }

    //Method to get particular element from the ArrayList.
    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    //Method to get ID of particular elements in the ArrayList.
    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    //getView method to create the layout and setting Values in the ArrayList in the layout.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //Setting View to the Layout file.
        view = mlayoutinflator.inflate(R.layout.row,viewGroup,false);

        //setting reference of head date with it's ID in layout file
        TextView date1 = view.findViewById(R.id.head_date);

        //setting date to the head date
        date1.setText(mlist.get(i).getMdate());

        //setting reference of title with it's ID in layout file
        TextView title = view.findViewById(R.id.title_tv);

        //setting date to the title
        title.setText(mlist.get(i).getMtitle());

        //setting reference of description with it's ID in layout file
        TextView description = view.findViewById(R.id.description_tv);

        //setting date to the description
        description.setText(mlist.get(i).getMdescription());

        //setting reference of tale date with it's ID in layout file
        TextView date2 = view.findViewById(R.id.tale_date);

        //setting date to the tale date
        date2.setText(mlist.get(i).getMdate());


        //setting reference of ImageView with it's ID in layout file
        ImageView status = view.findViewById(R.id.completion_status);

        //Setting image based on status of object in ImageView.
        if(mlist.get(i).getMstatus()==1)
        {
            status.setImageResource(R.drawable.completed);
        }
        else
        {
            status.setImageResource(R.drawable.to_complete);
        }

        //returing view
        return view;
    }
}