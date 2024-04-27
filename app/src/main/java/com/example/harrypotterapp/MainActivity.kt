package com.example.harrypotterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.harrypotterapp.adapter.ViewPagerAdapter
import com.example.harrypotterapp.api.ApiClient
import com.example.harrypotterapp.api.ApiInterface
import com.example.harrypotterapp.fragments.HowardStaffFragment
import com.example.harrypotterapp.fragments.HowardStudentsFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var bar: Toolbar    // creating object of ToolBar
    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
        bar = findViewById(R.id.toolbar)
        setSupportActionBar(bar)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(HowardStudentsFragment(), "Howard Students")
        adapter.addFragment(HowardStaffFragment(), "Howard Staff")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)
    }
}