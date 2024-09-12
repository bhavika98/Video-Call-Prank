package com.app.incroyable.videocall_prank.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.firebase.analytics.FirebaseAnalytics
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.adhelper.loadAdmobAppOpen
import com.app.incroyable.videocall_prank.apihelper.APIClient
import com.app.incroyable.videocall_prank.apihelper.APIInterface
import com.app.incroyable.videocall_prank.apihelper.decodeUrl
import com.app.incroyable.videocall_prank.apihelper.updateUrl
import com.app.incroyable.videocall_prank.core.dialog.ForceUpdateDialog
import com.app.incroyable.videocall_prank.core.storage.SERVER_VIDEO
import com.app.incroyable.videocall_prank.core.storage.SERVER_WALLPAPER
import com.app.incroyable.videocall_prank.core.storage.clearAdPreferences
import com.app.incroyable.videocall_prank.core.storage.resetCounter
import com.app.incroyable.videocall_prank.core.storage.setAdPreferences
import com.app.incroyable.videocall_prank.core.storage.setPrefList
import com.app.incroyable.videocall_prank.core.utils.NetworkError
import com.app.incroyable.videocall_prank.core.utils.currentVersion
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.core.utils.redirectApp
import com.app.incroyable.videocall_prank.model.MultipleResource
import com.app.incroyable.videocall_prank.presentation.common.findActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private var firebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetCounter()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setContent {
            SplashScreen()
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val showErrorDialog = remember { mutableStateOf(true) }
    val showUpdateDialog = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.splash),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    if (showErrorDialog.value) {
        context.NetworkError(
            onNetworkAvailable = {
                if (!context.isOnline()) {
                    showErrorDialog.value = true
                } else {
                    showErrorDialog.value = false
                    context.startActivity(
                        Intent(
                            context,
                            StartActivity::class.java
                        ).setAction("")
                    )
                    context.findActivity()?.finishAffinity()
//                    (context as Activity).apiCall((context),
//                        onFailure = {
//                            showErrorDialog.value
//                        }, onForceUpdate = {
//                            showUpdateDialog.value = true
//                        })
                }
            },
            negativeClickListener = {
                context.findActivity()?.finishAffinity()
            }
        )
    }
    if (showUpdateDialog.value) {
        ForceUpdateDialog(
            onPositiveClickListener = {
                showUpdateDialog.value = false
                context.redirectApp(context.packageName)
                context.findActivity()?.finish()
            },
            onNegativeClickListener = {
                context.findActivity()?.finishAffinity()
            }
        )
    }
}

private var latestVersionCode: Int = 0
private var currentVersionCode: Int = 0
private fun Activity.apiCall(activity: Activity, onForceUpdate: () -> Unit, onFailure: () -> Unit) {
    clearAdPreferences()
    currentVersionCode = currentVersion()
    val apiInterface = APIClient.client?.create(APIInterface::class.java)
    val url = decodeUrl(updateUrl)
    val call: Call<MultipleResource> = apiInterface!!.doGetListResources(url)
    call.enqueue(object : Callback<MultipleResource> {
        override fun onResponse(
            call: Call<MultipleResource>,
            response: Response<MultipleResource>
        ) {
            val responseCall = response.body()

            setAdPreferences(responseCall!!.data)
//            setTestAdPreferences()
            setPrefList(responseCall.wallpaper, SERVER_WALLPAPER)
            setPrefList(responseCall.video, SERVER_VIDEO)
            latestVersionCode = responseCall.version

            if (latestVersionCode != 0 && currentVersionCode < latestVersionCode) {
                onForceUpdate()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1500)
                    activity.loadAdmobAppOpen {
                        activity.startActivity(
                            Intent(
                                activity,
                                StartActivity::class.java
                            ).setAction("")
                        )
                        activity.finish()
                    }
                }
            }
        }

        override fun onFailure(call: Call<MultipleResource>, t: Throwable) {
            call.cancel()
            onFailure()
        }
    })
}
