package com.bourymbodj.employeedatabase;

/**
 * Created by bourymbodj on 16-07-14.
 */
public class Employee {

    private int id;
    private String name;
    private String age;
    private byte[] photo;



    public Employee(){

    }
    public Employee( String name, String age, byte[]photo) {
        this.name = name;
        this.photo = photo;
        this.age = age;
    }

    public Employee(int id, String name, String age, byte[]photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



}
