// нужно вызывать сервер перед запуском приложения, например через постман
// "https://spring-boot-mysql-server-part0.herokuapp.com/"
package com.tae.a82mytesttask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnAddMark;
    Button btnGetMarkList;

    MarkInterface markInterface;
    ArrayList<Mark> listOfMarks = new ArrayList<>();
    RecyclerView recyclerView;
    RecycAdapter recycAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddMark = findViewById(R.id.btnAddMark);
        btnGetMarkList = findViewById(R.id.btnGetMarkList);
        btnGetMarkList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getMarkList();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recycAdapter = new RecycAdapter(this, listOfMarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycAdapter);
        btnAddMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MarkActivity.class);
                intent.putExtra("markName", "");
                startActivity(intent);
            }
        });

        markInterface = ApiUtils.getMarkInterface();
    }

    public void getMarkList(){
        Call<ArrayList<Mark>> call = markInterface.getMarks();
        call.enqueue(new Callback<ArrayList<Mark>>(){
            @Override
            public void onResponse(Call<ArrayList<Mark>> call, Response<ArrayList<Mark>> response) {
                if(response.isSuccessful()){
                    listOfMarks = response.body();
                }
                recycAdapter.setData(listOfMarks);

            }

            @Override
            public void onFailure(Call<ArrayList<Mark>> call, Throwable t) {
            }
        });
    }
}
