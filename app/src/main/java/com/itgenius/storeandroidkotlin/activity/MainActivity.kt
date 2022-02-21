package com.itgenius.storeandroidkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.itgenius.storeandroidkotlin.R
import com.itgenius.storeandroidkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // การสร้างตัวแปรสำหรับทำงานกับ View Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // หากต้องการ binding id ใดๆ ใน xml
        // binding.mainAppbar.setOnClickListener{}

        // กำหนดค่าเริ่มต้นให้กับ navController
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // เรียกทำงานกับ Toolbar
        setSupportActionBar(binding.mainToolbar)

        // กำหนด Fragment ที่ไม่ต้องการให้แสดงปุ่ม Back
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.productFragment,
            R.id.reportFragment,
            R.id.notificationFragment,
            R.id.accountFragment,
            R.id.settingFragment
        ).setOpenableLayout(binding.mainDrawerLayout).build()

        // กำหนด Toolbar ให้แสดง icon เมนู
        setupActionBarWithNavController(navController, appBarConfiguration)

        // เรียกใช้งาน visibilityNavElements
        visibilityNavElements(navController)

    }

    // สร้างฟังก์ชันสำหรับเรียกแสดงผล Navigation
    private fun visibilityNavElements(navController: NavController){
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.aboutFragment -> hideBothNavigation() // ซ่อนทั้งเมนูด้านข้างและด้านล่าง
                R.id.serviceFragment -> hideBothNavigation() // ซ่อนทั้งเมนูด้านข้างและด้านล่าง
                R.id.settingFragment -> hideBottomNavigation() // ซ่อนเฉพาะเมนูด้านล่าง
                R.id.profileFragment -> hideBothNavigation() // ซ่อนทั้งเมนูด้านข้างและด้านล่าง
                else -> showBothNavigation()
            }
        }
    }

    // ซ่อนการแสดงผลทั้งเมนูด้านข้าง (Drawer) และด้านล่าง (Bottom)
    private fun hideBothNavigation(){
        binding.mainBottomNavigationVeiw.visibility = View.GONE // ซ่อนเมนูด้านล่าง
        binding.mainNavigationView.visibility = View.GONE // ซ่อนเมนูด้านข้าง
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) // ล็อกไม่ได้ผู้ใช้เรียกเมนูด้านข้างได้
    }

    // ซ่อนเฉพาะเมนูด้านล่าง (Bottom)
    private fun hideBottomNavigation(){
        binding.mainBottomNavigationVeiw.visibility = View.GONE // ซ่อนเมนูด้านล่าง
        binding.mainNavigationView.visibility = View.VISIBLE // แสดงเมนูด้านข้าง
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        //Setup Drawer navigation with navController
        binding.mainNavigationView.setupWithNavController(navController)
    }

    // แสดงผลทั้งเมนูด้านบนและด้านล่าง
    private fun showBothNavigation(){
        binding.mainBottomNavigationVeiw.visibility = View.VISIBLE
        binding.mainNavigationView.visibility = View.VISIBLE
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        // เรียกใช้งาน Drawer navigation
        binding.mainNavigationView.setupWithNavController(navController)

        // เรียกใช้งาน Bottom navigation
        binding.mainBottomNavigationVeiw.setupWithNavController(navController)
    }

    // สร้างฟังก์ชันเรียกซ่อน / แสดงเมนู
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }
}