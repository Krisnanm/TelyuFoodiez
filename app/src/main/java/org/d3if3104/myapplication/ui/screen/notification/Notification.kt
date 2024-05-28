package org.d3if3104.myapplication.ui.screen.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Screen.Dashboard.route)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title ={
                    Text(text = stringResource(id = R.string.notification))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = Color.White,
                ),
            )
        }
    ){
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent (navController: NavController, modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 80.dp)
    ) {
        OrderCanceledCard()
        Spacer(modifier = Modifier.height(16.dp))
        OrderSuccessCard()
    }
}

@Composable
fun OrderCanceledCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Order canceled!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "You have canceled an order at Burger Hut. We apologize for your inconvenience. We will try to improve our service next time",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun OrderSuccessCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_check_circle_24), // Replace with your success icon
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Order Success",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Your order at Burger Hut was successful. Thank you for your order!",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}


@Preview
@Composable
fun NotificationPrev () {
    NotificationScreen(rememberNavController())
}