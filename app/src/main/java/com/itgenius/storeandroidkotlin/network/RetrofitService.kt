package com.itgenius.storeandroidkotlin.network
import com.itgenius.storeandroidkotlin.model.ProductModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    // สร้าง Method สำหรับเรียก API
    @GET("products")
    fun getAllProducts(): Call<List<ProductModel>>

    // สร้าง Object ของ Retrofit
    companion object {
        // กำหนด BaseURL ของ API
        private const val BaseURL = "https://smart-inventory-ep3.herokuapp.com/api/"

        // สร้าง object ของ Retrofit
        private var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService{
            if(retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}