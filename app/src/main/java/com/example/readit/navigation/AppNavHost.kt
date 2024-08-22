package com.example.readit.navigation

import android.content.Context
import android.net.Uri
import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readit.ui.theme.screens.about.AboutScreen
import com.example.readit.ui.theme.screens.details.FileUploader
import com.example.readit.ui.theme.screens.details.fileReader
import com.example.readit.ui.theme.screens.details.fileReader
import com.example.readit.ui.theme.screens.home.HomeScreen
import com.example.readit.ui.theme.screens.login.LoginScreen
import com.example.readit.ui.theme.screens.menu.MenuScreen
import com.example.readit.ui.theme.screens.signup.SignUpScreen
import com.example.readit.ui.theme.screens.splash.SplashScreen
import java.io.FileReader


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ROUT_HOME) {
            HomeScreen(navController = navController)
        }

        composable(ROUT_ABOUT) {
            AboutScreen(navController = navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(ROUT_SIGNUP) {
            SignUpScreen(navController = navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(ROUT_FILEPICKER) {
            FileUploader()
        }
        composable(ROUT_MENU) {
            MenuScreen(navController = navController)
        }

    }
}


