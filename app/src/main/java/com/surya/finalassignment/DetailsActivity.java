package com.surya.finalassignment;

import static com.surya.finalassignment.MainActivity.EXTRA_BRAND;
import static com.surya.finalassignment.MainActivity.EXTRA_CATEGORY;
import static com.surya.finalassignment.MainActivity.EXTRA_DESCRIPTION;
import static com.surya.finalassignment.MainActivity.EXTRA_DISCOUNT;
import static com.surya.finalassignment.MainActivity.EXTRA_NAME;
import static com.surya.finalassignment.MainActivity.EXTRA_PRICE;
import static com.surya.finalassignment.MainActivity.EXTRA_RATING;
import static com.surya.finalassignment.MainActivity.EXTRA_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String productName = intent.getStringExtra(EXTRA_NAME);
        int productPrice = intent.getIntExtra(EXTRA_PRICE, 0);
        String productDescription = intent.getStringExtra(EXTRA_DESCRIPTION);
        String Brand = intent.getStringExtra(EXTRA_BRAND);
        String Category = intent.getStringExtra(EXTRA_CATEGORY);
        int Rating = intent.getIntExtra(EXTRA_RATING, 0);
        int Discount = intent.getIntExtra(EXTRA_DISCOUNT, 0);

        ImageView ImageVoew = findViewById(R.id.image_view_detail);
        TextView textViewName = findViewById(R.id.tittle_tvdetail);
        TextView textViewPrice = findViewById(R.id.price_tvdetail);
        TextView textViewDescription = findViewById(R.id.description_tvdetail);
        TextView textViewBrand = findViewById(R.id.brand);
        TextView textViewRating = findViewById(R.id.Rating);
        TextView textViewDiscount = findViewById(R.id.discount);
        TextView textViewCategory = findViewById(R.id.categorydetail);
        Button button1 = findViewById(R.id.button);
        Button button2 =findViewById(R.id.button2);


        Picasso.get().load(imageUrl).fit().centerInside().into(ImageVoew);

        textViewName.setText(productName);
        textViewPrice.setText("$ "+ productPrice);
        textViewDescription.setText("description : " + productDescription);
        textViewBrand.setText("Brand : "+ Brand);
        textViewRating.setText("Rating : " +Rating);
        textViewDiscount.setText("Discount : "+ Discount+"%");
        textViewCategory.setText("Category : "+ Category);

        button1.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Item bought",Toast.LENGTH_SHORT).show());
    }}