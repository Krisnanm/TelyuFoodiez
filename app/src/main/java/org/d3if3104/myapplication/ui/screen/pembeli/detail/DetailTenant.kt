package org.d3if3104.myapplication.ui.screen.pembeli.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.model.CartItem
import org.d3if3104.myapplication.model.MenuItemData
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.Stroke
import org.d3if3104.myapplication.viewmodel.CartViewModel

@Composable
fun DetailTenant(navController: NavHostController) {
    Scaffold(containerColor = Color.White) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            painter = painterResource(id = R.drawable.food),
            contentDescription = stringResource(R.string.app_logo),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {
            Text(
                text = "Warung Sambal Bakar",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoItem(title = "8AM-8PM", subtitle = "Opening hours")
            InfoItem(title = "6", subtitle = "Menu variants")
            InfoItem(title = "49-890rb", subtitle = "Price range")
            InfoItem(title = "20 mins", subtitle = "Delivery Time")
        }
        MenuSection(navController)
    }
}

@Composable
fun InfoItem(title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            )
        )
    }
}

@Composable
fun MenuSection(navController: NavHostController) {
    val menuItems = listOf(
        MenuItemData(
            R.drawable.fried,
            "Bottega's Fried Rice",
            "Rp98.000",
            "https://firebasestorage.googleapis.com/v0/b/telyufoodiez.appspot.com/o/menu%202.jpg?alt=media&token=44653b5c-573c-4c65-a9fb-3a5558a7299d"
        ),
        MenuItemData(
            R.drawable.sushi_platter,
            "Sushi Platter",
            "Rp150.000",
            "https://firebasestorage.googleapis.com/v0/b/telyufoodiez.appspot.com/o/menu%201.jpg?alt=media&token=7fe96437-37c3-4777-964e-0af44b448da1"
        ),
        MenuItemData(
            R.drawable.wagyu_grill,
            "Wagyu Grill",
            "Rp300.000",
            "https://firebasestorage.googleapis.com/v0/b/telyufoodiez.appspot.com/o/menu%204.jpg?alt=media&token=f9d147cb-4bd6-47fb-94b2-de2cb894697d"
        ),
        MenuItemData(
            R.drawable.chicken_grill,
            "Chicken Grill",
            "Rp300.000",
            "https://firebasestorage.googleapis.com/v0/b/telyufoodiez.appspot.com/o/menu%203.jpg?alt=media&token=1a855588-3aaf-46cb-abe5-0b92f147f029"
        ),
        MenuItemData(
            R.drawable.grilled_salmon,
            "Grilled Salmon",
            "Rp200.000",
            "https://firebasestorage.googleapis.com/v0/b/telyufoodiez.appspot.com/o/menu%202.jpg?alt=media&token=44653b5c-573c-4c65-a9fb-3a5558a7299d"
        )
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItem(navController = navController, menuItem = menuItem)
        }
    }
}

@Composable
fun MenuItem(navController: NavHostController, menuItem: MenuItemData) {
    val viewModel: CartViewModel = viewModel()
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(2.dp, color = Stroke)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = menuItem.imageRes),
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = menuItem.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = menuItem.price,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                )
            }
            Button(
                onClick = {
                    val cartItem = CartItem(
                        id = "", // ID akan diisi oleh Firestore
                        name = menuItem.title,
                        price = menuItem.price.replace("Rp", "").replace(".", "").toInt(),
                        quantity = 1
                    )
                    viewModel.addItemToCart(cartItem, onSuccess = {
                        navController.navigate(Screen.Cart.route)
                    }, onFailure = { e ->
                        Log.e(TAG, "Failed to add item to cart", e)
                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenButton, contentColor = Color.White
                )
            ) {
                Text(
                    text = "Add",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailTenantPrev() {
    DetailTenant(rememberNavController())
}