package com.megamind.megamindmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.megamind.megamindmovies.ui.screen.DetailScreen
import com.megamind.megamindmovies.ui.screen.ListMoviesScreen
import com.megamind.megamindmovies.ui.theme.MegaMindMoviesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaMindMoviesTheme {


                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SharedTransitionLayout() {
                        NavHost(navController = navController, startDestination = "list") {


                            composable(route = "list") {
                                ListMoviesScreen(
                                    animatedVisibilityScope = this,
                                    onItemClick = { title, detail, image ->
                                        navController.navigate("detail/$title/$detail/$image")


                                    }
                                )
                            }


                            composable(
                                route = "detail/{title}/{detail}/{image}",
                                arguments = listOf(
                                    navArgument("title") { type = NavType.StringType },
                                    navArgument("detail") { type = NavType.StringType },
                                    navArgument("image") { type = NavType.IntType },
                                )
                            ) { navBackStack ->

                                DetailScreen(
                                    animatedVisibilityScope = this,
                                    title = navBackStack.arguments?.getString("title")!!,
                                    image = navBackStack.arguments?.getInt("image")!!,
                                    details = navBackStack.arguments?.getString("detail")!!
                                )


                            }

                        }


                    }
                }
            }
        }
    }
}
