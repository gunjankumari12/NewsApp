package com.kanagalatech.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kanagalatech.newsapp.Adapter.NewsAdapter;
import com.kanagalatech.newsapp.Response.Multimedium;
import com.kanagalatech.newsapp.Response.NewsList;
import com.kanagalatech.newsapp.Response.Result;
import com.kanagalatech.newsapp.Util.APIService;
import com.kanagalatech.newsapp.Util.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewSouresList extends AppCompatActivity {

    private RecyclerView mRecyclerView1;
    private List<Result> mResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_soures_list);
        mRecyclerView1 = findViewById(R.id.recyler_view);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView1.setHasFixedSize(true);
        newCarBudgetdetails();
    }

    private void newCarBudgetdetails() {
        String apikey = "0eBNcNH4cSfWXOCwooSXheVrjG2NNnBW";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);
        //defining the call
        Call<NewsList> call = service.news_list(apikey);
        //calling the api
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, retrofit2.Response<NewsList> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        mResultList = new ArrayList<>();
                        for (int i = 0; i < response.body().getResults().size(); i++) {
                            //progressDialog.dismiss();
                            String price = response.body().getResults().get(i).getTitle().toString();
                            System.out.println("getsvxsjcvsjcvjcs   "+price);
                            Result mResult = response.body().getResults().get(i);

                            mResult.getSection().equals("us");
                            mResult.getTitle();
                            mResultList.add( mResult);
                            mRecyclerView1.setLayoutManager(new GridLayoutManager(NewSouresList.this, 1));
                            mRecyclerView1.setHasFixedSize(true);
                            NewsAdapter newsAdapter= new NewsAdapter(NewSouresList.this, mResultList);
                            mRecyclerView1.setAdapter(newsAdapter);
                        }
                        System.out.println("*****gndhbhdccdvcdvcv***********"  +    response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Internel Server error", Toast.LENGTH_LONG).show();
                // Log.e(TAG, "error" + t.getMessage());
                System.out.println("*****gndhbhdccdvcdvcv" + t);

            }
        });
    }

}