package com.example.fauzighozali.nutapostestui.nested.model

import com.example.fauzighozali.nutapostestui.nested.supportinterface.ItemInterface

class GroupTitleModel(var title: String) : ItemInterface {

    override val isSection: Boolean
        get() = true
}
