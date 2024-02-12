package com.example.questcreator.creator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.questcreator.ui.theme.QuestCreatorTheme
import com.example.questcreator.user_database.UserState
import com.example.questcreator.user_database.UsersEvent

@Composable
fun CreatorConfirmationScreen(state: UserState, navController: NavController, onEvent: (UsersEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Adjust padding as needed
        ) {
            Text(
                text = "Confirmation",
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(state.users.size) { index ->
                    UserItem(
                        state = state,
                        index = index,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}
@Composable
fun UserItem(state: UserState, index: Int, onEvent: (UsersEvent) -> Unit) {
    Row {
        Column {
            Text(text = state.users[index].username)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = state.users[index].password)
//            Text(text = state.users[index].dateAdded.toString())
//            Text(text = state.users[index].id.toString())
        }
        IconButton(
            onClick = { onEvent(UsersEvent.DeleteUser(state.users[index])) }
        ) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete User")
        }
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

        // Sample UserState for the preview
        val sampleUserState = UserState(/* initialize with desired values */)

        // Pass the sample state to the CreatorConfirmationScreen
        CreatorConfirmationScreen(state = sampleUserState,
            navController = NavController(context),
            onEvent = { /* handle UsersEvent in preview if needed */ })
    }
}