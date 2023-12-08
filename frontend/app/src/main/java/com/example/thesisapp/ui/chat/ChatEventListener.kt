package com.example.thesisapp.ui.chat

import com.example.thesisapp.BuildConfig
import com.example.thesisapp.domain.model.ChatMessage
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class ChatEventListener: EventListener<QuerySnapshot> {

    private val messages: MutableCollection<ChatMessage> = mutableListOf()

    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
        if (error != null) {
            return
        }
        if (value != null) {
            for (documentChange: DocumentChange in value.documentChanges) {
                messages.add(
                    ChatMessage(
                        senderId =
                        documentChange.document.getString(BuildConfig.KEY_SENDER_ID) ?: "",
                        receiverId =
                        documentChange.document.getString(BuildConfig.KEY_RECEIVER_ID) ?: "",
                        message =
                        documentChange.document.getString(BuildConfig.KEY_MESSAGE) ?: ""
                    )
                )
            }
        }
    }
}