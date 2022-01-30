package com.amicum.report_presentation.home.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.amicum.auth_domain.model.AuthUser
import com.amicum.report_presentation.R
import com.amicum.core_ui.*
import com.amicum.core_ui.utils.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Боковое навигационное меню
@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    authUser: AuthUser,
    onExit: () -> Unit = {}
) {
    val items = listDrawItem()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Header(authUser)

            Text(
                text = """АО "Воркута Уголь" / ш. Комсомольская / участок АИСиРЭО """,
                modifier = Modifier.padding(mediumPadding),
                style = MaterialTheme.typography.body2
            )

            // Список элементов навигации
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                if (item.isLine) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = normalPadding, horizontal = mediumPadding),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    )
                } else {
                    DrawerItem(
                        item = item,
                        selected = currentRoute == item.route,
                        onItemClick = itemClick(navController, item, scope, scaffoldState)
                    )
                }
            }
        }

        ExitItem(scope, scaffoldState, onExit)

    }
}

@Composable
private fun listDrawItem() = listOf(
    NavDrawerItem(
        route = Route.HOME,
        icon = R.drawable.ic_home,
        title = stringResource(R.string.sOnHomeMenu)
    ),

    NavDrawerItem(
        route = Route.REPORT,
        icon = R.drawable.ic_report,
        title = stringResource(R.string.sFillReportMenu)
    ),

    NavDrawerItem(
        route = Route.MATERIALS,
        icon = R.drawable.ic_materials,
        title = stringResource(R.string.sMaterialsMenu)
    ),

    NavDrawerItem(
        route = Route.DOWN_TIME,
        icon = R.drawable.ic_time,
        title = stringResource(R.string.sDowntimeMenu)
    ),

    NavDrawerItem(isLine = true),

    NavDrawerItem(
        route = Route.NOTIFICATION,
        icon = R.drawable.ic_notification,
        title = stringResource(R.string.sNotificationsMenu)
    ),

    NavDrawerItem(
        route = Route.SETTINGS,
        icon = R.drawable.ic_setting,
        title = stringResource(R.string.sSetingsMenu)
    )
)

@Composable
private fun itemClick(
    navController: NavController,
    item: NavDrawerItem,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
): (NavDrawerItem) -> Unit = {
    navController.navigate(item.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = false
            }
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = false
    }
    // Close drawer
    scope.launch {
        scaffoldState.drawerState.close()
    }
}

@Composable
private fun Header(authUser: AuthUser) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(mediumPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(4f)) {
            Text(
                text = "${authUser.number} | ${authUser.fio}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
            )
            Text(
                text = authUser.post,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                overflow = TextOverflow.Ellipsis,
            )
        }

        authUser.avatar?.let {
            Image(
                bitmap = it,
                contentDescription = authUser.fio,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = smallPadding)
                    .clip(CircleShape),
            )
        }
    }
}

@Composable
fun DrawerItem(
    item: NavDrawerItem,
    selected: Boolean,
    onItemClick: (NavDrawerItem) -> Unit
) {
    val background = if (selected) MaterialTheme.colors.secondary else Color.Transparent
    val color = if (selected) MaterialTheme.colors.onSecondary
    else MaterialTheme.colors.onBackground.copy(alpha = 0.7f)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .clickable(onClick = { onItemClick(item) })
            .background(background)
            .padding(horizontal = mediumPadding)

    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.h6,
            color = color,
//            modifier = Modifier.weight(8f),
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = item.icon),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = smallPadding),
            tint = color,
        )
    }
}

@Composable
private fun BoxScope.ExitItem(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onExit: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .padding(bottom = bigPadding)
            .height(itemHeight)
            .clickable(onClick = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                onExit()
            })
            .padding(horizontal = mediumPadding)

    ) {
        Text(
            text = stringResource(R.string.sLogout),
            style = MaterialTheme.typography.h6,
            color = Carrot,
            modifier = Modifier.weight(8f),
        )

        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = stringResource(R.string.sLogout),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            tint = Carrot,
        )
    }
}

private val itemHeight = 60.dp