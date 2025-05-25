package com.example.myapplication.presentation.view.pages
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarketShope() {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Amazon Icon (رسم يدوي)
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(Color(0xFFFFC72C), CircleShape)
                    .clip(CircleShape)
                    .clickable {
                        if (!isPreview) {
                            val url = "https://www.amazon.com/s?k=solar+panel+accessories"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "a",
                        color = Color.White,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    // رسم الابتسامة
                    Canvas(modifier = Modifier.size(60.dp, 20.dp)) {
                        drawArc(
                            color = Color.White,
                            startAngle = 20f,
                            sweepAngle = 140f,
                            useCenter = false,
                            topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
                            size = androidx.compose.ui.geometry.Size(size.width, size.height),
                            style = Stroke(width = 6f)
                        )
                        // السهم
                        drawLine(
                            color = Color.White,
                            start = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height * 0.7f),
                            end = androidx.compose.ui.geometry.Offset(size.width * 0.95f, size.height * 0.5f),
                            strokeWidth = 6f
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))

            // AliExpress Icon (رسم يدوي)
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable {
                        if (!isPreview) {
                            val url = "https://www.aliexpress.com/wholesale?SearchText=solar+panel+accessories"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // الجزء البرتقالي
                    drawRoundRect(
                        color = Color(0xFFFFA000),
                        topLeft = androidx.compose.ui.geometry.Offset(0f, size.height * 0.18f),
                        size = androidx.compose.ui.geometry.Size(size.width, size.height * 0.82f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(24f, 24f)
                    )
                    // الجزء الأحمر العلوي
                    drawRoundRect(
                        color = Color(0xFFE53935),
                        topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
                        size = androidx.compose.ui.geometry.Size(size.width, size.height * 0.22f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(18f, 18f)
                    )
                    // الإطار الأسود
                    drawRoundRect(
                        color = Color.Black,
                        topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
                        size = androidx.compose.ui.geometry.Size(size.width, size.height),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(24f, 24f),
                        style = Stroke(width = 6f)
                    )
                    // مقبض الحقيبة
                    drawArc(
                        color = Color.White,
                        startAngle = 200f,
                        sweepAngle = 140f,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset(size.width * 0.25f, size.height * 0.45f),
                        size = androidx.compose.ui.geometry.Size(size.width * 0.5f, size.height * 0.3f),
                        style = Stroke(width = 6f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarketScreen() {
    MarketShope()
}