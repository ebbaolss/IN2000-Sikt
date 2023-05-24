package com.example.in2000_prosjekt.ui.components

import com.example.in2000_prosjekt.R

fun getRightCloudiness(clouds: Float): Int {
    return when (clouds.toInt()) {
        in 75..100 -> R.drawable.small_clouds_both
        in 50..74 -> R.drawable.small_clouds_big
        in 25..49 -> R.drawable.small_clouds_small
        else -> R.drawable.small_clouds_clear
    }
}

fun getRightSikt(sikt: Float): String {
    return when (sikt.toInt()) {
        in 75..100 -> "Meget dårlig sikt"
        in 50..74 -> "Dårlig sikt"
        in 25..49 -> "Lettskyet"
        else -> "Klart vær"
    }
}

fun getRightKm(km: Float): String {
    return when (km.toInt()){
        in 75..100 -> "< 1 km sikt"
        in 50..74 -> "1-4 km sikt"
        in 25..49 -> "4-10 km sikt"
        else -> "> 10 km sikt"
    }
}

fun getHighClouds(highclouds: Float): Int {
    return when (highclouds.toInt()) {
        in 75..100 -> R.drawable.clouds_high_both
        in 50..74 -> R.drawable.clouds_high_big
        in 25..49 -> R.drawable.clouds_high_small
        else -> R.drawable.klart
    }
}

fun getMidClouds(midclouds: Float): Int {
    return when (midclouds.toInt()) {
        in 75..100 -> R.drawable.clouds_mid_both
        in 50..74 -> R.drawable.clouds_mid_big
        in 25..49 -> R.drawable.clouds_mid_small
        else -> R.drawable.klart
    }
}

fun getLowClouds(lowclouds: Float): Int {
    return when (lowclouds.toInt()) {
        in 75..100 -> R.drawable.clouds_low_both
        in 50..74 -> R.drawable.clouds_low_big
        in 25..49 -> R.drawable.clouds_low_small
        else -> R.drawable.klart
    }
}
