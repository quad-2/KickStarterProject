package com.example.quad2.kickstarterprojects.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quad2.kickstarterprojects.R;
import com.example.quad2.kickstarterprojects.adapters.ProjectAdapter;
import com.example.quad2.kickstarterprojects.interfaces.ApiInterface;
import com.example.quad2.kickstarterprojects.pojo.ApiDatum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://starlord.hackerearth.com/";
    private RecyclerView projectRV;
    private ProjectAdapter adapter;
    private List<ApiDatum> projectData = new ArrayList<>();
    private ProgressDialog dialog;
    private Button searchBtn, resetBtn;
    private AppCompatEditText userInput;
    private List<ApiDatum> filterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        searchBtn = (Button) findViewById(R.id.search_btn);
        userInput = (AppCompatEditText) findViewById(R.id.user_input);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);

        //initializing RecyclerView
        projectRV = (RecyclerView) findViewById(R.id.project_rv);
        getProjects();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.getText().toString()!=null ){
                    String text = userInput.getText().toString();
                    if (text.length() == 0){
                        userInput.setError("Please enter a word");
                    } else if (text.contains(" ")){
                        userInput.setError("It can't contain spaces!");
                    } else {
                        dialog.show();
                        searchProject(text);
                    }
                } else{
                    Toast.makeText(MainActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerView(projectData);
            }
        });
    }

    // initializes the RecyclerView Adapter and completes RecyclerView setup
    public void setRecyclerView( List<ApiDatum> list) {
        projectRV.setHasFixedSize(true);
        adapter = new ProjectAdapter(list, MainActivity.this);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        projectRV.setLayoutManager(llm);
        projectRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // method that makes API call using retrofit to get data
    public void getProjects() {
        dialog.show();
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(15, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<ApiDatum>> call = apiInterface.getData();
        call.enqueue(new Callback<List<ApiDatum>>() {
            @Override
            public void onResponse(Call<List<ApiDatum>> call, Response<List<ApiDatum>> response) {
                dialog.dismiss();

                //checking if the response and response body is not null
                if (response != null && response.body() != null) {
                    projectData = response.body();
                    setRecyclerView(projectData);
                }
            }

            @Override
            public void onFailure(Call<List<ApiDatum>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchProject(String searchText){
        filterList.clear();
        searchText = searchText.toLowerCase();
        for (ApiDatum data : projectData){
            if (data.getTitle().toLowerCase().contains(searchText)){
                filterList.add(data);
            }
        }
        dialog.dismiss();
        setRecyclerView(filterList);
    }
}
