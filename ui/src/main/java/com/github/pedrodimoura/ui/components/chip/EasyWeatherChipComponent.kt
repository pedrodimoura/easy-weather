package com.github.pedrodimoura.ui.components.chip

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pedrodimoura.ui.theme.EasyWeatherTheme
import com.github.pedrodimoura.ui.theme.EasyWeatherUI

@Composable
fun EasyWeatherChipComponent(
    label: String,
    modifier: Modifier = Modifier,
) {

    Text(
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 24.dp),
            )
            .padding(
                start = 12.dp,
                end = 12.dp,
                top = 8.dp,
                bottom = 8.dp,
            ),
        text = label,
        style = EasyWeatherUI.Typography.defaults.bodySmall,
    )
}

@Preview(showBackground = true)
@Composable
fun EasyWeatherChipComponentPreview() {
    EasyWeatherTheme {
        EasyWeatherChipComponent(label = "Friday, 20 January")
    }
}
