package com.example.weatherapp.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.ui.theme.colorGray

@Composable
fun ElevationCustom(modifier: Modifier, color: Color = colorGray.copy(alpha = 0.22f) ) {

    Box(
        modifier = modifier
//            .offset(x = (-110).dp, y = (70).dp)
            .size(customElevationSize_300)
            .fillMaxWidth()
            .background(shape = CircleShape, color = Color.Transparent)
            .drawWithContent {
                val shadowBrush = Brush.radialGradient(
                    colors = listOf(
                        color,
                        Color.Transparent,
                    ),
                    center = center,
                    radius = size.width / 2f,
                )
                drawContent()

                drawRect(
                    brush = shadowBrush,
//
                )
            }
    )
}