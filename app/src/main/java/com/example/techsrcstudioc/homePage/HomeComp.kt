package com.example.techsrcstudioc.homePage

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.techsrcstudioc.TrackComp.customToastUI
import com.example.techsrcstudioc.searchPage.SearchBar
import com.example.techsrcstudioc.searchPage.SearchComp
import com.example.techsrcstudioc.ui.theme.mainBGC

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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


        SharedTransitionLayout {
            NavHost(navController = innerNavState, startDestination = "trackPart") {

                composable("searchPart") {
                    SearchComp(navController = navController, generalModel = generalModel, searchModel =searchModel, trackModel )
                }
                composable("trackPart") {
                    customToastUI()
                }


            }
        }
    }


}