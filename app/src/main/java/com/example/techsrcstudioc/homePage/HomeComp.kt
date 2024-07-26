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
import com.example.techsrcstudioc.searchPage.SearchBar
import com.example.techsrcstudioc.searchPage.SearchComp
import com.example.techsrcstudioc.ui.theme.mainBGC

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeComp(
    navController: NavController,
    generalModel: GeneralViewModel,
    lsModel: LoginLogoutViewModel
) {

    //better in mainactivity
    Scaffold(
        topBar = {
            TopBarComp(navController = navController, generalModel = generalModel , lsModel = lsModel )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mainBGC),
        backgroundColor = mainBGC

    ) {

        val innerNavState = rememberNavController()
        SharedTransitionLayout {
            NavHost(navController = innerNavState, startDestination = "searchPart") {

                composable("searchPart") {
                    SearchComp(navController = navController, generalModel = generalModel)
                }


            }
        }
    }


}