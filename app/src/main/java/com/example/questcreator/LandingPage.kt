package com.example.questcreator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.questcreator.navigation.Screen
import com.example.questcreator.ui.theme.QuestCreatorTheme

@Composable
fun LandingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Landing page",
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Default
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(Screen.CreatorScreen.route)
            },
            content = { Text("I am creator", fontSize = 24.sp, color = Color(0xFFC9CBD5),) },
            modifier = Modifier
                .wrapContentWidth()
                .height(50.dp),            containerColor = Color(0xFF07103F),
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            content = { Text("I am player", fontSize = 24.sp, color = Color(0xFFC9CBD5),) },
            modifier = Modifier
                .wrapContentWidth()
                .height(50.dp),
            containerColor = Color(0xFF07103F),
        )
    }
}

/**
 * Preview function
 */

@Preview(showBackground = true)
@Composable
fun PreviewLanding() {
    QuestCreatorTheme() {
        val context = LocalContext.current
        LandingScreen(navController = NavController(context))
    }
}