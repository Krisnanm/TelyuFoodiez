package org.d3if3104.myapplication.ui.screen.penjual

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GreenButton

@Composable
fun ConditionPenjual(navController: NavHostController) {
    Scaffold(containerColor = Color.White, bottomBar = {}) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GreenButton)
                    .padding(bottom = 100.dp) // Increase padding to make space for the card
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = "",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(top = 20.dp) // Add top padding to ensure there's space between top and logo
                    )
                }
            }
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Box(modifier = Modifier.padding(top = 180.dp)) { // Adjust padding to position the card below the logo
                    MenuCard(
                        navController = navController,
                        R.string.data_dibutuhkan,
                        R.string.data1,
                        R.string.data2,
                        R.string.data3,
                        R.string.data4
                    )
                }
                Spacer(modifier = Modifier.height(32.dp)) // Increase the space between the card and the button
                LanjutButton(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCard(
    navController: NavHostController,
    shopName: Int,
    timeOpen: Int,
    data2: Int,
    data3: Int,
    data4: Int
) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            onClick = { navController.navigate(Screen.Terms.route) },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(shopName),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MenuItem(
                        imageRes = R.drawable.baseline_person_24,
                        textRes = timeOpen
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MenuItem(
                        imageRes = R.drawable.baseline_content_paste_24,
                        textRes = data2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MenuItem(
                        imageRes = R.drawable.baseline_menu_book_24,
                        textRes = data3
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MenuItem(
                        imageRes = R.drawable.baseline_access_time_24,
                        textRes = data4
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun MenuItem(imageRes: Int, textRes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(52.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = textRes),
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun LanjutButton(navController: NavHostController) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = { navController.navigate(Screen.Dashboard.route) },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            GreenButton, Color.White
        )
    ) {
        Text(
            text = stringResource(R.string.lanjut_button),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun DashboardScreenPrev() {
    ConditionPenjual(rememberNavController())
}