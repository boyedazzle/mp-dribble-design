package com.example.moniepointdesignapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moniepointdesignapplication.ui.shipment.calculate.CalculateShipment
import com.example.moniepointdesignapplication.ui.shipment.history.ShipmentHistoryScreen
import com.example.moniepointdesignapplication.ui.shipment.home.CustomSearchItemScreen
import com.example.moniepointdesignapplication.ui.shipment.home.ShipmentHomeScreen
import com.example.moniepointdesignapplication.ui.shipment.success.ShipmentSuccessScreen
import com.example.moniepointdesignapplication.ui.theme.MoniepointDesignApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoniepointDesignApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "shipment_home_screen"
                    ) {
                        composable(
                            route = "shipment_home_screen"
                        ) {
                            ShipmentHomeScreen(
                                onCalculateClicked = {
                                    navController.navigate("shipment_calculate_screen")
                                },
                                onShipmentClicked = {
                                    navController.navigate("shipment_history_screen")
                                },
                                onSearchItemClick = {
                                    navController.navigate("custom_search_item_screen")
                                }
                            )
                        }

                        composable(
                            route = "shipment_history_screen"
                        ) {
                            ShipmentHistoryScreen(navController = navController)
                        }

                        composable(
                            route = "shipment_calculate_screen"
                        ) {
                            CalculateShipment(
                                navController = navController,
                                onCalculateButtonClick = {
                                    navController.navigate("shipment_success_screen")
                                }
                            )
                        }

                        composable(
                            route = "shipment_success_screen"
                        ) {
                            ShipmentSuccessScreen(
                                onBackToHomeClick = {
                                    navController.navigate("shipment_home_screen")
                                }
                            )
                        }

                        composable(
                            route = "custom_search_item_screen"
                        ) {
                            CustomSearchItemScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShipmentFlowPreview() {
    MoniepointDesignApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "shipment_home_screen"
            ) {
                composable(
                    route = "shipment_home_screen"
                ) {
                    ShipmentHomeScreen(
                        onCalculateClicked = {
                            navController.navigate("shipment_calculate_screen")
                        },
                        onShipmentClicked = {
                            navController.navigate("shipment_history_screen")
                        },
                        onSearchItemClick = {
                            navController.navigate("custom_search_item_screen")
                        }
                    )
                }

                composable(
                    route = "shipment_history_screen"
                ) {
                    ShipmentHistoryScreen(navController = navController)
                }

                composable(
                    route = "shipment_calculate_screen"
                ) {
                    CalculateShipment(
                        navController = navController,
                        onCalculateButtonClick = {
                            navController.navigate("shipment_success_screen")
                        }
                    )
                }

                composable(
                    route = "shipment_success_screen"
                ) {
                    ShipmentSuccessScreen(
                        onBackToHomeClick = {
                            navController.navigate("shipment_home_screen")
                        }
                    )
                }

                composable(
                    route = "custom_search_item_screen"
                ) {
                    CustomSearchItemScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}

