package com.example.weatherapp.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.hourlyItemBackgroundColor
import com.example.weatherapp.ui.theme.hourlyItemCornerColor
import com.example.weatherapp.ui.theme.textColorDate
import com.example.weatherapp.ui.theme.dailyItemCornerColor
import com.example.weatherapp.ui.theme.dailyItemSecondColor

@Composable
fun HourlyItem(
    text: String,
    temperature: String,
    onClick: () -> Unit,
    isNow: Boolean = false,
    isFirst: Boolean = false,
    icon: Int
) {

    Column(
        modifier = Modifier
            .padding(  start = if (isFirst) padding_32 else 0.dp,end = padding_22)
            .clip(RoundedCornerShape(cornerRadius_28))
            .clickable {  onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = padding_16),
            text = if (isNow) stringResource(R.string.now) else text,
            fontSize = fontSize_15,
            color = textColorDate
        )

        Box(
            modifier = Modifier
                .background(
                    if (isNow) dailyItemCornerColor else hourlyItemCornerColor,
                    shape = CircleShape
                )
                .size(cardSize_52)
        ) {
            Card(
                Modifier
                    .size(cardSize_50)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = if (isNow) dailyItemSecondColor else hourlyItemBackgroundColor)
            ) {}
            Image(
                modifier = Modifier
                    .size(imageSize_28)
                    .align(Alignment.Center),
                painter = painterResource(id = icon),
                contentDescription = ""
            )
        }

        Text(
            modifier = Modifier.padding(padding_8),
            text = temperature,
            fontSize = fontSize_15,
            color = textColorDate
        )
    }
}