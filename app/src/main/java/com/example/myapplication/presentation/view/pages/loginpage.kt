package com.example.myapplication.presentation.view.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.view.pages.intropage
import com.example.myapplication.ui.theme.HelveticaFamilly
import com.example.myapplication.ui.theme.bigNoodle
import kotlinx.coroutines.delay

@Composable
fun login () {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "LOGIN",
                color = Color(0xFFC7C1C1),
                fontSize = 50.sp,
                fontFamily = bigNoodle,
            )
        }
        Box(
          modifier = Modifier
              .weight(3f)
              .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(
                            topStart = 66.dp
                            , topEnd = 66.dp
                            , bottomStart = 0.dp
                            , bottomEnd = 0.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Email field
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("EMAIL", fontFamily = HelveticaFamilly) },
                    modifier = Modifier.fillMaxSize(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(17.dp))
                // pass field
                OutlinedTextField(
                    value = pass.value,
                    onValueChange = { pass.value = it },
                    label = { Text("password", fontFamily = HelveticaFamilly) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(17.dp))
                Button(
                    onClick = { /* Handle login */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue, // Change to your preferred color
                        contentColor = Color.White
                    )
                ) {
                    Text("SIGN IN")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Or divider
                Text(
                    text = "OR",
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Google sign in button
                Button(
                    onClick = { /* Handle Google sign in */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {

                    Text("SIGN IN WITH GOOGLE")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Facebook sign in button
                Button(
                    onClick = { /* Handle Facebook sign in */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4267B2), // Facebook blue
                        contentColor = Color.White
                    )
                ) {

                    Text("SIGN IN WITH FACEBOOK")

                }

            }
        }

            }



}




@Preview
@Composable
fun Previewlogin (){
    login()
}