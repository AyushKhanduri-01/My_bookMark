package com.example.my_bookmark;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class mydata {
    public  static final String titlefile="mydata1";
    public  static  final String urlFile="mydata2";
    public  static  void writeData(ArrayList <String> title,ArrayList<String> url,Context context){
        try {
            FileOutputStream fos1=context.openFileOutput(titlefile,Context.MODE_PRIVATE);
            FileOutputStream fos2=context.openFileOutput(urlFile,Context.MODE_PRIVATE);
            ObjectOutputStream oas1=new ObjectOutputStream(fos1);
            ObjectOutputStream oas2=new ObjectOutputStream(fos2);
            oas1.writeObject(title);
            oas2.writeObject(url);
            oas1.close();
            oas2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static   ArrayList<String> readTitle(Context context){
       ArrayList <String> list=null;
        try {
            FileInputStream fis=context.openFileInput(titlefile);
            ObjectInputStream ois=new ObjectInputStream(fis);
            list=(ArrayList<String>) ois.readObject();

        } catch (FileNotFoundException e) {
            list =new ArrayList<>();
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public  static   ArrayList<String> readURL(Context context){
        ArrayList <String> list=null;
        try {
            FileInputStream fis=context.openFileInput(urlFile);
            ObjectInputStream ois=new ObjectInputStream(fis);
            list=(ArrayList<String>)ois.readObject();

        } catch (FileNotFoundException e) {
            list=new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
