package com.example.myapplication.presentation.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.myapplication.presentation.Data.RetrofitClient
import com.example.myapplication.presentation.Data.WorkerUpdateRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    currentUsername: String = "",
    onSaveProfile: (String) -> Unit = {}
) {
    var username by remember { mutableStateOf(currentUsername) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .verticalScroll(scrollState)
                    .padding(bottom = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                EditProfileField(
                    value = username,
                    onValueChange = { username = it },
                    label = "USER NAME"
                )
                Spacer(modifier = Modifier.height(24.dp))


                EditProfileField(
                    value = email,
                    onValueChange = { email = it },
                    label = "EMAIL"
                )
                Spacer(modifier = Modifier.height(24.dp))


                EditProfileField(
                    value = password,
                    onValueChange = { password = it },
                    label = "PASSWORD",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(24.dp))


                EditProfileField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "CONFIRM PASSWORD",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(40.dp))


                Button(
                    onClick = {
                        if (username.isNotBlank()) {
                            onSaveProfile(username)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "EDIT PROFIL",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                label,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(56.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF6E6E6E)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF6E6E6E),
            unfocusedContainerColor = Color(0xFF6E6E6E),
            disabledContainerColor = Color(0xFF6E6E6E),
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = LocalTextStyle.current.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    )
  
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}
