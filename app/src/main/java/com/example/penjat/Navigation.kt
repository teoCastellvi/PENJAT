package com.example.penjat


sealed class Routes(val route: String) {
    object SplashScreen:Routes("splash_screen")
    object MenuScreen:Routes("menu_screen")
    object GameScreen:Routes("game_screen/{dificultat}"){
        fun createRoute(dificultat: String) = "game_screen/$dificultat"
    }

}
