package com.example.wishlist

import com.example.wishlist.data.CardRepo
import com.example.wishlist.data.MemoryCardRepo


interface AppContainer {
    val cardRepo: CardRepo
}

class DefaultAppContainer: AppContainer {
    override val cardRepo: CardRepo by lazy {
        MemoryCardRepo()
    }
}