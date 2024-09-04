package com.example.readit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readit.ui.theme.screens.about.AboutScreen
import com.example.readit.ui.theme.screens.bookmanager.AddBookScreen
import com.example.readit.ui.theme.screens.bookmanager.ViewBookScreen
import com.example.readit.ui.theme.screens.home.HomeScreen
import com.example.readit.ui.theme.screens.login.LoginScreen
import com.example.readit.ui.theme.screens.menu.MenuScreen
import com.example.readit.ui.theme.screens.products.AddProductsScreen
import com.example.readit.ui.theme.screens.products.ViewProductsScreen
import com.example.readit.ui.theme.screens.signup.SignUpScreen
import com.example.readit.ui.theme.screens.splash.SplashScreen


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
        composable(ADD_BOOK){
            AddBookScreen(navController = navController)
        }
        composable(VIEW_BOOKS){
            ViewBookScreen(navController = navController)
        }
        composable(ROUT_MENU) {
            MenuScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL) {
            AddProductsScreen(navController = navController)
        }
        composable(VIEW_PRODUCTS_URL) {
            ViewProductsScreen(navController = navController)
        }

    }
}


