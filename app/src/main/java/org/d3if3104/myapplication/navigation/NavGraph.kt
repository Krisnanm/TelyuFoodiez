package org.d3if3104.myapplication.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3104.myapplication.ui.screen.auth.LoginScreen
import org.d3if3104.myapplication.ui.screen.auth.Register
import org.d3if3104.myapplication.ui.screen.pembeli.checkout.CheckoutScreen
import org.d3if3104.myapplication.ui.screen.dashboard.DashboardScreen
import org.d3if3104.myapplication.ui.screen.pembeli.detail.DetailOrderScreen
import org.d3if3104.myapplication.ui.screen.pembeli.detail.DetailTenant
import org.d3if3104.myapplication.ui.screen.notification.NotificationScreen
import org.d3if3104.myapplication.ui.screen.penjual.ConditionPenjual
import org.d3if3104.myapplication.ui.screen.penjual.TermsPenjual
import org.d3if3104.myapplication.ui.screen.process.OrderProcess
import org.d3if3104.myapplication.ui.screen.pembeli.profile.ProfileScreen
import org.d3if3104.myapplication.ui.screen.pembeli.profile.TermsConditionScreen
import org.d3if3104.myapplication.ui.screen.role.RoleScreen


@Composable
fun SetupNavGraph() {
    val navController: NavHostController = rememberNavController()
    var showBottomNavigation by remember {
        mutableStateOf(false)
    }
    Scaffold(
        bottomBar = {
            if (showBottomNavigation) {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    listOfNavItems.forEach { navItems ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItems.route } == true,
                            onClick = {
                                navController.navigate(navItems.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navItems.icon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Role.route,
            modifier = Modifier.padding(paddingValues)
        ){
            composable(route = Screen.Login.route
            ) { backStackEntry ->
                LoginScreen(navController)
            }
            composable(route = Screen.Register.route,
                arguments = listOf(
                    navArgument("role") {type = NavType.StringType}
                )
            ) { backStackEntry ->
                val role = backStackEntry.arguments?.getString("role")
                Register(role, navController)
            }
            composable(route = Screen.Dashboard.route) {
                DashboardScreen(navController)
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(navController)
            }
            composable(route = Screen.Terms.route) {
                TermsConditionScreen(navController)
            }
            composable(route = Screen.DetailOrder.route) {
                DetailOrderScreen(navController)
            }
            composable(route = Screen.Notification.route) {
                NotificationScreen(navController)
            }
            composable(route = Screen.DetailTenant.route) {
                DetailTenant(navController)
            }
            composable(route = Screen.OrderProcess.route) {
                OrderProcess(navController)
            }
            composable(route = Screen.Cart.route) {
                CheckoutScreen(navController)
            }
            composable(route = Screen.TermsPenjual.route) {
                TermsPenjual(navController)
            }
            composable(route = Screen.ConditionPenjual.route) {
                ConditionPenjual(navController)
            }
            composable(route = Screen.Role.route) {
                RoleScreen(navController)
            }
        }
        LaunchedEffect(navController.currentDestination) {
            val currentDestination = navController.currentDestination
            showBottomNavigation = listOf(
                Screen.Dashboard.route,
                Screen.Cart.route,
                Screen.Profile.route
            ).contains(currentDestination?.route)
        }
    }
}

