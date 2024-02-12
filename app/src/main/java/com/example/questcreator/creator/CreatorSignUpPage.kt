package com.example.questcreator.creator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.questcreator.user_database.UserState
import com.example.questcreator.user_database.UsersEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorSignUpScreen(state: UserState, navController: NavController, onEvent: (UsersEvent) -> Unit) {
    var username by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var passwordConfirm by rememberSaveable {mutableStateOf("")}
    var passwordConfirmVisible by rememberSaveable { mutableStateOf(false) }
    var isErrorMin by rememberSaveable { mutableStateOf(false) }
    var isErrorConfirm by rememberSaveable { mutableStateOf(false) }
    var isErrorUsername by rememberSaveable { mutableStateOf(false) }
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    val charMin = 8
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
                text = "Sign Up",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 46.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
            )
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedTextField(
                value = state.username.value,
                onValueChange = { state.username.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                isError = isErrorUsername,
                supportingText = {
                    if (isErrorUsername) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Please enter valid email",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                label = {
                    Text("Email", fontSize = 16.sp)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.imePadding()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = state.password.value,
                onValueChange = { state.password.value = it },
                isError = isErrorMin,
                supportingText = {
                    if (isErrorMin) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Limit: ${password.length}/$charMin",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                label = {
                    Text("Create password", fontSize = 16.sp)
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
            OutlinedTextField(
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                singleLine = true,
                isError = isErrorConfirm,
                supportingText = {
                    if (isErrorConfirm) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Passwords are not equal",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                label = {
                    Text("Confirm password", fontSize = 16.sp)
                },
                visualTransformation = if (passwordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordConfirmVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordConfirmVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordConfirmVisible = !passwordConfirmVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.imePadding()
            )
            Spacer(modifier = Modifier.height(40.dp))
            ExtendedFloatingActionButton(
                onClick = {
                    isErrorMin = password.length < charMin
                    isErrorConfirm = password != passwordConfirm
                    isErrorUsername = !username.contains(emailRegex)
//                    if (!isErrorConfirm && !isErrorMin && !isErrorUsername) {
                        navController.navigate(Screen.CreatorConfirmationScreen.route)
                        onEvent(UsersEvent.SaveUser(
                            username = state.username.value,
                            password = state.password.value
                        ))
//                    }
                },
                content = { Text("Create", fontSize = 24.sp, color = MaterialTheme.colorScheme.onPrimary) },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Already have an account? Login",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.CreatorLoginScreen.route)
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
fun PreviewCreator() {
    QuestCreatorTheme() {
        val context = LocalContext.current

        // Sample UserState for the preview
        val sampleUserState = UserState(/* initialize with desired values */)

        // Pass the sample state to the CreatorSignUpScreen
        CreatorSignUpScreen(
            state = sampleUserState,
            navController = NavController(context),
            onEvent = { /* handle UsersEvent in preview if needed */ }
        )
    }
}