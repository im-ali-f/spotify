package com.example.techsrcstudioc.homePage.topbarComp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.R

@Composable
fun TopBarComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    lsModel: LoginLogoutViewModel
) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 22.dp, bottom = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Search",//todo dynamic it
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            color = Color.White
        )

        IconButton(
            modifier = Modifier.size(40.dp),
            onClick = {
                //todo
            }) {
            Icon(
                modifier = Modifier.size(33.dp),
                painter = painterResource(id = R.drawable.more),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

}