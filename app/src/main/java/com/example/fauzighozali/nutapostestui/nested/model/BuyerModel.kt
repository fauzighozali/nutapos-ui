package com.example.fauzighozali.nutapostestui.nested.model

import com.example.fauzighozali.nutapostestui.nested.supportinterface.ItemInterface

class BuyerModel(var tanggal: String, total: Double?, var pelanggan: String, var meja: String, var driver: String, var pemesan: String, var jam: String) : ItemInterface {
    var total: Double? = 0.0

    override val isSection: Boolean
        get() = false

    init {
        this.total = total
    }
}
