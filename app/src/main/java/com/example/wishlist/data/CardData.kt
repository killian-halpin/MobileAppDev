package com.example.wishlist.data

data class CardData(
    val id: Int,
    val title: String,
    val imagePath: String,
    val description: String,
    val price: String
){
    init {
        require(id >= 0) {"ID must be non-negative"}
        require(title.isNotBlank()) {"Title must not be blank"}
        require(description.isNotBlank()) {"Description must not be blank"}
    }
}