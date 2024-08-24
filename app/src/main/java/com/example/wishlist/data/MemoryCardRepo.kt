package com.example.wishlist.data

class MemoryCardRepo : CardRepo {
    private var cardList = mutableListOf<CardData>()

    override fun getCards(): List<CardData> = cardList.toList()

    override fun addCard(card: CardData) {
        cardList.add(card)
    }

    override fun updateCard(card: CardData) {
        val index = cardList.indexOfFirst { it.id == card.id }
        if (index != -1) {
            cardList[index] = card
        }
    }

    override fun deleteCard(card: CardData) {
        cardList.removeIf { it.id == card.id }

    }

    override fun getCardById(id: Int): CardData? {
        return cardList.find { it.id == id }
    }

    override fun deleteCardById(id: Int) {
        val card = getCardById(id)
        if (card != null) {
            deleteCard(card)
        }
    }


    override fun getListSize(): Int {
        return cardList.toList().size
    }

}