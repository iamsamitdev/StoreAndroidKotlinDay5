package com.itgenius.storeandroidkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itgenius.storeandroidkotlin.databinding.AdapterProductBinding
import com.itgenius.storeandroidkotlin.model.ProductModel

class ProductAdapter : RecyclerView.Adapter<MainViewHolder>() {

    // สร้างตัวแปรไว้เก็บรายชื่อสินค้า
    private var products = mutableListOf<ProductModel>()

    fun setProductList(products: List<ProductModel>){
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = products[position]
        holder.binding.productName.text = product.ProductName
        holder.binding.categoryName.text = product.category[0].CategoryName
        holder.binding.unitPrice.text = product.UnitPrice.toString()
        Glide.with(holder.itemView.context).load(product.ProductPicture).into(holder.binding.imageProduct)
    }

    override fun getItemCount(): Int {
        return products.size
    }

}

class MainViewHolder(val binding: AdapterProductBinding): RecyclerView.ViewHolder(binding.root)