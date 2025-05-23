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

@Composable
fun EditProfileScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
    ) {
        // الجزء الأبيض السفلي بحواف علوية دائرية
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.62f)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // USER NAME
                EditProfileField(
                    value = username,
                    onValueChange = { username = it },
                    label = "USER NAME"
                )
                Spacer(modifier = Modifier.height(24.dp))

                // EMAIL
                EditProfileField(
                    value = email,
                    onValueChange = { email = it },
                    label = "EMAIL"
                )
                Spacer(modifier = Modifier.height(24.dp))

                // PASSWORD
                EditProfileField(
                    value = password,
                    onValueChange = { password = it },
                    label = "PASSWORD",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(24.dp))

                // CONFIRM PASSWORD
                EditProfileField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "CONFIRM PASSWORD",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(40.dp))

                // زر EDIT PROFIL
                Button(
                    onClick = { /* تنفيذ التعديل */ },
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