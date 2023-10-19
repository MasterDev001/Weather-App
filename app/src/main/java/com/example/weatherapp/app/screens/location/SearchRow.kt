package com.example.weatherapp.app.screens.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.cardSize_50
import com.example.weatherapp.app.screens.cardSize_52
import com.example.weatherapp.app.screens.cornerRadius_12
import com.example.weatherapp.app.screens.imageSize_24
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_2
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorGray
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.dailyItemCornerColor
import com.example.weatherapp.ui.theme.dailyItemSecondColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRow(
    modifier: Modifier,
    cityList: List<CityData>,
    searchText: String,
    onLocationClick: () -> Unit,
    onSearchClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onCityClick: (CityData) -> Unit,
    onActiveChange: (Boolean) -> Unit
) {

    var active by rememberSaveable { mutableStateOf(false) }

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(dailyItemCornerColor, backgroundColor)
                    ), shape = RoundedCornerShape(cornerRadius_12)
                )
        ) {
//            Box(
//                Modifier
//                    .padding(top = padding_3, start = padding_3)
//                    .fillMaxSize()
//                    .align(Alignment.BottomEnd)
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(dailyItemSecondColor, backgroundColor)
//                        ), shape = RoundedCornerShape(cornerRadius_8)
//                    )
//            )
            DockedSearchBar(
                modifier = Modifier
                    .padding(top = padding_3, start = padding_3)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(dailyItemSecondColor, backgroundColor)
                        ), shape = RoundedCornerShape(cornerRadius_12)
                    ),
                query = searchText,
                onQueryChange = {
                    if (it == "") active = false
                    onValueChange(it)
                },
                shape = RoundedCornerShape(cornerRadius_12),
                placeholder = { Text(text = stringResource(R.string.search), color = colorWhite) },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                    onActiveChange(it)
                },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent,
                    inputFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = colorWhite,
                        unfocusedTextColor = colorWhite
                    )
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            active = false
                            onSearchClick()
                        },
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        tint = colorGray
                    )
                }
            ) {
                LazyColumn {
                    items(count = cityList.size) {
                        CityNameItem(
                            cityData = cityList[it],
                            modifier.clickable {
                                active = false
                                onCityClick(cityList[it])
                            })
                    }
                }
            }
        }
//            CustomTextField(
//                Modifier.fillMaxSize(),
//                text = searchText,
//                placeholderText = stringResource(R.string.search),
//                onValueChange = onValueChange,
//                backgroundColor = Color.Transparent,
//                textColor = colorWhite,
//                indicatorColor = Color.White,
//                trailingIcon = {
//
//                    Icon(
//                        modifier = Modifier.clickable {
//                            onSearchClick()
//                        },
//                        painter = painterResource(id = R.drawable.search),
//                        contentDescription = "",
//                        tint = colorGray
//                    )
//                }, placeHolderColor = placeHolderColor
//            )


        Box(
            modifier = Modifier
                .padding(start = padding_16, top = padding_2)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(dailyItemCornerColor, backgroundColor)
                    ),
                    shape = RoundedCornerShape(cornerRadius_12)
                )
                .size(cardSize_52)

        ) {
            Box(
                Modifier
                    .size(cardSize_50)
                    .align(Alignment.BottomEnd)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(dailyItemSecondColor, backgroundColor)
                        ), shape = RoundedCornerShape(cornerRadius_12)
                    )
                    .clickable {
                        onLocationClick()
                    }
            )
            Image(
                modifier = Modifier
                    .size(imageSize_24)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.location),
                contentDescription = ""
            )
        }
    }
}
//}