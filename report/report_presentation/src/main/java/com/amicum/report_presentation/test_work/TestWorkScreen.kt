package com.amicum.report_presentation.test_work

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amicum.core_ui.utils.TopBarOther
import com.amicum.report_presentation.R
import com.amicum.report_presentation.test_work.TestWorkViewModel

@ExperimentalComposeUiApi
@Composable
fun TestWorkScreen(onNavigateUp: () -> Unit = {}) {

    val viewModel: TestWorkViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopBarOther(header = stringResource(R.string.sComment),
                imageOther = Icons.Default.Done,
                onBack = onNavigateUp,
                onOther = {
                    keyboardController?.hide()
                }
            )
        },
    ) {
        val configuration = LocalConfiguration.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = configuration.screenHeightDp.dp / 3)

        ) {
            val edMod = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .border(1.dp, MaterialTheme.colors.onBackground, shape = RoundedCornerShape(5.dp))

            TextField(
                value = viewModel.comment.value,
                onValueChange = {
                    viewModel.comment.value = it
                },
                modifier = edMod,
                singleLine = false,
                placeholder = { Text("Введите комментарий") },
            )
        }
    }
}