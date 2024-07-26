package com.example.techsrcstudioc.homePage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.techsrcstudioc.Data.Models.searchModel.Item
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel
import com.example.techsrcstudioc.Data.VMs.TrackViewModel
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.addons.shimmerEffect
import com.example.techsrcstudioc.ui.theme.lightWhiteFontColor
import com.example.techsrcstudioc.ui.theme.searchIconColor
import com.example.techsrcstudioc.ui.theme.selectedBottomIcon
import com.example.techsrcstudioc.ui.theme.unselectedBottomIcon

@Composable
fun BottomBarComp(
    navController: NavController,
    searchModel: SearchViewModel,
    generalModel: GeneralViewModel,
    trackModel:TrackViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        AnimatedVisibility(visible = true) {
            if(trackModel.selectedTrack.name !=""){
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    //info and controll
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF42382F))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.LightGray)
                            ) {
                                //todo open this music
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth(0.9f)
                                .padding(start = 12.dp, end = 12.dp, top = 9.dp, bottom = 9.dp)
                                .clip(RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color.LightGray)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    model = trackModel.selectedTrack.album.images[2].url,
                                    contentDescription = "Image of track",
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 14.dp)
                            ) {
                                Text(
                                    text = "${trackModel.selectedTrack.name}",
                                    fontSize = 15.sp,
                                    lineHeight = 21.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color.White
                                )
                                Text(
                                    text = searchModel.getArtistString(trackModel.selectedTrack.artists),
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(400),
                                    color = lightWhiteFontColor
                                )
                            }
                        }
                        Box(Modifier.padding(end = 12.dp)) {
                            IconButton(
                                modifier = Modifier.size(22.dp),
                                onClick = {
                                    //todo play
                                }) {
                                androidx.compose.material.Icon(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .offset(x = 2.dp),
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }

                    }
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