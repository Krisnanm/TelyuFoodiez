package org.d3if3104.myapplication.ui.screen.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.GrayTextField
import org.d3if3104.myapplication.ui.theme.GreenButton

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(containerColor = Color.White) {
        ScreenContent(navController,modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController,modifier: Modifier) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(
                modifier = Modifier.size(220.dp, 100.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        Column {
            Text(text = stringResource(R.string.phone_number))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                shape = RoundedCornerShape(14.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_phone_24),
                        contentDescription = "Address Icon",
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                label = { Text(text = "Enter your phone number") },
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            Text(text = stringResource(R.string.password))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(14.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                label = { Text(text = "Enter your password") },
            )

            Spacer(modifier = Modifier.padding(bottom = 24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = {navController.navigate(Screen.Dashboard.route)},
                shape = RoundedCornerShape(16.dp),
                colors = buttonColors(
                    GreenButton, Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.login_button),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row {
        Text(text = stringResource(R.string.no_account), fontSize = 14.sp)
            ClickableText(text = AnnotatedString(stringResource(R.string.sign_up_button)), onClick = {navController.navigate(
                Screen.Register.route)}, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GreenButton))
        }

    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(rememberNavController())
}