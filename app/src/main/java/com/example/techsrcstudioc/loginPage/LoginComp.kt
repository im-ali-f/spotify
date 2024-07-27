package com.example.techsrcstudioc.loginPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.ui.theme.mainBGC
import com.example.techsrcstudioc.ui.theme.poppins
import com.example.techsrcstudioc.ui.theme.spotifyIconColor
import com.example.techsrcstudioc.ui.theme.unclickedBTNColor

@Composable
fun LoginComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    lsModel: LoginLogoutViewModel
) {
    Column(
        Modifier
            .padding(top = 100.dp)//todo can be dynamic
            .fillMaxSize()
            .background(mainBGC)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(72.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "spotify logo",
                tint = spotifyIconColor
            )
            Spacer(modifier = Modifier.height(90.dp))
            Button(
                onClick = {
                   lsModel.calURL()
                },
                modifier = Modifier
                    .width(146.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(100))
                    .background(unclickedBTNColor),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    textAlign = TextAlign.Center, fontFamily = poppins,
                    text = "Log In",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
            }
        }


    }

}