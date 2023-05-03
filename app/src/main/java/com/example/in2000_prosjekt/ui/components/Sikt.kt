package com.example.in2000_prosjekt.ui.components

import com.example.in2000_prosjekt.R

// Klart vær = God sikt = Sikt på mer enn 10 km (INGEN SKYER) under 25%
// Lettskyet = Moderat sikt = Sikt på 4-10 km (LITEN SKY) 25% - 50%
// Delvis skyet = Dårlig sikt = Sikt på 1-4 km (STOR SKY) 50% - 75%
// Skyet = Tåke = Sikt på mindre enn 1 km (STOR + LITEN SKY) over 100%

enum class Sikt(val weather: String) {
    CLOUDY("cloudy") {
        override fun getRightWeather(): String {
            return "Meget dårlig sikt"
        }

        override fun getIcon(): Int {
            return R.drawable.small_clouds_both
        }

        override fun sightInKm(): String {
            return "> 1 km sikt"
        }
    },
    PARTLYCLOUDY("partlycloudy") {
        override fun getRightWeather(): String {
            return "Dårlig sikt"
        }

        override fun getIcon(): Int {
            return R.drawable.small_clouds_big
        }

        override fun sightInKm(): String {
            return "1-4 km sikt"
        }
    },
    FAIR("fair") {
        override fun getRightWeather(): String {
            return "Lettskyet"
        }

        override fun getIcon(): Int {
            return R.drawable.small_clouds_small
        }

        override fun sightInKm(): String {
            return "4-10 km sikt"

        }
    },
    CLEARSKY("clearsky") {
        override fun getRightWeather(): String {
            return "Klart vær"
        }

        override fun getIcon(): Int {
            return R.drawable.small_clouds_clear
        }

        override fun sightInKm(): String {
            return "<10 km sikt"

        }
    };

    abstract fun getRightWeather(): String
    abstract fun getIcon(): Int
    abstract fun sightInKm(): String
}