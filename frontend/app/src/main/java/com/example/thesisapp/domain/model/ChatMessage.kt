package com.example.thesisapp.domain.model

data class ChatMessage(
    val id: Long = 1L,
    val senderId: String,
    val receiverId: String,
    val message: String
)
