package com.example.my_bookmark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   ArrayList<String> title= new ArrayList<>();
   ArrayList<String> url=new ArrayList<>();
   ArrayAdapter <String>ad;
   EditText t1,t2;
   Button b1,b2;
   ListView list;
   //AlertDialog alertdialog;

    GestureDetector gesdec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.editTextTextPersonName);
        t2=findViewById(R.id.editTextTextPersonName2);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        list=findViewById(R.id.listview);

        title = mydata.readTitle(this);
        url = mydata.readURL(this);
        ad=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,title);
        list.setAdapter(ad);

        gesdec = new GestureDetector(MainActivity.this, new MyGestureListener());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titl=t1.getText().toString();
                String ur=t2.getText().toString();
                if(!titl.isEmpty() && !ur.isEmpty()) {

                title.add(titl);
                url.add(ur);
                }
                t1.setText("");
                t2.setText("");
                mydata.writeData(title,url,getApplicationContext());
                ad.notifyDataSetChanged();


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.clear();
                url.clear();
                ad.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link=url.get(i);
                Uri webpage = Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW,webpage));

            }

        });
         list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                 AlertDialog.Builder db = new AlertDialog.Builder(MainActivity.this);
                 db.setTitle("Delete");
                 db.setMessage("Are you sure?");
                 db.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                     }
                 });
                 db.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int ii) {
                         title.remove(title.get(i));
                         url.remove(url.get(i));
                         ad.notifyDataSetChanged();
                     }
                 }).show();
                 db.create();
                 return false;

             }
         });



    }
  private class MyGestureListener extends  GestureDetector.SimpleOnGestureListener{
      @Override
      public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
           float diffx=e2.getX() - e1.getX();
           float diffy=e2.getY() - e1.getY();

           if(Math.abs(diffx) > Math.abs(diffy)){
               if(Math.abs(diffx) > 100 && Math.abs(velocityX)>100 ){
                   if(diffx > 0){
                       Toast.makeText(getApplicationContext(), "aaj.kj", Toast.LENGTH_SHORT).show();
                   }
               }
           }
          return true;
      }
  }
}