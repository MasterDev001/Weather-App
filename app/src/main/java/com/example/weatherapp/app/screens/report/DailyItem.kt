package com.example.weatherapp.app.screens.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.app.screens.card_Sizes_120
import com.example.weatherapp.app.screens.cornerRadius_32
import com.example.weatherapp.app.screens.fontSize_13
import com.example.weatherapp.app.screens.fontSize_16
import com.example.weatherapp.app.screens.fontSize_52
import com.example.weatherapp.app.screens.imageSize_76
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.textColorDate
import com.example.weatherapp.ui.theme.dailyItemCornerColor
import com.example.weatherapp.ui.theme.dailyItemSecondColor

@Composable
fun DailyItem(day: String, date: String, onClick: () -> Unit, temperature: String, icon: Int) {

    Box(
        modifier = Modifier
            .padding(bottom = padding_16)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        dailyItemCornerColor
//                        smallItemBackgroundColor
                        , backgroundColor
                    )
                ), shape = RoundedCornerShape(cornerRadius_32)
            )
            .fillMaxWidth()
            .height(card_Sizes_120)
    ) {

        Box(
            Modifier
                .padding(top = padding_3, start = padding_3)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            dailyItemSecondColor, backgroundColor
                        )
                    ), shape = RoundedCornerShape(cornerRadius_32)
                )

        )

        Row(
            Modifier
                .clickable { onClick() }
                .padding(horizontal = padding_16)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier) {
                Text(text = day, color = Color.White, fontSize = fontSize_16)
                Text(text = date, color = textColorDate, fontSize = fontSize_13)
            }
            Text(
                text = temperature,
                color = Color.White,
                fontSize = fontSize_52
            )
            Image(
                modifier = Modifier.size(imageSize_76),
                painter = painterResource(id = icon),
                contentDescription = ""
            )

        }
    }
}