package org.d3if3104.myapplication.ui.screen.process

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R

@Composable
fun OrderProcess (navController: NavHostController) {
    Scaffold(containerColor = Color.White) {
        ScreenContent(navController,modifier = Modifier.padding(it))
    }
}

@Composable
fun ScreenContent (navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(140.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ordericon),
            contentDescription = stringResource(R.string.app_logo)
        )
        Text(
            text = "Order Process..",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Wait a moment, Your order is\n being processed!",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OrderProcessPrev() {
    OrderProcess(rememberNavController())
}