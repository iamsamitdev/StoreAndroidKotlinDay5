package com.itgenius.storeandroidkotlin.model

data class ProductModel(
    val CategoryID: Int, // 1
    val CreatedDate: String, // 2021-11-21T17:00:00.000Z
    val ModifiedDate: String, // 2021-11-21T17:00:00.000Z
    val ProductID: Int, // 1
    val ProductName: String, // iPhone 13 Pro Max
    val ProductPicture: String, // https://www.mxphone.com/wp-content/uploads/2021/04/41117-79579-210401-iPhone12ProMax-xl-1200x675.jpg
    val UnitInStock: Int, // 3
    val UnitPrice: Int, // 55000
    val _id: String, // 61f632d956fa1ca490a99d34
    val category: List<Category>
)

data class Category(
    val CategoryID: Int, // 1
    val CategoryName: String, // Mobile
    val CategoryStatus: Int, // 1
    val CreatedDate: String, // 2021-11-21T17:00:00.000Z
    val ModifiedDate: String, // 2021-11-21T17:00:00.000Z
    val _id: String // 61dee1e8637992b10b3f28fd
)