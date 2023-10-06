package com.example.weatherapp.app.screens.report

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.ElevationCustom
import com.example.weatherapp.app.screens.SmallItem
import com.example.weatherapp.app.screens.fontSize_22
import com.example.weatherapp.app.screens.fontSize_24
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_28
import com.example.weatherapp.app.screens.padding_32
import com.example.weatherapp.app.screens.padding_5
import com.example.weatherapp.app.screens.topAppBarHeight_64
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportScreen() {

    Scaffold(
        Modifier.navigationBarsPadding().fillMaxSize(),
        containerColor = backgroundColor,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            ElevationCustom(
                modifier = Modifier
                    .size(220.dp)
                    .offset(x = (40).dp, y = (-70).dp)
                    .align(Alignment.TopEnd),
                color = colorWhite.copy(alpha = 0.1f)
            )
            Column(
                Modifier.statusBarsPadding().fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topAppBarHeight_64)
                ) {
                    IconButton(
                        modifier = Modifier
//                            .padding(start = padding_16)
                            .align(Alignment.CenterStart), onClick = {

                        }) {
                        Icon(
                            painterResource(id = R.drawable.arrow2),
                            contentDescription = "back",
                            tint = colorWhite,
                        )
                    }

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.forecast_report),
                        fontSize = fontSize_24,
                        color = colorWhite
                    )
                }

                Text(
                    text = stringResource(R.string.today),
                    color = colorWhite,
                    modifier = Modifier.padding(
                        start = padding_32,
                        top = it.calculateTopPadding() + padding_16,
//                    bottom = padding_16
                    ),
                    fontSize = fontSize_22
                )

                LazyRow(
                    modifier = Modifier
                        .padding(start = padding_32, end = padding_5)
                        .fillMaxWidth()
                ) {
                    items(count = 7) {
                        SmallItem(text = "01:00", temperature = "18°")
                    }
                }

                Text(
                    text = stringResource(R.string.next_forecast),
                    color = colorWhite,
                    fontSize = fontSize_22,
                    modifier = Modifier.padding(start = padding_32, bottom = padding_28)
                )

                LazyColumn(
                    Modifier
                        .padding(horizontal = padding_16)
                        .fillMaxHeight()
                ) {
                    items(count = 9) {
                        XLargeItem("Friday", "March,02", "17°")
                    }
                }

            }

            ElevationCustom(
                modifier = Modifier
                    .offset(x = (-110).dp, y = (70).dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}
