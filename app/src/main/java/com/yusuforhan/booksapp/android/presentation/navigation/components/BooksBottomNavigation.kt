package com.yusuforhan.booksapp.android.presentation.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.presentation.theme.Light
import com.yusuforhan.booksapp.android.presentation.theme.Light2
import com.yusuforhan.booksapp.android.presentation.theme.Primary
import com.yusuforhan.booksapp.android.presentation.theme.Secondary

@Composable
fun BooksBottomNav(
    items: List<BottomNavigationItem>, selected: Int, onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)).size(75.dp), tonalElevation = 10.dp,
        containerColor = Secondary,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                             painter = painterResource(id = item.icon), contentDescription = null
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                     }
                },
                label = { Text(text = item.text) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = Primary,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Light2,
                    unselectedTextColor = Light2
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: Int , val text: String
)