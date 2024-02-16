package com.example.permissionhandling.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.permissionhandling.Content

@OptIn(ExperimentalMaterial3Api::class)
//Alert Dialog untuk menampilkan sebuah Alert Dialog jika "Deny" permissionnya
@Composable
fun PermissionDeniedContent(
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRational: Boolean,
    onRequestPermission: () -> Unit
){

    if (shouldShowRational){
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text(text = "Give Permission")
                }
            },
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(text = rationaleMessage)
            },
        )
    }else{
        Content(text = deniedMessage, onClick =  onRequestPermission)
    }
}