package com.example.wishlist.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wishlist.data.CardData
import com.example.wishlist.data.CardRepo
import com.example.wishlist.wishlist

class CardModel(val repository: CardRepo) : ViewModel() {
    val cards = mutableStateOf<List<CardData>>(emptyList())

    init {
        refreshCards()
    }

    fun addCard(newCard: CardData) {
        repository.addCard(newCard)
        refreshCards()
    }
    fun getCardById(id: Int): CardData?{
        return repository.getCardById(id)
    }
    private fun refreshCards() {
        cards.value = repository.getCards()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as wishlist)
                val cardRepository = application.container.cardRepo
                CardModel(repository = cardRepository)
            }
        }
    }
}
