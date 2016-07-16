package com.bourymbodj.employeedatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Employee> imageArry= new ArrayList<Employee>();
    EmployeeImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhelper db = new DBhelper(this);
// get image from drawable
        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile);

// convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
/**
 * CRUD Operations
 * */
// Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addEmployee(new Employee("Profile","25", imageInByte));
// display main List view bcard and contact name

// Reading all contacts from database
        List<Employee> employees = db.getAllEmployees();
        for ( Employee cn : employees){
            String log = "ID:" + cn.getId() + " Name: " + cn.getName()
                    + " ,Age: " + cn.getAge()
                    + " ,Image: " + cn.getPhoto();

// Writing Contacts to log
            Log.d("Result: ", log);
//add contacts data in arrayList
            imageArry.add(cn);

        }
        adapter = new EmployeeImageAdapter(this, R.layout.activity_list_item,
                imageArry);
        ListView dataList = (ListView) findViewById(R.id.list);
        dataList.setAdapter(adapter);
    }


    }
