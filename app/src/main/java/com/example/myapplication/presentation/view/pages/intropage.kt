// presentation/view/pages/intropage.kt
package com.example.myapplication.presentation.view.pages
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

@Composable
fun intropage(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323)), // أسود غامق مثل الصورة
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // إذا كان لديك صورة شعار الشمس أضفها هنا:
            // Image(painter = painterResource(id = R.drawable.sun_logo), contentDescription = null, modifier = Modifier.size(120.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitle,
                color = Color(0xFFBBBBBB),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun intropage() {
    intropage(
        title = "SUNSIGHT",
        subtitle = "WELCOME TO NEW FUTURE"
    )
}