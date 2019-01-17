// нужно вызывать сервер перед запуском приложения, например через постман
// "https://spring-boot-mysql-server-part0.herokuapp.com/"

package com.tae.a82mytesttask1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkActivity extends AppCompatActivity {
    MarkInterface markInterface;
    EditText editFormId;
    EditText editFormTitle;
    EditText editFormDescription;
    Button buttonSave;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        editFormId = findViewById(R.id.editMarkId);
        editFormTitle = findViewById(R.id.editTitle);
        editFormDescription = findViewById(R.id.editDescription);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);

        markInterface = ApiUtils.getMarkInterface();
        final Bundle extras = getIntent().getExtras();

        final String markId = String.valueOf(extras.getInt("id"));
        String title = extras.getString("title");
        String author = extras.getString("author");
        String description = extras.getString("description");
        int published = extras.getInt("published");

        editFormId.setText(markId);
        editFormTitle.setText(title);
        editFormDescription.setText(description);

        if(markId!=null && markId.trim().length()>0){
            editFormId.setFocusable(false);
        } else{
            buttonDelete.setVisibility(View.INVISIBLE);
            editFormId.setVisibility(View.INVISIBLE);
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mark mark = new Mark();
                mark.setId(editFormId.getId());
                mark.setTitle(editFormTitle.getText().toString());
                mark.setDescription(editFormDescription.getText().toString());

                if(markId!=null && markId.trim().length()>0&&(Integer.parseInt(markId)!=0)){
                    updateMark(Integer.parseInt(markId), mark);
                } else{
                    addMark(mark);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String userId = String.valueOf(extras.getInt("id"));
                deleteMark(Integer.parseInt(userId));
            }
        });
    }

    public void addMark(Mark mark){
        Call<Mark> callMark = markInterface.addMark(mark);

        callMark.enqueue(new Callback<Mark>(){
            @Override
            public void onResponse(Call<Mark> call, Response<Mark> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MarkActivity.this, "Mark created successfully!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Mark> call, Throwable t) {
                Toast.makeText(MarkActivity.this, "Mark has not saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMark (final int id, final Mark mark){
        Call<Mark> callMark = markInterface.updateMark(id, mark);
        callMark.enqueue(new Callback<Mark>(){
            @Override
            public void onResponse(Call<Mark> call, Response<Mark> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MarkActivity.this, "Mark updated successful! " + mark.getId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mark> call, Throwable t) {
                Toast.makeText(MarkActivity.this, "Mark update was unsuccesful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteMark(final int id){
        Call<Mark> callMark = markInterface.deleteMark(id);
        System.out.println(id);
        callMark.enqueue(new Callback<Mark>(){
            @Override
            public void onResponse(Call<Mark> call, Response<Mark> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MarkActivity.this, "Deletion was successful! " + id, Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MarkActivity.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MarkActivity.this, "Deletion was not executed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
