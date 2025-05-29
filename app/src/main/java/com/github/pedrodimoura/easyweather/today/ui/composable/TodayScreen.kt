package com.github.pedrodimoura.easyweather.today.ui.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedrodimoura.easyweather.today.ui.model.TodayWeatherInformation
import com.github.pedrodimoura.easyweather.today.vm.TodayWeatherUiState
import com.github.pedrodimoura.ui.theme.EasyWeatherTheme
import com.github.pedrodimoura.ui.theme.EasyWeatherUI

@Composable
internal fun TodayScreen(
    uiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {

    val backgroundColor = remember {
        mutableStateOf(EasyWeatherUI.Colors.Temperature.Cool)
    }

    val animateBackgroundColor by animateColorAsState(
        targetValue = backgroundColor.value,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing,
        ),
        label = "animateBackgroundColor",
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(animateBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is TodayWeatherUiState.Loading -> composeTodayWeatherLoadingState()
            is TodayWeatherUiState.Success -> composeTodayWeatherSuccessState(
                todayWeatherInformation = uiState.todayWeatherInformation,
                backgroundColor = backgroundColor,
            )
        }
    }
}

internal fun LazyListScope.composeTodayWeatherLoadingState() {
    item(key = "loading") {
        CircularProgressIndicator(
            color = EasyWeatherUI.Colors.White,
        )
    }
}

internal fun LazyListScope.composeTodayWeatherSuccessState(
    todayWeatherInformation: TodayWeatherInformation,
    backgroundColor: MutableState<Color>,
) {
    backgroundColor.value = todayWeatherInformation.getTemperatureColor()
    item(key = "todayWeatherInformation") {
        Column {
            Text(
                text = todayWeatherInformation.dateTime,
                style = EasyWeatherUI.Typography.defaults.bodySmall,
                color = todayWeatherInformation.getFontColor(),
            )
            Text(
                text = todayWeatherInformation.temperature,
                style = EasyWeatherUI.Typography.defaults.displayLarge,
                color = todayWeatherInformation.getFontColor(),
            )
            Text(
                text = todayWeatherInformation.location,
                style = EasyWeatherUI.Typography.defaults.labelSmall,
                color = todayWeatherInformation.getFontColor(),
            )
        }
    }
}

internal fun TodayWeatherInformation.getTemperatureColor(): Color {
    return when {
        rawTemperature < -10 -> EasyWeatherUI.Colors.Temperature.Colder
        rawTemperature < 0 -> EasyWeatherUI.Colors.Temperature.Cold
        rawTemperature < 20 -> EasyWeatherUI.Colors.Temperature.Cool
        rawTemperature < 35 -> EasyWeatherUI.Colors.Temperature.Warm
        rawTemperature < 40 -> EasyWeatherUI.Colors.Temperature.Hot
        else -> EasyWeatherUI.Colors.Temperature.Hotter
    }
}

internal fun TodayWeatherInformation.getFontColor(): Color {
    return when {
        rawTemperature < 30 -> EasyWeatherUI.Colors.White
        rawTemperature < 40 -> EasyWeatherUI.Colors.Black
        else -> EasyWeatherUI.Colors.White
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenLoadingPreview() {
    EasyWeatherTheme {
        TodayScreen(TodayWeatherUiState.Loading)
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenColderPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "-15°C",
                    rawTemperature = -15.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenColdPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "-5°C",
                    rawTemperature = -5.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenCoolPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "15°C",
                    rawTemperature = 15.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenWarmPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "30°C",
                    rawTemperature = 30.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenHotPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "37°C",
                    rawTemperature = 37.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayScreenHotterPreview() {
    EasyWeatherTheme {
        TodayScreen(
            TodayWeatherUiState.Success(
                todayWeatherInformation = TodayWeatherInformation(
                    temperature = "40°C",
                    rawTemperature = 40.0,
                    dateTime = "Friday, 20 January",
                    location = "Recife, PE - Brazil",
                )
            )
        )
    }
}
