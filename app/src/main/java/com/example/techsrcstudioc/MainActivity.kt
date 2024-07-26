package com.example.techsrcstudioc

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.techsrcstudioc.Data.API.Repository
import com.example.techsrcstudioc.Data.API.SpotifyAPI
import com.example.techsrcstudioc.Data.VMs.GeneralViewModel
import com.example.techsrcstudioc.Data.VMs.LoginLogoutViewModel
import com.example.techsrcstudioc.Data.VMs.MainViewModel
import com.example.techsrcstudioc.Data.VMs.SearchViewModel
import com.example.techsrcstudioc.homePage.HomeComp
import com.example.techsrcstudioc.loginPage.LoginComp
import com.example.techsrcstudioc.ui.theme.TechsrcstudiocTheme
import com.example.techsrcstudioc.ui.theme.mainBGC

class MainActivity : ComponentActivity() {
    //dependency for permission + handle if not granted
    val TAG = "MainActivity -->"
    val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        Log.d(TAG, "permissions: $permissions")
        permissions.forEach { perm ->
            if (perm.value == false) {
                Toast.makeText(this, "${perm.key} denided", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //view models dependency
        var context = this
        val repo = Repository()

        val mainVM = MainViewModel(repo)
        val gerenalVM = GeneralViewModel(requestPermissionsLauncher, context)
        val lsVM = LoginLogoutViewModel(context)
        val searchVM = SearchViewModel(gerenalModel = gerenalVM, owner = this, mainViewModel = mainVM)

        //enableEdgeToEdge()
        //permissions function
        //gerenalVM.checkPermissions()

        setContent {
            TechsrcstudiocTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = mainBGC
                ) {
                    val navStateBig = rememberNavController()
                    val startDestination = if(gerenalVM.IsExpiredToken()) "loginPage" else "homePage"
                    NavHost(navController = navStateBig, startDestination = startDestination) {
                        composable("loginPage") {
                            LoginComp(
                                navController = navStateBig,
                                generalModel = gerenalVM,
                                lsModel = lsVM
                            )

                        }

                        composable(
                            route = "DoNotUseThisRoute",
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern =
                                        "http://192.168.1.103:3000/api/auth/spotify/callback#access_token={token}&token_type={type}&expires_in={expire}"
                                    action = Intent.ACTION_VIEW
                                }
                            ),
                            arguments = listOf(
                                navArgument("token") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument("type") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument("expire") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) { entry ->
                            gerenalVM.saveData("token", entry.arguments?.get("token").toString())
                            gerenalVM.saveData("expire", gerenalVM.calculateExpire())
                            Log.d(TAG, "onCreate: ${entry.arguments?.get("token")}")
                            Log.d(TAG, "onCreate: ${entry.arguments?.get("expire")}")
                            navStateBig.navigate("homePage")


                        }
                        composable(route = "homePage"){
                            HomeComp(
                                navController = navStateBig,
                                generalModel = gerenalVM,
                                lsModel = lsVM,
                                searchModel = searchVM
                            )
                        }

                        /*
                        composable("detail/{index}",
                        arguments = listOf(
                            navArgument("index") {
                                type = NavType.IntType
                            }
                        )) {
                        var index = it.arguments?.getInt("index")
                        DetailComp(
                            model = model,
                            navController = navController,
                            index = index as Int,
                            animatedVisibilityScope = this
                        )
                        }
                         */

                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TechsrcstudiocTheme {

    }
}