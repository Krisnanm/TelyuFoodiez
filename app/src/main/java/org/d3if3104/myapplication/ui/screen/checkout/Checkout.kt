package org.d3if3104.myapplication.ui.screen.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.screen.detail.ScreenContent
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavHostController) {
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
                    Text(text = stringResource(id = R.string.checkout))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = Color.White,
                ),
            )
        },
        bottomBar = {
            BottomAppBar(
            ){
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
                            text = "Rp 27.000",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .width(200.dp),
                        onClick = {navController.navigate(Screen.OrderProcess.route)},
                        shape = RoundedCornerShape(40),
                        colors = ButtonDefaults.buttonColors(
                            GreenButton, contentColor = Color.White
                        )
                    ) {
                        Text(text = "Checkout", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        },
    ){
        org.d3if3104.myapplication.ui.screen.checkout.ScreenContent(navController , modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    var deliveryLocation by remember { mutableStateOf(TextFieldValue("Gedung Asrama 5, No Kamar 20")) }
    var quantity by remember { mutableStateOf(1) }
    val price = 28000
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Delivery location",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = deliveryLocation,
            shape = RoundedCornerShape(14.dp),
            onValueChange = { deliveryLocation = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Item",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ItemRow(price, quantity, onQuantityChange = { quantity = it })
        Spacer(modifier = Modifier.height(16.dp))
        PaymentSummary(price, quantity)
    }
}

@Composable
fun ItemRow(price: Int, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Column {
        Item(price, quantity, onQuantityChange)
        Spacer(modifier = Modifier.height(8.dp))
        Item(price, quantity, onQuantityChange)
    }
}

@Composable
fun Item(price: Int, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.fried), // Replace with actual image resource
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Fried Noodle")
            Spacer(modifier = Modifier.height(4.dp))
            Text("Rp $price")
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
                Icon(Icons.Default.Delete, contentDescription = null)
            }
            Text(quantity.toString())
            IconButton(onClick = { onQuantityChange(quantity + 1) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun PaymentSummary(price: Int, quantity: Int) {
    val totalPrice = price * quantity
    Column {
        Text(
            text = "Payment",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Price")
            Text("Rp $price")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Delivery fee")
            Text("Free")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total payment")
            Text("Rp $totalPrice")
        }
    }
}

@Preview
@Composable
fun CheckoutScreenPrev () {
    CheckoutScreen(rememberNavController())
}