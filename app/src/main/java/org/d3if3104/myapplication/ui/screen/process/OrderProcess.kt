@file:Suppress("DEPRECATION")

package org.d3if3104.myapplication.ui.screen.process

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieAnimatable
import kotlinx.coroutines.delay
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen

@Composable
fun OrderProcess(navController: NavHostController) {
    Scaffold(containerColor = Color.White) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        composition?.let {
            lottieAnimatable.animate(
                composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(4000) // 4 seconds delay
        navController.navigate(Screen.Dashboard.route) {
            popUpTo(Screen.OrderProcess.route) { inclusive = true }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 100.dp), // Adjust this value to move content upwards
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = lottieAnimatable.progress,
            modifier = Modifier.size(300.dp) // Adjust the size if needed
        )
        Text(
            text = "Order Process..",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp) // Reduced padding
        )
        Text(
            text = "Wait a moment, Your order is\n being processed!",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp) // Reduced padding
        )
    }
}

@Preview
@Composable
fun OrderProcessPrev() {
    OrderProcess(rememberNavController())
}
