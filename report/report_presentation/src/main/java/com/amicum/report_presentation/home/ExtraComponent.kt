package com.amicum.report_presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.amicum.core_ui.GrayDA
import com.amicum.core_ui.GreenYellow
import com.amicum.core_ui.normalPadding
import com.amicum.core_ui.smallPadding
import com.amicum.report_presentation.R
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.sin

/**
 * Компоненты главного экрана
 */

/**
 * Контейнер заданного цвета с вертикальной линией посередине.
 * Ширина равна ширине родителя, а высота определяется содержимым
 */
@Composable
fun BoxScope.ContainerVerticalLine(
    elevation: Dp = normalPadding,
    color: Color = MaterialTheme.colors.secondaryVariant, // Цвет заполнения
    lineColor: Color = Color.White, // Цвет вертикальной линии
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    Surface(
        elevation = elevation,
        modifier = Modifier.align(Alignment.TopCenter)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .drawBehind {
                    val dh = normalPadding.toPx()
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width / 2, dh),
                        end = Offset(size.width / 2, size.height - dh),
                        strokeWidth = 3f
                    )
                },
            content = content
        )
    }
}

/**
 * Компонент кнопки c верхним и центральным текстом и изображением "стрелка вниз"
 */
@Composable
fun BoxWithConstraintsScope.ButtonWithDownImage(
    topText: String,
    centerText: String,
    color: Color = Color.White,
    alignment: Alignment = Alignment.CenterStart, // Левое / правое расположение
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .align(alignment)
            .width(maxWidth / 2)
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(smallPadding)
        ) {
            Text(
                text = topText,
                style = MaterialTheme.typography.body1,
                color = color,
                modifier = Modifier.padding(top = smallPadding)
            )

            Text(
                text = centerText,
                style = MaterialTheme.typography.h6,
                color = color,
                modifier = Modifier.padding(smallPadding)
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_down),
                contentDescription = null,
                tint = color,
                modifier = Modifier.padding(smallPadding)
            )
        }
    }
}

/**
 * Компонент с верхним изображением и нижней кнопкой
 */
@Composable
fun ButtonWithTopImage(
    textButton: String,
    image: ImageVector = ImageVector.vectorResource(R.drawable.ic_down),
    width: Dp = 135.dp, // Ширина элемента
    buttonColor: Color = MaterialTheme.colors.secondaryVariant,
    iconColor: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(width)
    ) {
        Icon(
            imageVector = image,
            contentDescription = textButton,
            tint = iconColor,
            modifier = Modifier.padding(bottom = normalPadding)
        )

        val textList = textButton.split(" ")
        Button(
            onClick = onClick,
            modifier = Modifier.width(width),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                textList.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }
}

/**
 * Информационный компонент с двумя типами текстов
 */
@Composable
fun BoxWithConstraintsScope.TwoTextStyleInfo(
    topText: String,
    centerText: String,
    bottomTextColor: Color = MaterialTheme.colors.onBackground,
    centerTextColor: Color = MaterialTheme.colors.onSurface,
    isLeft: Boolean = true, // Левое / правое расположение
) {
    val textList = centerText.split(" ")

    Box(
        modifier = Modifier
            .align(if (isLeft) Alignment.CenterStart else Alignment.CenterEnd)
            .width(maxWidth / 2)
    ) {
        Column(
//            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(smallPadding)
        ) {
            Text(
                text = topText,
                style = MaterialTheme.typography.h4,
                color = bottomTextColor,
                modifier = Modifier.padding(top = smallPadding)
            )

            textList.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = centerTextColor,
                )
            }
        }
    }
}

/**
 * Центральная кнопка "Заполнить отчет" с круговым индикатором прогресса
 */
@Composable
fun BoxScope.CenterButtonWithProgress(
    percent: State<Float>,
    centerText: String = stringResource(R.string.sFillReport), // Можно переиспользовать с любым текстом
    imageVector: ImageVector = ImageVector.vectorResource(R.drawable.ic_report), // и изображением
    sizeCanvas: Dp, // Размер элемента
    onClick: () -> Unit
) {
    val surfaceColor = MaterialTheme.colors.surface
    val isDarkTheme = isSystemInDarkTheme()

    Box(modifier = Modifier
        .align(Alignment.Center)
        .size(sizeCanvas)
        .clip(CircleShape)
        .clickable { onClick() }
        .padding(smallPadding)
        .drawBehind {
            circularProgress(
                percent = percent.value,
                background = surfaceColor,
                circularColor = GrayDA,
                antiProgressColor = if (isDarkTheme) surfaceColor else GrayDA
            )
        }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)

        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = centerText,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(sizeCanvas / 2.8f)
            )

            val textList = centerText.split(" ")
            textList.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

// Переводит радианы в градусы
fun Float.toGrad() = this * 180f / PI.toFloat()

/**
 * Рисует круговой индикатор прогресса в области рисования
 */
private fun DrawScope.circularProgress(
    percent: Float, // % заполнения
    background: Color, // Задний фон
    circularColor: Color, // Цвет кругов вокруг индикатора
    progressColor: Color = GreenYellow, // Цвет дуги прогресса
    antiProgressColor: Color // Цвет противоположной дуги прогресса
) {

    val checkPercent = when {
        percent < 0f -> 0f
        percent > 100f -> 100f
        else -> percent
    }

    val pi = PI.toFloat()

    val interval = -(1 - checkPercent / 100f) * 2f + 1f // percent --> [-1, 1]
    val grad = asin(interval).toGrad() + 90f // 0..180

    val a1 = 90f - grad
    val a2 = a1 + grad * 2

    val dx = 4f // расстояние между окружностями и индикатором

    // применяется для горизонтальной разделяющей линии
    val dr = dx - dx * sin(grad * pi / 180f) / 1.3f // минус радиальная погрешность
    val width = size.width

    drawArc(
        color = antiProgressColor,
        startAngle = a2 + dr,
        sweepAngle = 360f - grad * 2 - dr,
        useCenter = false,
        topLeft = Offset(0f, 0f),
        size = Size(width, width)
    )

    // Зеленая дуга - сам элемент заполнения в %
    drawArc(
        color = progressColor,
        startAngle = a1 + dr,
        sweepAngle = grad * 2 - dr,
        useCenter = false,
        topLeft = Offset(0f, 0f),
        size = Size(width, width)
    )

    // Центральная область заполнения
    val dxR = dx * 2
    val fillR = width / 20f // Ширина индикатора прогресса
    val centerRadius = width / 2 - fillR
    drawCircle(
        brush = Brush.linearGradient(
            colors = listOf(background, background)
        ),
        radius = centerRadius,
        center = center,
    )

    // Внутренняя окружность
    drawCircle(
        brush = Brush.linearGradient(colors = listOf(circularColor, circularColor)),
        radius = width / 2 - fillR - dxR,
        center = center,
        style = Stroke(width = 4f)
    )

    // Внешняя окружность
    drawCircle(
        brush = Brush.linearGradient(colors = listOf(circularColor, circularColor)),
        radius = width / 2 + dxR,
        center = center,
        style = Stroke(width = 4f)
    )
}