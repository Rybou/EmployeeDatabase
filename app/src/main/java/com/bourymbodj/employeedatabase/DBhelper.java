package com.bourymbodj.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bourymbodj on 16-07-14.
 */
public class DBhelper extends SQLiteOpenHelper {

    //All static variables
    //Database Version
    private static final int DATABASE_VERSION=1;

    // Database name
    private static final String DATABASE_NAME= "employeedb";

    // Employee table name
    private static final String TABLE_EMPLOYEE = "employees";

    public static final String EMP_ID = "id";
    public static final String EMP_NAME = "name";
    public static final String EMP_AGE = "age";
    public static final String EMP_PHOTO = "photo";


    public DBhelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "(" + EMP_ID
                + "INTEGER PRIMARY KEY," + EMP_NAME + "TEXT," + EMP_AGE + "TEXT,"
                + EMP_PHOTO + "BLOB" + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_EMPLOYEE);

        // Create table again
        onCreate(db);

    }

    // All CRUD (Create, read , update, delete) operations

    public void addEmployee(Employee employee){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMP_NAME, employee.getName()); // Contact Name
        values.put(EMP_AGE, employee.getAge()); // Contact Age
        values.put(EMP_PHOTO, employee.getPhoto()); // Contact Photo


// Inserting Row
        db.insert(TABLE_EMPLOYEE, null, values);
        db.close(); // Closing database connection
    }
    // Getting single EMPLOYEE
    Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[] { EMP_ID,
                        EMP_NAME,EMP_AGE,  EMP_PHOTO }, EMP_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee employee = new Employee(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getBlob(3));

// return employee
        return employee;

    }

    // Getting All Contacts
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<Employee>();
// Select All Query
        String selectQuery = "SELECT * FROM employees ORDER BY name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setAge(cursor.getString(2));
                employee.setPhoto(cursor.getBlob(3));
// Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return employee list
        return employeeList;

    }

    // Updating single employee
    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMP_NAME, employee.getName());
        values.put(EMP_AGE, employee.getAge());
        values.put(EMP_PHOTO, employee.getPhoto());

// updating row
        return db.update(TABLE_EMPLOYEE, values,EMP_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });

    }

    // Deleting single employee
    public void deleteEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, EMP_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
        db.close();
    }

    // Getting employee Count
    public int getEmployeeCount() {
        String countQuery = "SELECT * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

}
