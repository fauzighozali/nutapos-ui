package com.example.fauzighozali.nutapostestui.nested.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View

import com.example.fauzighozali.nutapostestui.R
import com.example.fauzighozali.nutapostestui.nested.adapter.MyAdapter
import com.example.fauzighozali.nutapostestui.nested.model.GroupTitleModel
import com.example.fauzighozali.nutapostestui.nested.model.BuyerModel
import com.example.fauzighozali.nutapostestui.nested.supportinterface.ItemInterface
import com.example.fauzighozali.nutapostestui.nested.supportinterface.MyMediatorInterface
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainTwoActivity : AppCompatActivity(), MyMediatorInterface {

    private var mAdapter: MyAdapter? = null
    private var mUsersAndSectionList: ArrayList<ItemInterface>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)

        val usersList = ArrayList<BuyerModel>()

        usersList.add(BuyerModel("Senin, 5 November 2018", 44250.0, "Bapak Alex", "2", "0", "0", "10:21"))
        usersList.add(BuyerModel("Senin, 5 November 2018", 50000.0, "0", "0", "Sukijo", "Ibu Anis", "10:15"))
        usersList.add(BuyerModel("Senin, 5 November 2018", 150000.0, "Bapak Sony", "0", "0", "0", "10:10"))
        usersList.add(BuyerModel("Minggu, 4 November 2018", 150000.0, "Ibu Amel", "0", "0", "0", "10:10"))

        mUsersAndSectionList = ArrayList()
        getSectionalList(usersList)

        val recyclerView = findViewById<View>(R.id.rv_my_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@MainTwoActivity)
        mAdapter = MyAdapter(mUsersAndSectionList!!, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

    private fun getSectionalList(usersList: ArrayList<BuyerModel>) {

        Collections.sort(usersList) { user1, user2 -> if (user1.tanggal.compareTo(user2.tanggal) > 0) 1 else 0 }

        var lastHeader = ""

        val size = usersList.size

        for (i in 0 until size) {

            val user = usersList[i]
            val header = user.tanggal

            if (!TextUtils.equals(lastHeader, header)) {
                lastHeader = header

                mUsersAndSectionList!!.add(GroupTitleModel(header))
            }

            mUsersAndSectionList!!.add(user)
        }
    }

    override fun userItemClick(pos: Int) {

    }
}
