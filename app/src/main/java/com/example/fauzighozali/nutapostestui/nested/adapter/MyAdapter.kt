package com.example.fauzighozali.nutapostestui.nested.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.example.fauzighozali.nutapostestui.R
import com.example.fauzighozali.nutapostestui.nested.model.GroupTitleModel
import com.example.fauzighozali.nutapostestui.nested.model.BuyerModel
import com.example.fauzighozali.nutapostestui.nested.activity.MainTwoActivity
import com.example.fauzighozali.nutapostestui.nested.supportinterface.ItemInterface

import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.ArrayList

class MyAdapter(internal var mUsersAndSectionList: ArrayList<ItemInterface>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal var mContextWeakReference: WeakReference<Context>

    init {

        this.mContextWeakReference = WeakReference(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val context = mContextWeakReference.get()
        return if (viewType == SECTION_VIEW) {
            SectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_group_title, parent, false))
        } else MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_user_item, parent, false), context!!)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mUsersAndSectionList[position].isSection) {
            SECTION_VIEW
        } else {
            CONTENT_VIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val context = mContextWeakReference.get() ?: return

        if (SECTION_VIEW == getItemViewType(position)) {

            val sectionViewHolder = holder as SectionViewHolder
            val sectionItem = mUsersAndSectionList[position] as GroupTitleModel
            val currentUser = mUsersAndSectionList[position] as BuyerModel

            sectionViewHolder.title.text = sectionItem.title
            sectionViewHolder.totalPenjualan.text = currentUser.total.toString()
            return
        }

        val myViewHolder = holder as MyViewHolder

        val currentUser = mUsersAndSectionList[position] as BuyerModel

        var formatter: NumberFormat? = null
        var number: String? = null

        formatter =  DecimalFormat("#,###,###")

        myViewHolder.TvPelanggan.text = currentUser.pelanggan
        myViewHolder.TvMeja.text = currentUser.meja
        number = formatter.format(currentUser.total)
        myViewHolder.TvTotal.text = number
        myViewHolder.TvJam.text = currentUser.jam
        myViewHolder.TvDriver.text = currentUser.driver
        myViewHolder.TvPemesan.text = currentUser.pemesan

        if (currentUser.pelanggan == "0") {
            myViewHolder.TvInfoPelanggan.visibility = View.GONE
            myViewHolder.TvPelanggan.visibility = View.GONE
        } else if (currentUser.driver == "0" && currentUser.pemesan == "0") {
            myViewHolder.llThree.visibility = View.GONE
        }

        if (currentUser.meja == "0") {
            myViewHolder.TvInfoMeja.visibility = View.GONE
            myViewHolder.TvMeja.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return mUsersAndSectionList.size
    }

    class MyViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        var TvPelanggan: TextView
        var TvMeja: TextView
        var TvTotal: TextView
        var TvJam: TextView
        var TvInfoMeja: TextView
        var TvDriver: TextView
        var TvPemesan: TextView
        var TvInfoPelanggan: TextView
        var ll: LinearLayout
        var llThree: LinearLayout

        init {

            TvPelanggan = itemView.findViewById<View>(R.id.tv_pelanggan) as TextView
            TvMeja = itemView.findViewById<View>(R.id.tv_meja) as TextView
            TvTotal = itemView.findViewById<View>(R.id.tv_total) as TextView
            TvJam = itemView.findViewById<View>(R.id.tv_jam) as TextView
            TvInfoMeja = itemView.findViewById<View>(R.id.tv_infoMeja) as TextView
            TvDriver = itemView.findViewById<View>(R.id.tv_driver) as TextView
            TvPemesan = itemView.findViewById<View>(R.id.tv_pemesan) as TextView
            TvInfoPelanggan = itemView.findViewById<View>(R.id.tv_infoPelanggan) as TextView

            ll = itemView.findViewById<View>(R.id.ll_layout) as LinearLayout
            llThree = itemView.findViewById<View>(R.id.ll_three) as LinearLayout

            ll.setOnClickListener { (context as MainTwoActivity).userItemClick(adapterPosition) }
        }
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var totalPenjualan: TextView



        init {
            title = itemView.findViewById<View>(R.id.tv_group_title) as TextView
            totalPenjualan = itemView.findViewById<View>(R.id.tv_total_penjualan) as TextView
        }
    }

    companion object {

        val SECTION_VIEW = 0
        val CONTENT_VIEW = 1
    }
}
