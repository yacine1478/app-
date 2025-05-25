package com.example.myapplication.presentation.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.HelveticaFamilly
import com.example.myapplication.ui.theme.bigNoodle
import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// استبدل الخطوط بما يناسبك
val bigNoodle = FontFamily.Default
val HelveticaFamilly = FontFamily.Default

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login(
    onLoginSuccess: () -> Unit,
    onSignInClick: () -> Unit,
    onGoogleLoginClick: () -> Unit
) {

    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222222))
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
                fontSize = 75.sp,
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
                            topStart = 66.dp, topEnd = 66.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    )
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 66.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = {
                            Text(
                                text = "EMAIL",
                                fontFamily = HelveticaFamilly,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(horizontal = 10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedLabelColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedContainerColor = Color(0xFF2A2A2A),
                            focusedContainerColor = Color.White,
                            unfocusedLeadingIconColor = Color.White,
                            focusedLeadingIconColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email icon",
                            )
                        },
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(65.dp)
                        .padding(top = 150.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        OutlinedTextField(
                            value = pass.value,
                            onValueChange = { pass.value = it },
                            label = {
                                Text(
                                    text = "PASSWORD",
                                    fontFamily = HelveticaFamilly,
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 0.dp),
                            shape = RoundedCornerShape(16.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Password icon",
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedLabelColor = Color.White,
                                focusedLabelColor = Color.Black,
                                unfocusedContainerColor = Color(0xFF2A2A2A),
                                focusedContainerColor = Color.White,
                                unfocusedLeadingIconColor = Color.White,
                                focusedLeadingIconColor = Color.Black,
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Forgot password?",
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable { /* Handle click */ },
                            color = Color.Gray,
                            fontFamily = HelveticaFamilly
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 120.dp)
                ) {
                    Spacer(modifier = Modifier.height(165.dp))
                    Button(
                        onClick = {
                            if (email.value.isNotBlank() && pass.value.isNotBlank()) {
                                onLoginSuccess()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 40.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Log In",
                            fontFamily = bigNoodle,
                            fontSize = 30.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "or",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontFamily = bigNoodle,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { onSignInClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 40.dp),
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

                    Spacer(modifier = Modifier.height(85.dp))
                    Button(
                        onClick = { onGoogleLoginClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .padding(horizontal = 10.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(50.dp)
                            ),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                        ),
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 0.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GoogleIcon()
                            Spacer(Modifier.width(15.dp))
                            Text(
                                "SIGN IN WITH GOOGLE",
                                fontFamily = bigNoodle,
                                fontSize = 34.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

// أيقونة Google مرسومة بالكود
@Composable
fun GoogleIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(40.dp)) {
        // الأزرق
        drawArc(
            color = Color(0xFF4285F4),
            startAngle = 180f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 8f)
        )
        // الأحمر
        drawArc(
            color = Color(0xFFEA4335),
            startAngle = 225f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 8f)
        )
        // الأصفر
        drawArc(
            color = Color(0xFFFBBC05),
            startAngle = 270f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 8f)
        )
        // الأخضر
        drawArc(
            color = Color(0xFF34A853),
            startAngle = 315f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 8f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Previewlogin() {
    login(
        onLoginSuccess = {},
        onSignInClick = {},
        onGoogleLoginClick = {}
    )
}