package com.itgenius.storeandroidkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.itgenius.storeandroidkotlin.R
import com.itgenius.storeandroidkotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // สร้างตัวแปรสำหรับไว้ binding view
    private lateinit var binding: FragmentHomeBinding
    // สร้างตัวแปรสำหรับเรียกใช้ Navigation Controller
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // กำหนดค่าเริ่มต้นให้ navController
        navController = findNavController()

        // ทำการเขียน event click ปุ่ม home_button_to_profile
         binding.homeButtonToProfile.setOnClickListener {
             // เรียกเปิด Fragment profileFragment
             // navController.navigate(R.id.profileFragment)
             val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
             navController.navigate(action)

         }
    }
}