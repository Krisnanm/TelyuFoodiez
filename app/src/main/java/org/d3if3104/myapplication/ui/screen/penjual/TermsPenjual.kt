package org.d3if3104.myapplication.ui.screen.penjual

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
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
import org.d3if3104.myapplication.ui.theme.GreenButton

@Composable
fun TermsPenjual(navController: NavHostController) {
    Scaffold(containerColor = Color.White, bottomBar = {}) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GreenButton)
                    .padding(bottom = 80.dp) // increased padding to accommodate the overlay card
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp)) // additional spacer to provide space between logo and card
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(modifier = Modifier.padding(top = 180.dp)) { // position card partially over green background
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MenuCard(
                            navController = navController,
                            R.string.pendaftaran_persetujuan,
                            R.string.terms_desc,
                            R.string.kebijakan_privasi,
                            R.string.privasi_desc
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        NextButton(navController)
                    }
                }
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
    kebijakanPrivasi: Int,
    privasiDesc: Int
) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        elevation = cardElevation(defaultElevation = 8.dp),
        colors = cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            onClick = { navController.navigate(Screen.Dashboard.route) },
            colors = cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(shopName),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(timeOpen),
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(kebijakanPrivasi),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(privasiDesc),
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun NextButton(navController: NavHostController) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = {navController.navigate(Screen.ConditionPenjual.route)},
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
private fun TermsPenjualScreenPrev() {
    TermsPenjual(rememberNavController())
}