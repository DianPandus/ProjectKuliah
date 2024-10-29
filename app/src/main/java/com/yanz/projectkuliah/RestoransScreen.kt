package com.yanz.projectkuliah

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.yanz.projectkuliah.ui.theme.projectkuliah
import com.yanz.projectkuliahpackage.RestaurantsViewModel

@Composable
fun RestaurantsScreen(onItemClick: (id: Int) -> Unit) {
    val viewModel: RestaurantsViewModel = viewModel()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Display name and NIM at the top
        Text(
            text = "Dian Pandu Syahfitra",  // Replace with actual name
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "225150200111032",  // Replace with actual NIM
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyColumn for displaying restaurant items
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 8.dp
            )
        ) {
            items(viewModel.state.value) { restaurant ->
                RestaurantItem(
                    item = restaurant,
                    onFavoriteClick = { id ->
                        viewModel.toggleFavorite(id, restaurant.isFavorite) // Pass `oldValue` here
                    },
                    onItemClick = { id -> onItemClick(id) }
                )
            }
        }
    }
}



@Composable
fun RestaurantItem(item: Restaurant,
                   onFavoriteClick: (id: Int) -> Unit,
                   onItemClick: (id: Int) -> Unit) {
    val icon = if (item.isFavorite)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            RestaurantDetails(item.title, item.description, Modifier.weight(0.7f))
            RestaurantIcon(icon, Modifier.weight(0.15f)) {
                onFavoriteClick(item.id)
            }
        }
    }
}

@Composable
fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = { }) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() })
}

@Composable
fun RestaurantDetails(title: String,
                      description: String,
                      modifier: Modifier,
                      horizontalAlignment: Alignment.Horizontal = Alignment.Start) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    projectkuliah {
        RestaurantsScreen({})
    }
}
