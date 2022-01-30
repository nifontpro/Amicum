package com.amicum.report_presentation.home.drawer

import androidx.annotation.DrawableRes

data class NavDrawerItem(
    val route: String = "",
    @DrawableRes val icon: Int = 0,
    val title: String = "",
    val isLine: Boolean = false

)