package com.amicum.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val smallPadding: Dp = 5.dp,
    val normalPadding: Dp = 10.dp,
    val mediumPadding: Dp = 20.dp,
    val bigPadding: Dp = 30.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
