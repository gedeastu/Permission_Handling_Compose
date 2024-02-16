package com.example.permissionhandling

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.permissionhandling.ui.theme.PermissionHandlingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionHandlingTheme {
                //SingleRequestPermission(permission = Manifest.permission.READ_CONTACTS)
                MultipleRequestPermissions(permissions = listOf(Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS))
            }
        }
    }
}