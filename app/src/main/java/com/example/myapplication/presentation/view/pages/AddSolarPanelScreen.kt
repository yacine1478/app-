package com.example.myapplication.presentation.view.pages
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// غيّر اسم الكلاس لتجنب التعارض مع الدالة
class AddSolarPanelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddSolarPanelScreen()
        }
    }
}

@Composable
fun AddSolarPanelScreen() {
    val context = LocalContext.current
    var group by remember { mutableStateOf("") }
    var voltage by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var locationSet by remember { mutableStateOf(false) }
    var locationUri by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF232323))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = group,
            onValueChange = { group = it },
            label = { Text("GROUP", fontWeight = FontWeight.Bold, color = Color.LightGray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = voltage,
            onValueChange = { voltage = it },
            label = { Text("V", fontWeight = FontWeight.Bold, color = Color.LightGray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = current,
            onValueChange = { current = it },
            label = { Text("I", fontWeight = FontWeight.Bold, color = Color.LightGray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = temperature,
            onValueChange = { temperature = it },
            label = { Text("T", fontWeight = FontWeight.Bold, color = Color.LightGray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(32.dp))
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "اختر الموقع",
            tint = Color.Black,
            modifier = Modifier
                .size(64.dp)
                .clickable {
                    val gmmIntentUri = Uri.parse("geo:0,0?q=solar+panel+location")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                    locationSet = true
                    locationUri = gmmIntentUri.toString()
                }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "IT MUST CHOICE THE SOLAR PANEL IN SAME LOCATION",
            color = Color.LightGray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                saveSolarPanel(
                    context, group, voltage, current, temperature, locationUri
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Save", fontWeight = FontWeight.Bold)
        }
    }
}

fun saveSolarPanel(
    context: Context,
    group: String,
    voltage: String,
    current: String,
    temperature: String,
    location: String
) {
    // TODO: استخدم Retrofit أو OkHttp لإرسال البيانات إلى PHP API في XAMPP

    // إظهار Toast notification عند النجاح
    Toast.makeText(
        context,
        "تمت إضافة اللوحة الشمسية الجديدة بنجاح!",
        Toast.LENGTH_LONG
    ).show()
}

@Preview(showBackground = true)
@Composable
fun AddSolarPanelPreview() {
    AddSolarPanelScreen()
}
