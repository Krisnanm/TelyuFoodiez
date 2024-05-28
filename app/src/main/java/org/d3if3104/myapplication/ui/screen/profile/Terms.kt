package org.d3if3104.myapplication.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3104.myapplication.R
import org.d3if3104.myapplication.navigation.Screen
import org.d3if3104.myapplication.ui.theme.LightGreen
import org.d3if3104.myapplication.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsConditionScreen (navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Screen.Profile.route)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(id = R.string.kembali),
                        )
                    }
                },
                title ={
                    Text(text = stringResource(id = R.string.terms))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = LightGreen,
                    titleContentColor = Color.White,
                ),
            )
        }) {
        ScreenContent(navController,modifier = Modifier.padding(it))
    }
}

@Composable
private fun ScreenContent(navController: NavHostController,modifier: Modifier){
    Column {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(25.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text(
                text = "Last Update: 22/05/2024",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Please read these terms of service, carefully before using our app operated by us",)

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.conditions),
                color = Orange,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.termscondition))
        }
    }
}