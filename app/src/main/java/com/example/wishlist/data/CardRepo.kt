package com.example.wishlist.data

interface CardRepo {
    fun getCards(): List<CardData>
    fun addCard(card: CardData)
    fun updateCard(card: CardData)
    fun deleteCard(card: CardData)

    fun getCardById(id: Int):CardData?
    fun deleteCardById(id: Int)

    fun getListSize():Int
}