package com.example.weatherapp.app.screens.location

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.card_Sizes_44
import com.example.weatherapp.app.screens.card_Sizes_46
import com.example.weatherapp.app.screens.cornerRadius_8
import com.example.weatherapp.app.screens.imageSize_24
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.presentation.view_models.location.LocationContract
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorGray
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.placeHolderColor
import com.example.weatherapp.ui.theme.xLargeItemCornerColor
import com.example.weatherapp.ui.theme.xLargeItemSecondColor

@Composable
fun SearchRow(
    modifier: Modifier,
    searchText: String,
    onLocationClick: () -> Unit,
    onSearchClick: () -> Unit,
    onValueChange: (String) -> Unit
) {

    Row(
        modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(card_Sizes_46)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(xLargeItemCornerColor, backgroundColor)
                    ), shape = RoundedCornerShape(cornerRadius_8)
                )
        ) {
            Box(
                Modifier
                    .padding(top = padding_3, start = padding_3)
                    .fillMaxSize()
                    .align(Alignment.BottomEnd)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(xLargeItemSecondColor, backgroundColor)
                        ), shape = RoundedCornerShape(cornerRadius_8)
                    )
            )

            CustomTextField(
                Modifier.fillMaxSize(),
                text = searchText,
                placeholderText = stringResource(R.string.search),
                onValueChange = onValueChange,
                backgroundColor = Color.Transparent,
                textColor = colorWhite,
                indicatorColor = Color.White,
                trailingIcon = {

                    Icon(
                        modifier = Modifier.clickable {
                            onSearchClick()
                        },
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        tint = colorGray
                    )
                }, placeHolderColor = placeHolderColor
            )
        }

        Box(
            modifier = Modifier
                .padding(start = padding_16)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(xLargeItemCornerColor, backgroundColor)
                    ),
                    shape = RoundedCornerShape(cornerRadius_8)
                )
                .size(card_Sizes_46)
                .clickable {
                    onLocationClick()
                }
        ) {
            Box(
                Modifier
                    .size(card_Sizes_44)
                    .align(Alignment.BottomEnd)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(xLargeItemSecondColor, backgroundColor)
                        ), shape = RoundedCornerShape(cornerRadius_8)
                    )
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