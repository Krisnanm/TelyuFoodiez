package org.d3if3104.myapplication.ui.screen.role

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun RoleScreen(navController: NavHostController) {
    Scaffold(containerColor = Color.White, bottomBar = {}) {
        ScreenContent(navController,modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController,modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "",
            modifier = Modifier.size(220.dp)
        )
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.choosing),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            item { // Tambahkan item untuk setiap MenuCard
                MenuCard(
                    navController = navController,
                    R.string.penjual,
                    R.string.desc_individual,
                    onClick = { navController.navigate(Screen.Register.withRole("penjual"))}
                )
            }
            item {
                MenuCard(
                    navController = navController,
                    R.string.pembeli,
                    R.string.desc_franchise,
                    onClick = { navController.navigate(Screen.Register.withRole("pembeli"))}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCard(navController: NavHostController,shopName: Int, timeOpen: Int, onClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        elevation = cardElevation(defaultElevation = 8.dp),
        colors = cardColors(containerColor = Color.Transparent),
    ) {
        Card(
            onClick = onClick,
            colors = cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.store_app),
                    contentDescription = "",
                    modifier = Modifier.size(82.dp)
                )
                Spacer(modifier = Modifier.width(1.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(shopName),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text(
                                text = stringResource(timeOpen),
                                fontSize = 13.sp,
                                color = Color.LightGray

                            )
                            Text(
                                text = "",
                                fontSize = 13.sp
                            )
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = "",
                            modifier = Modifier
                                .offset(y = (-10).dp)

                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 24.dp))
}



@Preview
@Composable
private fun DashboardScreenPrev() {
    RoleScreen(rememberNavController())
}