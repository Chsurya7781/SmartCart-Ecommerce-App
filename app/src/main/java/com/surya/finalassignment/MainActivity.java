package com.surya.finalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ProductAdapter.Onitemclicklistener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "productName";
    public static final String EXTRA_PRICE = "productPrice";
    public static final String EXTRA_DESCRIPTION = "productDescription";
    public static final String EXTRA_BRAND = "productBrand";
    public static final String EXTRA_RATING = "productRating";
    public static final String EXTRA_DISCOUNT = "productDiscount";
    public static final String EXTRA_CATEGORY = "productCategory";

    private final Handler handler=new Handler();
    private static final int AUTO_REFRESH_DELAY = 180000;
    private final Runnable autorefreshTask = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            parseJSON();
            handler.postDelayed(this,AUTO_REFRESH_DELAY);
            String currentTime = getCurrentTime();
            mLastRefreshedTime.setText("Last Refreshed: " + currentTime);
        }
    };

    private RecyclerView mRecyclerview;
    private ProductAdapter mExampleAdapter;
    private ArrayList<Product> mExampleList;
    private RequestQueue mRequestQueue;
    private TextView mLastRefreshedTime;


    @Override
    protected void onStart(){
        super.onStart();
        handler.postDelayed(autorefreshTask,AUTO_REFRESH_DELAY);
    }
    @Override
    protected void onStop(){
        super.onStop();
        handler.removeCallbacks(autorefreshTask);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            parseJSON();
            swipeRefreshLayout.setRefreshing(false);
        });
        mLastRefreshedTime = findViewById(R.id.last_refreshed_time);
        mRecyclerview = findViewById(R.id.recycler_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();


        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        mRequestQueue = volleySingleton.getRequestQueue();
        parseJSON();
    }

    private void  parseJSON(){
        String url = "https://dummyjson.com/products";

        @SuppressLint("SetTextI18n") JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("products");

                        for (int i=0;i< jsonArray.length();i++){
                            JSONObject product = jsonArray.getJSONObject(i);

                            String productName = product.getString("title");
                            String imagrUrl =product.getString("thumbnail");
                            int productPrice = product.getInt("price");
                            String productdescription = product.getString("description");
                            String productBrand = product.getString("brand");
                            int productRating = product.getInt("rating");
                            int productDiscount = product.getInt("discountPercentage");
                            String productCategory = product.getString("category");



                            mExampleList.add(new Product(imagrUrl,productName,productPrice,productdescription,productBrand,productRating,productDiscount,productCategory));


                        }
                        Collections.shuffle(mExampleList);
                        mExampleAdapter = new ProductAdapter(MainActivity.this,mExampleList);
                        mRecyclerview.setAdapter(mExampleAdapter);
                        mExampleAdapter.setOnItemclickListener(MainActivity.this);

                        String currentTime = getCurrentTime();
                        mLastRefreshedTime.setText("Last Refreshed: " + currentTime);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }, Throwable::printStackTrace);

        mRequestQueue.add(request);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    @Override
    public void Onitemclick(int position) {
        Intent detailIntent  = new Intent(this, DetailsActivity.class);
        Product clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.gettitle());
        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getprice());
        detailIntent.putExtra(EXTRA_DESCRIPTION,clickedItem.getdescription());
        detailIntent.putExtra(EXTRA_BRAND,clickedItem.getBrand());
        detailIntent.putExtra(EXTRA_RATING,clickedItem.getrating());
        detailIntent.putExtra(EXTRA_DISCOUNT,clickedItem.getdiscount());
        detailIntent.putExtra(EXTRA_CATEGORY,clickedItem.getcategory() );
        startActivity(detailIntent);
    }
}