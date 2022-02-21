package com.itgenius.storeandroidkotlin.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itgenius.storeandroidkotlin.adapter.ProductAdapter
import com.itgenius.storeandroidkotlin.databinding.FragmentProductBinding
import com.itgenius.storeandroidkotlin.network.RetrofitService
import com.itgenius.storeandroidkotlin.repository.MainRepository
import com.itgenius.storeandroidkotlin.viewmodel.MainViewModel
import com.itgenius.storeandroidkotlin.viewmodelfactory.MainViewModelFactory

class ProductFragment : Fragment() {

    // สร้างตัวแปรสำหรับการ binding
    private lateinit var binding: FragmentProductBinding

    // สร้างตัวแปรสำหรับเรียกใช้ viewModel
    lateinit var viewModel: MainViewModel

    // สร้างตัวแปรสำหรับเรียกใช้ RetrofitService
    private val retrofitService = RetrofitService.getInstance()

    // สร้างตัวแปรเรียกใช้ adapter
    val adapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner, Observer {
            adapter.setProductList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d("Message","Error Call ViewModel")
        })

        viewModel.getAllProducts()

    }

}