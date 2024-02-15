package com.example.permissionhandling

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
import com.example.permissionhandling.widgets.PermissionDeniedContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SingleRequestPermission(
    permission: String,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission"
){
    val permissionState = rememberPermissionState(permission = permission)
    HandleRequest(
        permissionState = permissionState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(deniedMessage = deniedMessage, rationaleMessage = rationaleMessage, shouldShowRational = shouldShowRationale, onRequestPermission = {
                permissionState.launchPermissionRequest()
            })
        },
        content = {
            Content(text = "PERMISSION GRANTED", showButton = false){}
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
//Handling Permintaan Permission dengan condition when
private fun HandleRequest(
    //parameter permissionState dengan tipe PermissionState
    permissionState: PermissionState,

    //parameter deniedContent dengan tipe Composable Unit
    deniedContent: @Composable (Boolean) -> Unit,

    //parameter content dengan tipe Composable Unit
    content: @Composable () -> Unit,
){

    //when condition untuk menentukan parameter deniedContent & content
    when(permissionState.status){
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
        }
    }

}

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
