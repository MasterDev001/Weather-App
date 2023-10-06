package com.example.weatherapp.app.screens.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.TextUnit
import com.example.weatherapp.app.screens.padding_16

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    shape: Shape = MaterialTheme.shapes.small,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "",
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    indicatorColor:Color=MaterialTheme.colorScheme.primary,
    placeHolderColor:Color=MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
) {

    BasicTextField(modifier = modifier
        .background(backgroundColor, shape = shape)
        .fillMaxWidth()
        ,
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(indicatorColor),
        textStyle = LocalTextStyle.current.copy(
            color = textColor,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier.padding(horizontal = padding_16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = placeHolderColor,
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}