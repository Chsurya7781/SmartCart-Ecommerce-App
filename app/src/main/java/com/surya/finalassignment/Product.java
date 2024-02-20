package com.surya.finalassignment;

public class Product {
   private final String mImageUrl;
    private final String mtitle;
    private final int mprice;
    private final String mdescription ;
    private final String mBrand ;
    private final int mrating;
    private final int mdiscount;
    private final String mCategory ;

    public Product(String imageUrl,String title,int price,String description,String Brand,int rating,int discount,String category){
        mImageUrl = imageUrl;
        mtitle = title;
        mprice = price;
        mdescription = description;
        mBrand = Brand;
        mrating = rating;
        mdiscount = discount;
        mCategory = category;
    }
    public String getImageUrl(){
        return mImageUrl;
    }

    public  String gettitle(){
        return mtitle;
    }
    public int getprice(){
        return mprice;
    }
    public  String getdescription(){
        return mdescription;
    }
    public  String getBrand(){
        return mBrand;
    }

    public  String getcategory(){
        return mCategory;
    }
    public  int getdiscount()  {
        return mdiscount ;
    }
    public  int getrating(){
        return mrating ;
    }

}
