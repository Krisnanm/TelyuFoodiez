package org.d3if3104.myapplication.navigation


sealed class Screen (val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register/{role}"){
        fun withRole(role: String) = "register/$role"
    }
    data object Dashboard: Screen("dashboard")
    data object Cart: Screen("cart")
    data object Profile: Screen("profile")
    data object Terms: Screen("terms")
    data object DetailOrder: Screen("detailorder")
    data object Notification: Screen("notification")
    data object DetailTenant: Screen("detailtenant")
    data object OrderProcess: Screen("orderprocess")
    data object Checkout: Screen("checkout")
    data object Role: Screen("role")
    data object TermsPenjual: Screen("termspenjual")
    data object ConditionPenjual: Screen("Condition")
}