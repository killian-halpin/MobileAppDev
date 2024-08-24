package com.example.wishlist

import AddWishListItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wishlist.ui.theme.AllItemsScreen
import com.example.wishlist.ui.theme.HomeScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "homescreen") {
                composable("homescreen") {
                    HomeScreen(navController = navController)
                }
                composable("addWishListItem") {
                    AddWishListItem(navController = navController)
                }
                composable("AllItemsScreen") {
                    AllItemsScreen(navController = navController)
                }
            }
        }
    }
}
