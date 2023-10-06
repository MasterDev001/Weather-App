package com.example.weatherapp.app.screens.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.app.screens.card_Sizes_110
import com.example.weatherapp.app.screens.cornerRadius_28
import com.example.weatherapp.app.screens.fontSize_12
import com.example.weatherapp.app.screens.padding_10
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.infoIconColor
import com.example.weatherapp.ui.theme.xLargeItemCornerColor
import com.example.weatherapp.ui.theme.xLargeItemSecondColor

@Composable
fun MiddleItem(modifier: Modifier, icon: Int, title: String, text: String) {

    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        xLargeItemCornerColor, backgroundColor
                    )
                ), shape = RoundedCornerShape(cornerRadius_28)
            )
            .fillMaxWidth()
            .height(card_Sizes_110)
    ) {

        Box(
            Modifier
                .padding(top = padding_3, start = padding_3, end = padding_3)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            xLargeItemSecondColor, backgroundColor
                        )
                    ), shape = RoundedCornerShape(cornerRadius_28)
                )
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                Modifier
//                    .padding( top = padding_16)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = padding_10),
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = infoIconColor
                )
                Text(text = title, color = infoIconColor, fontSize = fontSize_12)
            }

            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = colorWhite
            )
        }
    }
}