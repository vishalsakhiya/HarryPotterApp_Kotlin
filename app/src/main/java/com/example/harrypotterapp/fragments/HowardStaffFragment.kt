package com.example.harrypotterapp.fragments

import android.app.SearchManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterapp.R
import com.example.harrypotterapp.adapter.HowardListAdapter
import com.example.harrypotterapp.api.HowardResponse
import com.example.harrypotterapp.api.HowardViewModel

class HowardStaffFragment : Fragment() {
    private var howardViewModel: HowardViewModel? = null
    private var adapter: HowardListAdapter? = null
    private var rvStudents: RecyclerView? = null
    private var progressStudents: ProgressBar? = null
    private var staffList: ArrayList<HowardResponse>? = null
    private var filteredStaffList: List<HowardResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        howardViewModel = ViewModelProvider(this)[HowardViewModel::class.java]
        howardViewModel!!.fetchAllStaff()

        howardViewModel!!.postModelListLiveData?.observe(this, Observer {
            if (it!=null){
                rvStudents?.visibility = View.VISIBLE
                staffList = it as ArrayList<HowardResponse>
                adapter?.setData(staffList as ArrayList<HowardResponse>)
            }
            progressStudents?.visibility = View.GONE
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_howard_student, container, false) as ViewGroup
        progressStudents = root.findViewById<View>(R.id.progress_students) as ProgressBar
        rvStudents = root.findViewById<View>(R.id.rv_Students) as RecyclerView
        adapter = HowardListAdapter()
        rvStudents?.layoutManager = LinearLayoutManager(activity)
        rvStudents?.adapter = adapter
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.search_menu, menu)
        val searchView: SearchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = activity?.getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredStaffList = staffList?.filter { character -> character.name.contains(newText.toString(), ignoreCase = true) }
                adapter?.setData(filteredStaffList as ArrayList<HowardResponse>)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}