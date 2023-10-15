package com.example.weatherapp.app.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.fabBackground
import com.example.weatherapp.ui.theme.iconColor

@Composable
fun FabButton(modifier: Modifier,onClick: () -> Unit) {


    FloatingActionButton(
        modifier =modifier,
        onClick = onClick,
        shape = CircleShape,
//        modifier = Modifier
//            .shadow(
//                elevation = 24.dp,
//                shape = CircleShape,
//                spotColor = Color.Red, ambientColor = Color(0xff343B49) // Specify the desired elevation color
//            ),
        contentColor = iconColor,
        containerColor = fabBackground,
//        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 24.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "add")
    }
}