package com.example.myapplication.ui.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.products.ProductFragment
import com.example.myapplication.ui.student.StudentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
//
//        val navController = this.findNavController(R.id.activity_main_nav_host_fragment)
//        // Find reference to bottom navigation view
//        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        // Hook your navigation controller to bottom navigation view
//        navView.setupWithNavController(navController)

        _binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.menu_user -> {
                    replaceFragment(ProductFragment())
                }
                R.id.menu_student -> {
                    replaceFragment(StudentFragment())
                }
            }

            true
        }





    }

    private fun replaceFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(_binding.fragmentContainerView.id , fragment)
        transaction.commit()
    }
}