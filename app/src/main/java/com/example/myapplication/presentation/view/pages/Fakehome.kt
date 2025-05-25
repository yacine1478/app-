package com.example.myapplication.presentation.view.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny // أيقونة الشمس
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// لون الخلفية الرئيسي المشابه للصورة
val DarkBackground = Color(0xFF222222)
val CardBackground = Color.White
val TextColor = Color.Black

@Composable
fun FakeHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp) // مسافة بين البطاقات
    ) {
        Spacer(modifier = Modifier.height(32.dp)) // مسافة من الأعلى

        // البطاقة العلوية (الشمس ودرجة الحرارة)
        SunWeatherCard()

        // البطاقة السفلية (عداد الطاقة)
        PowerLeftCard()
    }
}

@Composable
fun SunWeatherCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min), // يسمح بتحديد الارتفاع بناءً على المحتوى
        shape = RoundedCornerShape(20.dp),
        backgroundColor = CardBackground,
        elevation = 0.dp // لا يوجد ظل في الصورة الأصلية
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.WbSunny,
                    contentDescription = "Sun Icon",
                    tint = TextColor,
                    modifier = Modifier.size(70.dp) // حجم أيقونة الشمس
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "HOT SUN",
                    color = TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "THIS DAY",
                    color = TextColor,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(20.dp)) // مسافة بين الأقسام

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "IT FEEL",
                        color = TextColor,
                        fontSize = 14.sp,
                        modifier = Modifier.alignByBaseline()
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "50",
                        color = TextColor,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignByBaseline()
                    )
                }
                Text(
                    text = "CHECK YOUR SOLARS PANNALS ^", // قد تحتاج لتعديل هذه الأيقونة
                    color = TextColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun PowerLeftCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp), // ارتفاع ثابت لهذه البطاقة
        shape = RoundedCornerShape(20.dp),
        backgroundColor = CardBackground,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp), // حجم الدائرة
                contentAlignment = Alignment.Center
            ) {
                CircularPowerIndicator(
                    percentage = 0.75f, // 75% من الطاقة
                    radius = 120.dp,
                    strokeWidth = 15.dp,
                    foregroundColor = TextColor,
                    backgroundColor = Color.LightGray.copy(alpha = 0.5f) // لون الدائرة الخلفية
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "75",
                        color = TextColor,
                        fontSize = 90.sp, // حجم كبير للرقم
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "POWER LEFT",
                        color = TextColor,
                        fontSize = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp)) // مسافة بين الدائرة والنقاط

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // النقطة الأولى (ممتلئة)
                CircleIndicator(selected = true)
                Spacer(modifier = Modifier.width(8.dp))
                // النقطة الثانية (فارغة)
                CircleIndicator(selected = false)
                Spacer(modifier = Modifier.width(8.dp))
                // النقطة الثالثة (فارغة)
                CircleIndicator(selected = false)
            }
        }
    }
}

@Composable
fun CircleIndicator(selected: Boolean) {
    val color = if (selected) TextColor else Color.LightGray.copy(alpha = 0.7f)
    Canvas(modifier = Modifier.size(8.dp)) {
        drawCircle(color = color)
    }
}

@Composable
fun CircularPowerIndicator(
    percentage: Float,
    radius: Dp,
    strokeWidth: Dp,
    foregroundColor: Color,
    backgroundColor: Color
) {
    Canvas(modifier = Modifier.size(radius * 2)) {
        // رسم الدائرة الخلفية الكاملة
        drawCircle(
            color = backgroundColor,
            radius = radius.toPx(),
            style = Stroke(width = strokeWidth.toPx())
        )

        // رسم التقدم (الجزء الممتلئ)
        drawArc(
            color = foregroundColor,
            startAngle = -90f, // ابدأ من الأعلى
            sweepAngle = 360 * percentage,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round) // نهاية مستديرة
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 700) // معاينة بالحجم التقريبي للهاتف
@Composable
fun PreviewFakeHomeScreen() {
    MaterialTheme { // استخدم MaterialTheme لتطبيق سمات المواد الأساسية
        FakeHomeScreen()
    }
}