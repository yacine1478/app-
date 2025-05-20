package com.example.myapplication.presentation.view.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.view.pages.intropage
import com.example.myapplication.ui.theme.HelveticaFamilly
import com.example.myapplication.ui.theme.bigNoodle
import com.example.MyApplication.R
import kotlinx.coroutines.delay
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sign () {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val confirmpass = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(true) }


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
                text = "SIGN IN",
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
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 70.dp),
                    contentAlignment = Alignment.TopStart
                ) {


                    // Email field**********************************************************


                    OutlinedTextField(
                        value = username.value,
                        onValueChange = { username.value = it },
                        label = {
                            Text(
                                text = "USER NAME",
                                fontFamily = HelveticaFamilly,
                                // Optional label color
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(horizontal = 10.dp), // Add vertical padding
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next // Or ImeAction.Done depending on flow
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { /* Move focus to next field */ },
                            onDone = { /* Submit form or hide keyboard */ }
                        ),
                        singleLine = true, // Important for email input
                        colors = TextFieldDefaults.colors(
                            unfocusedLabelColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedContainerColor = Color(0xFF2A2A2A),
                            focusedContainerColor = Color.White,
                            unfocusedLeadingIconColor = Color.White,
                            focusedLeadingIconColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        ), // Rounded corners
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Email icon",

                                )
                        },

                        )


                }
                // pass field.................................................


                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxSize()
                        .height(65.dp)
                        .padding(top = 150.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 10.dp)
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
                                .padding(horizontal = 0.dp),
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Email icon",

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
                        Column(

                        ){
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
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 16.dp,
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Email icon",

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
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                isError = !isPasswordVisible.value && confirmpass.value.isEmpty()
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(
                                value = confirmpass.value,
                                onValueChange = { confirmpass.value = it },
                                label = {
                                    Text(
                                        text = "confirm password",
                                        fontFamily = HelveticaFamilly,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 0.dp),
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 16.dp,
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Email icon",

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
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                isError = !isPasswordVisible.value && confirmpass.value.isEmpty()

                            )
                            if(!isPasswordVisible.value && confirmpass.value.isEmpty()){
                                Text(
                                    text = "Password dont match",
                                    color = Color.Red,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }

                        }

                    }


                }

                Column(

                ) {
                    Spacer(modifier = Modifier.height(375.dp))
                    Button(
                        onClick = { /* Handle sign-in logic here */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 40.dp),
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Your preferred color
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Sign In",
                            fontFamily = bigNoodle,
                            fontSize = 30.sp
                        )
                    }


                    //second button ///////////////////


                    Spacer(modifier = Modifier.height(85.dp))
                }
            }

        }


    }
}
// FINALLY FINISH THIS PAGE YESSSSSSSSSSSSSSSSSS*************


@Preview
@Composable
fun Previewsign (){
    sign()
}