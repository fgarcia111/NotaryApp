package com.example.notaryapp.feature_date.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notaryapp.feature_date.presentation.ask_date.AddEditDateScreen
import com.example.notaryapp.feature_date.presentation.dates.DateViewModel
import com.example.notaryapp.feature_date.presentation.dates.DatesScreen
import com.example.notaryapp.feature_date.presentation.util.Screen
import com.example.notaryapp.ui.theme.NotaryAppTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        enableEdgeToEdge()
        setContent {
            NotaryAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DatesScreen.route
                    ) {
                        composable(route = Screen.DatesScreen.route) {
                            DatesScreen(navController)
                        }
                        composable(route = Screen.AddEditDateScreen.route) {
                            AddEditDateScreen(navController)
                        }
                    }
                }

            }
        }
    }
    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.POST_NOTIFICATIONS
        )

        requestPermissionsLauncher.launch(permissions)
    }

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.values.all { it }
            if (!allGranted) {
                // Puedes mostrar un mensaje diciendo que los permisos son necesarios
            }
        }
}