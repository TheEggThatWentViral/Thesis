package com.example.thesisapp.ui.chat

import com.example.thesisapp.domain.model.ChatMessage

data class ChatViewState(
    val messages: List<ChatMessage>,
    val currentUser: String
)
