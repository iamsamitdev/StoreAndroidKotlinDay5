package com.itgenius.storeandroidkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itgenius.storeandroidkotlin.model.ProductModel
import com.itgenius.storeandroidkotlin.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel(){

    val productList = MutableLiveData<List<ProductModel>>()
    val errorMessage = MutableLiveData<String>()

    // ส้ร้างฟังก์ชันดึงรายชื่อสินค้า
    fun getAllProducts(){
        val response = repository.getAllProducts()
        response.enqueue(object: Callback<List<ProductModel>> {

            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                productList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}