package org.d3if3104.myapplication.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.d3if3104.myapplication.navigation.listOfNavItems
import org.d3if3104.myapplication.ui.theme.BackgroundBar
import org.d3if3104.myapplication.ui.theme.Outline

@Composable
fun BottomNavBar(navController: NavHostController, currentRoute: String) {
    NavigationBar(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(1.dp, 1.dp)),
        containerColor = BackgroundBar) {
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
                        contentDescription = null,
                        tint = if (navItems.route == currentRoute) Outline else Color.Gray
                    )
                }
            )
        }
    }
}