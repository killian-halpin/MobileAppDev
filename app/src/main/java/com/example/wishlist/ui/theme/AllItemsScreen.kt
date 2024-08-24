package com.example.wishlist.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wishlist.R



@SuppressLint("SuspiciousIndentation")
@Composable
fun AllItemsScreen(navController: NavController) {
    val cardModel: CardModel = viewModel(factory = CardModel.Factory)

    var cardId by remember { mutableIntStateOf(1) }
    var card = cardModel.getCardById(cardId)

    val cardViewModel: CardModel = viewModel(factory = CardModel.Factory)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f)) // Push content to bottom

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingActionButton(
                containerColor = Color(0xFF639FD8),
            onClick = {
                if (cardId > cardModel.cards.value.size) { cardId-- }
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.app_name)
            )
        }
            // Delete Card FAB
            FloatingActionButton(
                containerColor = Color(0xFF639FD8),
                onClick = {
                    if (card != null) {
                        cardViewModel.repository.deleteCardById(card.id)
                    }
                    navController.navigate("homescreen") {
                        popUpTo("homescreen") { inclusive = true }
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.app_name)
                )
            }

            // Next Card FAB
            FloatingActionButton(
                containerColor = Color(0xFF639FD8),
                onClick = {
                    if (cardId < cardModel.cards.value.size) { cardId++ }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(R.string.app_name)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Go Back FAB
        ExtendedFloatingActionButton(
            onClick = { navController.navigate("homescreen") },
            text = { Text("Go Back") },
            icon = { Icon(Icons.Default.Home, contentDescription =
            stringResource(R.string.app_name)) },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            containerColor = Color.LightGray,
        )
    }

    if (card != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            CardItem(card = card)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = card.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = card.description,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

