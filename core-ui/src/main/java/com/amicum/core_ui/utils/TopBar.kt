package com.amicum.core_ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(header: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                tint = MaterialTheme.colors.onPrimary
            )
        }

        Text(
            text = header,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TopBarOther(
    header: String,
    image: ImageVector = Icons.Default.ArrowBack,
    imageOther: ImageVector,
    onBack: () -> Unit,
    onOther: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBack,
        ) {
            Icon(
                imageVector = image, contentDescription = "Back",
                tint = MaterialTheme.colors.onPrimary
            )
        }

        Text(
            text = header,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary
        )

        IconButton(
            onClick = onOther,
        ) {
            Icon(
                imageVector = imageOther, contentDescription = "Other",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}
