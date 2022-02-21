package com.itgenius.storeandroidkotlin.repository
import com.itgenius.storeandroidkotlin.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    // เก็บ Rest API Method จาก RetrofitService ทั้งหมดไว้ที่นี่
    fun getAllProducts() = retrofitService.getAllProducts()

}