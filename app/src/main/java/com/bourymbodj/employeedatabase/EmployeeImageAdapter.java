package com.bourymbodj.employeedatabase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by bourymbodj on 16-07-15.
 */
public class EmployeeImageAdapter extends ArrayAdapter<Employee> {
    Context context;
    int layoutResourceId;
    ArrayList<Employee> data = new ArrayList<Employee>();

    public EmployeeImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    public EmployeeImageAdapter(Context context, int layoutResourceId, ArrayList<Employee> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row= convertView;
        ImageHolder holder= null;
        if (row==null)
        {
         LayoutInflater inflater = ((Activity)context).getLayoutInflater();
         row= inflater.inflate(layoutResourceId,parent, false)  ;
         holder= new ImageHolder();
            holder.name = (TextView)row.findViewById(R.id.name);
            holder.age = (TextView)row.findViewById(R.id.age);
            holder.photo = (ImageView)row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
        Employee picture = data.get(position);
        holder.name.setText(picture.getName());
        holder.age.setText(picture .getAge());
//convert byte to bitmap take from contact class
        byte[] outImage=picture.getPhoto();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.photo.setImageBitmap(theImage);
        return row;
    }
    static class ImageHolder
    {
        ImageView photo;
        TextView name;
        TextView age;

    }
}




