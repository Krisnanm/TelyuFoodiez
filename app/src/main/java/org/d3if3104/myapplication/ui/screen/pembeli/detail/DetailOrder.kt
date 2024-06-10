package org.d3if3104.myapplication.ui.screen.pembeli.detail

import android.content.ContentValues.TAG
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.firebase.addToCart
import org.d3if3104.myapplication.model.CartItem
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.LightGreen

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailOrderScreen(navController: NavHostController) {
    val foodName = stringResource(id = R.string.food) // Ambil nilai stringResource di dalam komposisi
    val foodPrice = 27000 // Sesuaikan harga makanan

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Dashboard.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.detailorder))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = Color.White,
                ),
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Harga",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Rp $foodPrice",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .width(200.dp),
                        onClick = {
                            val item = CartItem(
                                name = foodName,
                                price = foodPrice,
                                quantity = 1
                            )
                            addToCart(item, onSuccess = {
                                navController.navigate(Screen.Cart.route)
                            }, onFailure = { e ->
                                Log.e(TAG, "Failed to add item to cart", e)
                                // Tambahkan penanganan kesalahan yang sesuai di sini
                            })
                        },
                        shape = RoundedCornerShape(40),
                        colors = ButtonDefaults.buttonColors(
                            GreenButton, contentColor = Color.White
                        )
                    ) {
                        Text(text = "Add to cart", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        },
    ) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.fried),
            contentDescription = stringResource(R.string.app_logo)
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp),
                text = stringResource(id = R.string.food),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = "Rp 27.000",
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun DetailOrderScreenPrev() {
    DetailOrderScreen(rememberNavController())
}