package com.example.techsrcstudioc.searchPage

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel
import com.example.techsrcstudioc.ui.theme.lightWhiteFontColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    searchModel: SearchViewModel = viewModel()
) {
    Column(Modifier.fillMaxSize()) {
        SearchBar(navController = navController, generalModel = generalModel, searchModel)


        //for test
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.LightGray)
                ) {
                    //todo play this music
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(Color.LightGray)
            ) {
                //todo inja image biad
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp)) {
                Text(
                    text = "SLICING",
                    fontSize = 15.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
                Text(
                    text = "ali,zahra,moniba",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = lightWhiteFontColor
                )
            }
        }
        //end for test
    }
}