package common.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.jokka_app.Screen

@Composable
fun BottomBar(
    currentScreen: String,
    navController: NavController,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        val items = listOf(
            BottomBarItem("Home", Icons.Default.Home, Screen.Home.route),
            BottomBarItem("Destination", Icons.Default.Place, "destination"),
            BottomBarItem("Food", Icons.Default.Menu, "food"),
            BottomBarItem("Profile", Icons.Default.Person, Screen.Profile.route)
        )

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                selected = currentScreen == item.route,
                onClick = {
                    // Navigate to the selected screen
                    if (navController.currentDestination?.route != item.route) {
                        navController.navigate(item.route) {
                            // Avoid multiple copies of the same destination in the back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    onItemSelected(item.route)
                },
                label = { Text(item.name) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Red,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Red,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

data class BottomBarItem(val name: String, val icon: ImageVector, val route: String)