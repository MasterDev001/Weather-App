package com.example.weatherapp.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.dailyItemCornerColor
import com.example.weatherapp.ui.theme.errorIconColor
import com.example.weatherapp.ui.theme.fabBackground
import com.example.weatherapp.ui.theme.iconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(message: String, onDismiss: () -> Unit) {

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius_28))
            .background(fabBackground),
        onDismissRequest = { onDismiss() },
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                modifier = Modifier
                    .padding(top = padding_10)
                    .size(imageSize_52),
                painter = painterResource(id = R.drawable.error),
                contentDescription = "",
                tint = errorIconColor
            )
            Text(
                text = stringResource(R.string.error),
                fontWeight = FontWeight.Bold,
                fontSize = fontSize_24
            )

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = padding_10),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(top = padding_8, bottom = padding_16),
                colors = ButtonDefaults.buttonColors(containerColor = dailyItemCornerColor)
            ) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}