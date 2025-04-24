package com.example.myapplication.presentation.view.pages

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontsContractCompat.Columns
import com.example.myapplication.ui.theme.bebasfamilly
import com.example.myapplication.ui.theme.bigNoodle

@Composable
fun intropage(title: String, description: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = title, fontSize = 32.sp, fontFamily= bebasfamilly, fontWeight= FontWeight.Normal ,color =  Color(0xFFC7C1C1))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, fontSize = 18.sp,fontFamily= bigNoodle , fontWeight = FontWeight.Normal ,color =  Color(0xFFC7C1C1))

    }
}