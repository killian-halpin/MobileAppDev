package com.example.wishlist.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wishlist.R
import com.example.wishlist.data.CardData

@Composable

fun HomeScreen(navController: NavController) {

    val cardViewModel: CardModel = viewModel(factory =
    CardModel.Factory)


    // Column to stack the FABs vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Align FABs to the bottom
        horizontalAlignment = Alignment.End // Align FABs to the end

    ) {
        ExtendedFloatingActionButton(
            containerColor = Color(0xFF639FD8),
            onClick = {
                if (cardViewModel.cards.value.isNotEmpty()) {
                    navController.navigate("AllItemsScreen")
                }
            },
            text = { Text("View Items") },
            icon = { Icon(Icons.Default.Favorite, contentDescription =
            stringResource(R.string.app_name)) },
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp) // Add spacing between buttons

        )

        ExtendedFloatingActionButton(
            containerColor = Color(0xFF639FD8),
            onClick = { navController.navigate("addWishListItem") },
            text = { Text("Add Item") },
            icon = { Icon(Icons.Default.Create, contentDescription =
            stringResource(R.string.app_name)) },
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(card: CardData) {
    var imageUri = card.imagePath
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF639FD8)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            }
        }
    }




