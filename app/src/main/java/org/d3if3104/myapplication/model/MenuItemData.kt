package org.d3if3104.myapplication.model

import androidx.annotation.DrawableRes

data class MenuItemData(
    @DrawableRes val imageRes: Int,
    val title: String,
    val price: String,
    val imageUrl:String
)