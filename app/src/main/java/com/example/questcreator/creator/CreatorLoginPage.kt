package com.example.questcreator.creator

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.questcreator.navigation.Screen
import com.example.questcreator.ui.theme.QuestCreatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorLoginScreen(navController: NavController) {
    var username by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 46.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
            )
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                label = {
                    Text("Email", fontSize = 16.sp)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.imePadding()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                label = {
                    Text("Password", fontSize = 16.sp)
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.imePadding()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Forgot password?",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "In progress...", Toast.LENGTH_SHORT).show()
                },
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(30.dp))
            ExtendedFloatingActionButton(
                onClick = {
                    //TODO write login logic
                },
                content = { Text("Login", fontSize = 24.sp, color = MaterialTheme.colorScheme.onPrimary) },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Do not have an account? Sign up",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.CreatorSignUpScreen.route)
                },
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

/**
 * Preview function
 */

@Preview(showBackground = true)
@Composable
fun PreviewCreatorLogin() {
    QuestCreatorTheme() {
        val context = LocalContext.current
        CreatorLoginScreen(navController = NavController(context))
    }
}