package com.example.permissionhandling.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
//Hasil Setelah melakukan permission handler
fun Content(text: String, showButton: Boolean = true, onClick: ()->Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        if (showButton) {
            Button(onClick = onClick) {
                Text(text = "Request")
            }
        }
    }
}