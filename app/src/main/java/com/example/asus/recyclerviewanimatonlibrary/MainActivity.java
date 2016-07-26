package com.example.asus.recyclerviewanimatonlibrary;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Button btAdd;
    ArrayList<Student> listStudent;
    Random random=new Random();
    public static final String[] arrayName={"Son","Hung","Huy","Trung"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setAdapterDefault();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n=random.nextInt(10);
                String name="Nam"+n;
                adapter.add(name,0);
            }
        });
    }

    private void setAdapterDefault() {
        listStudent=new ArrayList<Student>();
        for(int i=0;i<arrayName.length;i++){
            listStudent.add(new Student(arrayName[i]));
        }
        adapter=new RecyclerViewAdapter(getApplicationContext(),listStudent, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student item, int currentPosition) {
                adapter.remove(currentPosition);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setupView() {
        btAdd=(Button)findViewById(R.id.button_add_student);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new ScaleInRightAnimator(new OvershootInterpolator(1f)));
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_sample);

        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
    }
}
