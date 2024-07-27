package com.example.techsrcstudioc.addons

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.ui.theme.errorAlertColor
import com.example.techsrcstudioc.ui.theme.poppins
import com.example.techsrcstudioc.ui.theme.successAlertColor
import com.example.techsrcstudioc.ui.theme.warningAlertColor

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val alertMap = mapOf(
    "" to mapOf(
        "icon" to null,
        "color" to Color.Transparent,
        "text" to ""
    ),

    "errorSpotify" to mapOf(
        "icon" to R.drawable.x,
        "color" to errorAlertColor,
        "text" to "Spotify Problem"
    ),
    "errorToken" to mapOf(
        "icon" to R.drawable.x,
        "color" to errorAlertColor,
        "text" to "Token Expired"
    ),
    "errorServerapi" to mapOf(
        "icon" to R.drawable.x,
        "color" to errorAlertColor,
        "text" to "Server Error"
    ),


    "successLogin" to mapOf(
        "icon" to R.drawable.tick,
        "color" to successAlertColor,
        "text" to "LoggedIn Successfully"
    ),

    "successLogout" to mapOf(
        "icon" to R.drawable.tick,
        "color" to successAlertColor,
        "text" to "Loggedout Successfully"
    ),



)

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AlertComp(selectedAlert: MutableState<String>) {
    val coroutineScope = rememberCoroutineScope()
    val delayJob = remember { mutableStateOf<Job?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedContent(
            modifier = Modifier.height(100.dp),
            contentAlignment = Alignment.BottomCenter,
            targetState = selectedAlert.value,
            content = { selectedAlert2 ->

                var alert = alertMap[selectedAlert2] as Map

                SideEffect {
                    if (selectedAlert.value != "") {
                        delayJob.value?.cancel()
                        delayJob.value = coroutineScope.launch {
                            delay(3000)
                            selectedAlert.value = ""
                        }
                    }
                }

                Box(contentAlignment = Alignment.BottomCenter) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp, start = 15.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(alert["color"] as Color),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        RightToLeftLayout {
                            Row(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                if (alert["icon"] != null) {
                                    Icon(
                                        modifier = Modifier.size(32.dp),
                                        painter = painterResource(id = alert["icon"] as Int),
                                        tint = Color.White,
                                        contentDescription = null
                                    )
                                }

                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = alert["text"] as String, fontFamily = poppins,
                                    fontWeight = FontWeight(600),
                                    fontSize = 12.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            },
            label = "alert Animation",
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000)
                ) with slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000)
                )
            }
        )
    }
}

@Composable
fun RightToLeftLayout(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}