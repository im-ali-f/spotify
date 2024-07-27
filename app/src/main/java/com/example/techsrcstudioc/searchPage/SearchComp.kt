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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel
import com.example.techsrcstudioc.Data.VMs.TrackViewModel
import com.example.techsrcstudioc.ui.theme.lightWhiteFontColor
import com.example.techsrcstudioc.ui.theme.poppins

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    searchModel: SearchViewModel,
    trackModel:TrackViewModel
) {
    Column(Modifier.fillMaxSize()) {
        SearchBar(navController = navController, generalModel = generalModel, searchModel)



        LazyColumn(Modifier.fillMaxSize()) {
            item{
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(searchModel.foundedItems) { item ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = Color.LightGray)
                            ) {
                                trackModel.selectedTrack = item
                                trackModel.play()
                                trackModel.playing = true
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .background(Color.LightGray)
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = item.album.images[2].url,
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
                                text = "${item.name}", fontFamily = poppins,
                                fontSize = 15.sp,
                                lineHeight = 21.sp,
                                fontWeight = FontWeight(400),
                                color = Color.White
                            )
                            Text(
                                text = searchModel.getArtistString(item.artists), fontFamily = poppins,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(400),
                                color = lightWhiteFontColor
                            )
                        }
                    }
            }
            item{
                Spacer(modifier = Modifier.height(130.dp))
            }
            item {

                LaunchedEffect(true) {
                    if(searchModel.foundedItems.isNotEmpty()){
                        searchModel.ContinueGetSearchedItemsFunctionallity()
                    }

                }


            }
        }
    }
}