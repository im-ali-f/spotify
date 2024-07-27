package com.example.techsrcstudioc.homePage

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel
import com.example.techsrcstudioc.Data.VMs.TrackViewModel
import com.example.techsrcstudioc.searchPage.SearchBar
import com.example.techsrcstudioc.searchPage.SearchComp
import com.example.techsrcstudioc.ui.theme.mainBGC

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.techsrcstudioc.R
import com.example.techsrcstudioc.ui.theme.activatedBTN

import com.example.techsrcstudioc.ui.theme.lightWhiteFontColor
import com.example.techsrcstudioc.ui.theme.progressColor
import com.example.techsrcstudioc.ui.theme.secondaryIconWhiteColor
import com.example.techsrcstudioc.ui.theme.selectedBottomIcon
import com.example.techsrcstudioc.ui.theme.thumbColor
import com.example.techsrcstudioc.ui.theme.unProgressColor
import com.example.techsrcstudioc.ui.theme.unselectedBottomIcon

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableInteractionSource")
@Composable
fun HomeComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    lsModel: LoginLogoutViewModel,
    searchModel: SearchViewModel,
    trackModel: TrackViewModel
) {
    val innerNavState = rememberNavController()
    //better in mainactivity

    Scaffold(
        topBar = {
            TopBarComp(navController = navController, generalModel = generalModel , lsModel = lsModel )
        },
        bottomBar = {
            BottomBarComp(navController = innerNavState, generalModel = generalModel, searchModel = searchModel, trackModel = trackModel )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mainBGC),
        backgroundColor = mainBGC

    ) {
        contentPadding->

        val screenWidth = LocalConfiguration.current.screenWidthDp
        val screenHeight = LocalConfiguration.current.screenHeightDp
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        val scope = rememberCoroutineScope()

        Scaffold(
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) { contentPaddingInner ->
            if (trackModel.showBottomSheet) {
                ModalBottomSheet(
                    dragHandle = {},
                    containerColor = Color.LightGray,
                    shape = RoundedCornerShape(0),
                    modifier = Modifier.fillMaxSize(),
                    onDismissRequest = {
                        trackModel.showBottomSheet = false
                    },
                    sheetState = sheetState,

                    ) {

                    Scaffold(
                        Modifier
                            .fillMaxSize()
                            .padding(start = 25.dp, end = 25.dp, top = 25.dp)
                            .background(Color.LightGray),
                        backgroundColor = Color.Transparent,
                        topBar = {
                            //topbar
                            Box(Modifier.fillMaxWidth()) {
                                IconButton(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.CenterStart),
                                    onClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                trackModel.showBottomSheet = false
                                            }
                                        }
                                    }) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }

                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .offset(y = 3.dp)
                                        .padding(start = 14.dp)
                                        .align(Alignment.Center),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "PLAYING FROM SEARCH",
                                        fontSize = 9.sp,
                                        lineHeight = 13.5.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "${trackModel.selectedTrack.name}",
                                        fontSize = 11.sp,
                                        lineHeight = 16.5.sp,
                                        fontWeight = FontWeight(400),
                                        color = lightWhiteFontColor
                                    )
                                }
                            }
                        },
                        bottomBar = {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 80.dp)
                            ) {
                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = "${trackModel.selectedTrack.name}",
                                        fontSize = 17.sp,
                                        lineHeight = 25.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.White
                                    )
                                }

                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(0.6f),
                                        text = "${trackModel.getArtistString(trackModel.selectedTrack.artists)}",
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontWeight = FontWeight(400),
                                        color = lightWhiteFontColor
                                    )

                                    Row(
                                        Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            modifier = Modifier
                                                .size(20.dp),
                                            onClick = {
                                                if (trackModel.muted) {
                                                    trackModel.muted = false
                                                    trackModel.voloume = 0.5f
                                                } else {
                                                    trackModel.muted = true
                                                    trackModel.voloume = 0f
                                                }
                                            }) {
                                            Icon(
                                                modifier = Modifier.size(18.dp),
                                                painter = painterResource(id = if (trackModel.muted) R.drawable.volume_off else R.drawable.volume_on),
                                                contentDescription = null,
                                                tint = secondaryIconWhiteColor
                                            )
                                        }
                                        val interactionSource = MutableInteractionSource()
                                        Box(contentAlignment = Alignment.Center) {
                                            Row(
                                                Modifier
                                                    .fillMaxWidth(0.85f)
                                                    .height(1.5.dp)
                                                    .background(
                                                        unProgressColor
                                                    )
                                            ) {}
                                            androidx.compose.material3.Slider(
                                                modifier = Modifier.semantics {
                                                    contentDescription = "Localized Description"
                                                },
                                                value = trackModel.voloume,
                                                onValueChange = { trackModel.voloume = it },
                                                valueRange = 0f..1f,
                                                interactionSource = interactionSource,
                                                onValueChangeFinished = {
                                                    if (trackModel.voloume == 0f) {
                                                        trackModel.muted = true
                                                    } else {
                                                        trackModel.muted = false
                                                    }
                                                },
                                                colors = SliderColors(
                                                    thumbColor = thumbColor,
                                                    activeTrackColor = progressColor,
                                                    activeTickColor = Color.Transparent,
                                                    inactiveTrackColor = Color.Transparent,
                                                    inactiveTickColor = Color.Transparent,
                                                    disabledInactiveTrackColor = Color.Transparent,
                                                    disabledActiveTrackColor = Color.Transparent,
                                                    disabledThumbColor = Color.Transparent,
                                                    disabledActiveTickColor = Color.Transparent,
                                                    disabledInactiveTickColor = Color.Transparent,
                                                ),
                                                thumb = {
                                                    androidx.compose.material3.SliderDefaults.Thumb( //androidx.compose.material3.SliderDefaults
                                                        interactionSource = interactionSource,
                                                        thumbSize = DpSize(9.dp, 9.dp),
                                                        modifier = Modifier.offset(y = 6.dp, x = 5.dp),
                                                        colors = SliderColors(
                                                            thumbColor = thumbColor,
                                                            activeTrackColor = progressColor,
                                                            activeTickColor = Color.Transparent,
                                                            inactiveTrackColor = Color.Transparent,
                                                            inactiveTickColor = Color.Transparent,
                                                            disabledInactiveTrackColor = Color.Transparent,
                                                            disabledActiveTrackColor = Color.Transparent,
                                                            disabledThumbColor = Color.Transparent,
                                                            disabledActiveTickColor = Color.Transparent,
                                                            disabledInactiveTickColor = Color.Transparent,
                                                        )
                                                    )
                                                },

                                                )

                                        }

                                    }
                                }



                                PercentageSlider(trackModel)
                                //CONTROLL play
                                Row(
                                    Modifier
                                        .fillMaxWidth().padding(start = 15.dp , end = 15.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    //shuffle
                                    IconButton(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .size(30.dp),
                                        onClick = {
                                            trackModel.shuffle = true
                                        }) {
                                        androidx.compose.material3.Icon(
                                            modifier = Modifier.size(25.dp),
                                            painter = painterResource(id = R.drawable.shuffle),
                                            contentDescription = null,
                                            tint = if(trackModel.shuffle) activatedBTN else Color.White
                                        )
                                    }
                                    //previous
                                    IconButton(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .size(30.dp),
                                        onClick = {

                                        }) {
                                        androidx.compose.material3.Icon(
                                            modifier = Modifier.size(30.dp),
                                            painter = painterResource(id = R.drawable.previous),
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                    //play pause
                                    IconButton(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp),
                                        onClick = {
                                            trackModel.playing =!trackModel.playing
                                        }) {
                                        Image(
                                            modifier = Modifier.size(70.dp),
                                            contentScale = ContentScale.Crop,
                                            painter = painterResource(id = if(trackModel.playing) R.drawable.pausebig else R.drawable.playbig),
                                            contentDescription = null,
                                        )
                                    }
                                    //next
                                    IconButton(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .size(30.dp),
                                        onClick = {

                                        }) {
                                        androidx.compose.material3.Icon(
                                            modifier = Modifier.size(30.dp),
                                            painter = painterResource(id = R.drawable.next),
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }

                                    //shuffle
                                    IconButton(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .size(30.dp),
                                        onClick = {
                                            if(trackModel.repeatNum !=3){
                                                trackModel.repeatNum++
                                            }else{
                                                trackModel.repeatNum = 1
                                            }

                                        }) {
                                        androidx.compose.material3.Icon(
                                            modifier = Modifier.size(25.dp),
                                            painter = painterResource(id = if(trackModel.repeatNum ==1) R.drawable.repeat_off else if(trackModel.repeatNum ==2) R.drawable.repeat_off else R.drawable.repeat_current_music),
                                            contentDescription = null,
                                            tint = if(trackModel.repeatNum !=1) activatedBTN else Color.White
                                        )
                                    }
                                }

                            }
                        }
                    ) { padding ->
                        //center Image
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((screenWidth - 50).dp)
                                    .align(Alignment.Center)
                                    .background(Color.LightGray)
                            ){
                                if(trackModel.selectedTrack.album.images.size>0){
                                    AsyncImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = trackModel.selectedTrack.album.images[0].url,
                                        contentDescription = "Image of track",
                                        contentScale = ContentScale.Crop
                                    )
                                }

                            }
                        }
                    }
                }
            }
            SharedTransitionLayout {
                NavHost(navController = innerNavState, startDestination = "searchPart") {

                    composable("searchPart") {
                        SearchComp(navController = navController, generalModel = generalModel, searchModel =searchModel, trackModel )
                    }
                    composable("historyPart") {

                    }


                }
            }
        }


    }



}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun PercentageSlider(trackModel: TrackViewModel) {


    val interactionSource = MutableInteractionSource()
    Row(Modifier.fillMaxWidth()) {
        Box(contentAlignment = Alignment.Center) {
            Row(
                Modifier
                    .fillMaxWidth(0.95f)
                    .height(1.5.dp)
                    .background(
                        unProgressColor
                    )
            ) {}
            androidx.compose.material3.Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Localized Description" },
                value = trackModel.trackListened,
                onValueChange = { trackModel.trackListened = it },
                valueRange = 0f..1f,
                interactionSource = interactionSource,
                onValueChangeFinished = {

                },
                colors = SliderColors(
                    thumbColor = Color.White,
                    activeTrackColor = progressColor,
                    activeTickColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent,
                    disabledInactiveTrackColor = Color.Transparent,
                    disabledActiveTrackColor = Color.Transparent,
                    disabledThumbColor = Color.Transparent,
                    disabledActiveTickColor = Color.Transparent,
                    disabledInactiveTickColor = Color.Transparent,
                ),
                thumb = {
                    androidx.compose.material3.SliderDefaults.Thumb( //androidx.compose.material3.SliderDefaults
                        interactionSource = interactionSource,
                        thumbSize = DpSize(9.dp, 9.dp),
                        modifier = Modifier.offset(y = 6.dp, x = 5.dp),
                        colors = SliderColors(
                            thumbColor = Color.White,
                            activeTrackColor = progressColor,
                            activeTickColor = Color.Transparent,
                            inactiveTrackColor = Color.Transparent,
                            inactiveTickColor = Color.Transparent,
                            disabledInactiveTrackColor = Color.Transparent,
                            disabledActiveTrackColor = Color.Transparent,
                            disabledThumbColor = Color.Transparent,
                            disabledActiveTickColor = Color.Transparent,
                            disabledInactiveTickColor = Color.Transparent,
                        )
                    )
                },

                )

        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .offset(y = -18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${trackModel.calculatepassedTime()}",
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(400),
            color = lightWhiteFontColor
        )
        Text(
            text = "${trackModel.mins}:${trackModel.seconds}",
            fontSize = 11.sp,
            lineHeight = 16.5.sp,
            fontWeight = FontWeight(400),
            color = lightWhiteFontColor
        )
    }

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


    }


}