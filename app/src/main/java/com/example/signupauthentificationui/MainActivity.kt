package com.example.signupauthentificationui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signupauthentificationui.ui.theme.SignUpAuthentificationUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpAuthentificationUITheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen"){
        composable("home_screen"){
            HomeScreen(navController)
        }
        composable("input_screen"){
            InputScreen(navController)
        }
        composable("detail_screen/{name}/{age}/{sex}/{email}"){navBackStackEntry ->
            DetailScreen(
                name = navBackStackEntry.arguments?.getString("name") ?: "",
                age = navBackStackEntry.arguments?.getString("age") ?: "",
                sex = navBackStackEntry.arguments?.getString("sex") ?: "",
                email = navBackStackEntry.arguments?.getString("email") ?: ""
            )
        }
    }
}