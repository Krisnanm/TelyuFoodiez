package org.d3if3104.myapplication.ui.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.component.BottomNavBar
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.DarkGreen
import org.d3if3104.myapplication.ui.theme.GrayIcon
import org.d3if3104.myapplication.ui.theme.GrayTextField
import org.d3if3104.myapplication.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        containerColor = Color.White, bottomBar = {
            BottomNavBar(navController = navController, Screen.Dashboard.route)
        }) {
        ScreenContent(navController,modifier = Modifier.padding(it))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(navController: NavHostController,modifier: Modifier) {
    var search by remember { mutableStateOf("") }
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            LightGreen, DarkGreen
                        )
                    ), shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .height(175.dp)
        ) {
            Column(modifier = Modifier.padding(30.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.greetings),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrayTextField
                    )
                    IconButton(onClick = {navController.navigate(Screen.Notification.route)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                            contentDescription = "notif",
                            tint = GrayTextField
                        )
                    }

                }
                Text(
                    text = stringResource(R.string.address),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = GrayTextField,
                    modifier = Modifier.alpha(0.7F)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.complete_address),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = GrayTextField
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    colors = colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = stringResource(
                                R.string.search_icon
                            ),
                            tint = GrayIcon
                        )
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp, bottom = 70.dp, top = 20.dp)
        )
        {
            item {
                MenuCard(
                    navController = navController,
                    R.drawable.food,
                    R.string.shop_name,
                    R.string.shop_time_open,
                    R.string.serve_time
                )
            }
            item {
                MenuCard(
                    navController = navController,
                    R.drawable.food,
                    R.string.shop_name,
                    R.string.shop_time_open,
                    R.string.serve_time
                )
            }
            item {
                MenuCard(
                    navController = navController,
                    R.drawable.food,
                    R.string.shop_name,
                    R.string.shop_time_open,
                    R.string.serve_time
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCard(navController: NavHostController,image: Int, shopName: Int, timeOpen: Int, serveTime: Int) {
    val context = LocalContext.current
    Card(
        onClick = {navController.navigate(Screen.DetailTenant.route)}, shape = RoundedCornerShape(20.dp), elevation = cardElevation(
            defaultElevation = 10.dp
        ),
        colors = cardColors(containerColor = Color.Transparent),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(
                R.string.food_image
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )
        Card(onClick = {}, colors = cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(shopName),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp, color = Color.Black
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(timeOpen),
                        fontSize = 12.sp,
                    )
                    Text(
                        text = stringResource(serveTime),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 24.dp))
}

@Preview
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen(rememberNavController())
}