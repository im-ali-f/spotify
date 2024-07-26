package com.example.techsrcstudioc.homePage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.ui.theme.selectedBottomIcon
import com.example.techsrcstudioc.ui.theme.unselectedBottomIcon

@Composable
fun BottomBarComp(navController: NavController, generalModel: GeneralViewModel) {
    Column(Modifier.fillMaxWidth()) {
        AnimatedVisibility(visible = true) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                //info and controll
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(

                            Color.Transparent,
                            Color(0xFF000000),


                            ),
                        startY = -600f
                    )
                )
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(50.dp),
                onClick = {
                    generalModel.selectedBottomBar = 1
                    navController.navigate("searchPart")
                }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.search_default),
                    contentDescription = null,
                    tint = if (generalModel.selectedBottomBar == 1) selectedBottomIcon else unselectedBottomIcon
                )
            }

            IconButton(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(50.dp),
                onClick = {
                    generalModel.selectedBottomBar = 2

                }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.history_default),
                    contentDescription = null,
                    tint = if (generalModel.selectedBottomBar == 2) selectedBottomIcon else unselectedBottomIcon
                )
            }


        }
    }

}