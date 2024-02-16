package com.example.permissionhandling

import androidx.compose.runtime.Composable
import com.example.permissionhandling.widgets.Content
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
