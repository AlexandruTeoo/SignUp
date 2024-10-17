package com.example.signupauthentificationui

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen (name:String, age:String, sex:String, email:String){
    val color = when (sex){
        "M" -> Color(34, 156, 220)
        "F" -> Color(209, 98, 140)
        else -> {Color.White}
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(color)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Hello, $name!")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = email)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "You are $age years old.")
            }
        }
    )
}