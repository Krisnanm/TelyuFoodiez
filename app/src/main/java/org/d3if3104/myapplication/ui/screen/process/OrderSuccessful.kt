import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.d3if3104.myapplication.ui.screen.pembeli.detail.ScreenContent

@Composable
fun OrderSuccessful (navController: NavHostController){
    Scaffold (containerColor = Color.White) {
        ScreenContent(navController, modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent (navController: NavHostController, modifier: Modifier) {
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
            painter = painterResource(id = R.drawable.successicon),
            contentDescription = stringResource(R.string.app_logo)
        )
        Text(
            text = "Order Successful",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Cool down, your food will arrive in\n 25 minutes.",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OrderSuccesfulPrev (){
    OrderSuccessful(rememberNavController())
}