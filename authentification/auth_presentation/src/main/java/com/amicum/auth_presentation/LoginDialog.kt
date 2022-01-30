package com.amicum.auth_presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.amicum.core_ui.*
import kotlin.system.exitProcess

@ExperimentalComposeUiApi
@Composable
fun LoginDialog() {
    val viewModel: LoginViewModel = hiltViewModel()
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        )
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = mediumPadding, vertical = bigPadding)
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(mediumPadding)
            ) {
                Header()
                WelcomeInSystem()
                LoginBox(viewModel)
                Spacer(modifier = Modifier.height(100.dp))
                EnterButton { viewModel.login() }
            }
        }
    }
}

@Composable
private fun Header(
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(80.dp).clickable {  }
        )

        val activity = (LocalContext.current as? Activity)

        TextButton(onClick = {
            // Завершение работы приложения:
            activity?.finish()
            exitProcess(0)
        }) {
            Text(
                text = stringResource(R.string.sExit),
                style = MaterialTheme.typography.h6,
                color = Carrot,
            )
        }
    }
}

@Composable
fun WelcomeInSystem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.sEnterInSystem),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = mediumPadding)
        )

        Text(
            text = stringResource(R.string.sWelcome),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
        )

        Text(
            text = stringResource(R.string.sLoginToContinue),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
private fun LoginBox(
    viewModel: LoginViewModel,
) {
    val login = viewModel.login
    val password = viewModel.password
    val passwordVisibility = viewModel.passwordVisibility
    val authorizationOnActiveDirectory = viewModel.authorizationOnActiveDirectory

    Column(modifier = Modifier.fillMaxWidth()) {

        val focusRequester = FocusRequester()

        val editMod = Modifier
            .fillMaxWidth()
            .padding(vertical = normalPadding)
            .clip(MaterialTheme.shapes.small)
            .focusRequester(focusRequester = focusRequester)

        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = login.value,
            onValueChange = { login.value = it },
            modifier = editMod,
            maxLines = 1,
            singleLine = true,
            placeholder = { Text(stringResource(R.string.sLogin)) },
            colors = textFieldColors(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.sLogin),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
        )

        OutlinedTextField(
            modifier = editMod,
            maxLines = 1,
            singleLine = true,
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text(stringResource(R.string.sPassword)) },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            colors = textFieldColors(),
            trailingIcon = {
                val image = if (passwordVisibility.value)
                    Icons.Filled.VisibilityOff
                else Icons.Filled.Visibility

                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(imageVector = image, stringResource(R.string.sPassword))
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                viewModel.login()
            }),
        )

        viewModel.isLoginError.value?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = normalPadding)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = normalPadding)
                .offset(x = -normalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = authorizationOnActiveDirectory.value,
                modifier = Modifier.padding(start = 0.dp),
                onCheckedChange = { authorizationOnActiveDirectory.value = it }
            )
            Text(
                text = stringResource(R.string.sAuthorizationOnActiveDirectory),
            )
        }
    } // Column
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colors.onBackground,
    backgroundColor = MaterialTheme.colors.surface,
    cursorColor = MaterialTheme.colors.secondary,
    focusedIndicatorColor = MaterialTheme.colors.secondary,
    unfocusedIndicatorColor = MaterialTheme.colors.onSurface,
    placeholderColor = MaterialTheme.colors.onSurface,
    leadingIconColor = MaterialTheme.colors.onSurface,
    trailingIconColor = MaterialTheme.colors.onSurface,
)

@Composable
private fun EnterButton(onEnter: () -> Unit) {
    Button(
        onClick = onEnter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 3.dp)
    ) {
        Text(
            text = stringResource(R.string.sEnter),
            modifier = Modifier.padding(smallPadding),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}

