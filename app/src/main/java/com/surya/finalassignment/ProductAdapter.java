package com.surya.finalassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productViewholder> {
    private final Context mcontext;
    private final ArrayList<Product> mExamplelist;
    private Onitemclicklistener mListener;

    public interface Onitemclicklistener{
        void Onitemclick(int position);
    }

    public void setOnItemclickListener(Onitemclicklistener listener){
        mListener = listener;
    }

    public ProductAdapter(Context context, ArrayList<Product>examplelist){
        mcontext =context;
        mExamplelist = examplelist;
    }

    @NonNull
    @Override
    public productViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.product_item,parent,false);
        return new productViewholder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull productViewholder holder, int position) {
        Product currentItem = mExamplelist.get(position);
        String imageUrl = currentItem.getImageUrl();
        String titlename = currentItem.gettitle();
        int productprice = currentItem.getprice();
        int productdicount  = currentItem.getdiscount();


        holder.mTextviewtittle.setText(titlename);
        holder.mTextviewprice.setText("$ " + productprice);
        holder.mProductdiscount.setText(productdicount+"% OFF");

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }


    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class productViewholder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextviewtittle;
        public TextView mTextviewprice;
        public TextView mProductdiscount;





        public productViewholder(@NonNull View itemView) {
            super(itemView);
            mImageView =  itemView.findViewById(R.id.image_view);
            mTextviewtittle = itemView.findViewById(R.id.tittle_tv);
            mTextviewprice = itemView.findViewById(R.id.price_tv);
            mProductdiscount = itemView.findViewById(R.id.OFF);



            itemView.setOnClickListener(v -> {
                if(mListener != null){
                    int position = getAdapterPosition();
                    if(position!= RecyclerView.NO_POSITION){
                        mListener.Onitemclick(position);
                    }
                }
            });
        }
    }
}
