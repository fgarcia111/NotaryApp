package com.example.notaryapp.feature_date.presentation.util

sealed class Screen(val route: String) {
    object DatesScreen: Screen("dates_screen")
    object AddEditDateScreen: Screen("add_dates_screen")
}