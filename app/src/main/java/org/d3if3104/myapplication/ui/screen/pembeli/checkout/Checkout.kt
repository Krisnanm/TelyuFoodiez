package org.d3if3104.myapplication.ui.screen.pembeli.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.model.CartItem
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.LightGreen
import org.d3if3104.myapplication.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavHostController) {
    val viewModel: CartViewModel = viewModel()
    val cartItems by viewModel.cartItems.collectAsState(initial = emptyList())

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
                    Text(text = stringResource(id = R.string.checkout))
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
                            text = "Rp ${cartItems.sumOf { it.price * it.quantity }}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .width(200.dp),
                        onClick = { navController.navigate(Screen.OrderProcess.route) },
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
    ) {
        ScreenContent(navController, modifier = Modifier.padding(it), cartItems, viewModel)
    }
}

@Composable
private fun ScreenContent(
    navController: NavHostController,
    modifier: Modifier,
    cartItems: List<CartItem>,
    viewModel: CartViewModel
) {
    var deliveryLocation by remember { mutableStateOf(TextFieldValue("Gedung Asrama 5, No Kamar 20")) }

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
        ItemRow(cartItems, viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PaymentSummary(cartItems)
    }
}

@Composable
fun ItemRow(cartItems: List<CartItem>, viewModel: CartViewModel) {
    Column {
        cartItems.forEach { item ->
            Item(item, viewModel)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Item(item: CartItem, viewModel: CartViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Assuming you have a way to map item names to image resources
        val imageRes = when (item.name) {
            "Sushi Platter" -> R.drawable.sushi_platter
            "Wagyu Grill" -> R.drawable.wagyu_grill
            "Chicken Grill" -> R.drawable.chicken_grill
            "Grilled Salmon" -> R.drawable.grilled_salmon
            // Add more cases as needed
            else -> R.drawable.ordericon // Default image
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(item.name)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Rp ${item.price}")
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { viewModel.decreaseQuantity(item) }) {
                Icon(Icons.Default.Delete, contentDescription = null)
            }
            Text(item.quantity.toString())
            IconButton(onClick = { viewModel.increaseQuantity(item) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun PaymentSummary(cartItems: List<CartItem>) {
    val totalPrice = cartItems.sumOf { it.price * it.quantity }
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
            Text("Rp $totalPrice")
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
fun CheckoutScreenPrev() {
    CheckoutScreen(rememberNavController())
}
