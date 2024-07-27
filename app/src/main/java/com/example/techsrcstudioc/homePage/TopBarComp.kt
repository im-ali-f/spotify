package com.example.techsrcstudioc.homePage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.Data.VMs.TrackViewModel
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.ui.theme.logOutButton

@Composable
fun TopBarComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    lsModel: LoginLogoutViewModel,
    trackModel:TrackViewModel
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 17.dp, end = 10.dp, top = 22.dp, bottom = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = if(generalModel.selectedBottomBar ==1)"Search" else "history",//todo dynamic it
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color.White
            )

            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = {
                    generalModel.showLogout = !generalModel.showLogout
                }) {
                Icon(
                    modifier = Modifier.size(33.dp),
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        AnimatedVisibility(visible =generalModel.showLogout ) {
            Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                Button(
                    onClick = {
                        generalModel.logoutFunctionallity(navController)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .height(55.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(logOutButton),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Log Out",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600)
                    )
                }
            }

        }
    }
    

}