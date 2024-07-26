package com.example.techsrcstudioc.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.homePage.topbarComp.SearchBar
import com.example.techsrcstudioc.homePage.topbarComp.TopBarComp
import com.example.techsrcstudioc.ui.theme.mainBGC

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
        SearchBar(navController = navController, generalModel = generalModel )
    }


}