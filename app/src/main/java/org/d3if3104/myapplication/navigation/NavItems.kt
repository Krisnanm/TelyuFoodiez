package org.d3if3104.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(
    NavItems(
        icon = Icons.Default.Home,
        route = Screen.Dashboard.route
    ),
    NavItems(
        icon = Icons.Default.ShoppingCart,
        route = Screen.Cart.route
    ),
    NavItems(
        icon = Icons.Default.Person,
        route = Screen.Profile.route
    )
)