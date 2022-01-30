package com.amicum.report_presentation.time

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amicum.core_ui.utils.TopBar
import com.amicum.report_presentation.R

@Composable
fun TimeScreen(onNavigateUp: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopBar(
                header = stringResource(R.string.sReport),
                onBack = onNavigateUp
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.sDowntimeMenu),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )

            }
        }
    }
}