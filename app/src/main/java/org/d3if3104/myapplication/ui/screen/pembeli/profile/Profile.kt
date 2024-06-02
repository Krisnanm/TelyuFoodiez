package org.d3if3104.myapplication.ui.screen.pembeli.profile

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.ButtonWhite
import org.d3if3104.myapplication.ui.theme.GreenButton
import org.d3if3104.myapplication.ui.theme.LightGreen
import org.d3if3104.myapplication.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
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
                    Text(text = stringResource(id = R.string.Profile))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = Color.White,
                ),
            )
        }) {
        ScreenContent(userViewModel,navController,modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(userViewModel: UserViewModel, navController: NavHostController, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    LaunchedEffect(userViewModel.logoutSuccess) {}
    val logoutSuccess =userViewModel.logoutSuccess.collectAsState().value
    if (logoutSuccess) {
        Toast.makeText(context, "Berhasil Keluar", Toast.LENGTH_SHORT).show()
        userViewModel.resetLogoutState()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(220.dp, 100.dp),
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = stringResource(R.string.app_logo)
        )
        Column {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Username section
                Text(text = "Name", fontSize = 18.sp, color = Color.Black)
                var name by remember { mutableStateOf(TextFieldValue()) }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24), // Replace with your user icon resource
                            contentDescription = "User Icon",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    },
                    label = { Text(text = "Enter your name") },
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Email or Phone Number section
                Text(text = "Phone Number", fontSize = 18.sp, color = Color.Black)
                var phone by remember { mutableStateOf(TextFieldValue()) }
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_phone_24), // Replace with your email icon resource
                            contentDescription = "Phone Icon",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    },
                    label = { Text(text = "Enter your Phone Number") },
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Address section
                Text(text = "Address", fontSize = 18.sp, color = Color.Black)
                var address by remember { mutableStateOf(TextFieldValue()) }
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_location_on_24), // Replace with your address icon resource
                            contentDescription = "Address Icon",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    },
                    label = { Text(text = "Enter your address") },
                )

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    onClick = {},
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        GreenButton, contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    onClick = {userViewModel.logout(navController)},
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, color = Color.Red),
                    colors = ButtonDefaults.buttonColors(ButtonWhite, contentColor = Color.White)
                ) {
                    Text(
                        text = stringResource(R.string.logout),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                }
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Terms.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Terms and Conditions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun SaveButton() {

}


@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(rememberNavController())
}