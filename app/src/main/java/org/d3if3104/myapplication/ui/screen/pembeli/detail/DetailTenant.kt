package org.d3if3104.myapplication.ui.screen.pembeli.detail

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.Stroke

@Composable
fun DetailTenant(navController: NavHostController) {
    Scaffold(containerColor = Color.White) {
        ScreennContent(navController,modifier = Modifier.padding(it))
    }
}

@Composable
fun ScreennContent (navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    )
    {
        items(10) {
            MenuItem(
                navController = navController,
                imageRes = R.drawable.fried,
                title = "Bottega's Fried Rice",
                price = "Rp98.000"
            )
        }
    }
}

@Composable
fun MenuItem(navController: NavHostController,imageRes: Int, title: String, price: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, color = Stroke)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
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
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                )
            }
            Button(
                onClick = {navController.navigate(Screen.DetailOrder.route)},
                colors = ButtonDefaults.buttonColors(
                    GreenButton, contentColor = Color.White)
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
fun DetailTenantPrev () {
    DetailTenant(rememberNavController())
}