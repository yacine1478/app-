package com.example.myapplication.presentation.view.pages
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.HelveticaFamilly
import com.example.myapplication.ui.theme.bigNoodle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sign(onSignUpSuccess: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val confirmpass = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "SIGN IN",
            color = Color(0xFFC7C1C1),
            fontSize = 75.sp,
            fontFamily = bigNoodle,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("USER NAME", fontFamily = HelveticaFamilly) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User icon")
            }
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("EMAIL", fontFamily = HelveticaFamilly) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon")
            }
        )

        OutlinedTextField(
            value = pass.value,
            onValueChange = { pass.value = it },
            label = { Text("PASSWORD", fontFamily = HelveticaFamilly) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password icon")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = confirmpass.value,
            onValueChange = { confirmpass.value = it },
            label = { Text("CONFIRM PASSWORD", fontFamily = HelveticaFamilly) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password icon")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.value.isNotBlank() && pass.value.isNotBlank() && pass.value == confirmpass.value) {
                    onSignUpSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(
                "Sign In",
                fontFamily = bigNoodle,
                fontSize = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun Previewsign() {
    sign {}
}