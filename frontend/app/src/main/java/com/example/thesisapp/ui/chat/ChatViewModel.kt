package com.example.thesisapp.ui.chat

import androidx.lifecycle.ViewModel
import com.example.thesisapp.BuildConfig
import com.example.thesisapp.config.ConfigurationProvider
import com.example.thesisapp.domain.model.ChatMessage
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val configurationProvider: ConfigurationProvider
) : ViewModel(), EventListener<QuerySnapshot> {

    private val _stateStream: MutableStateFlow<ChatViewState> = MutableStateFlow(
        ChatViewState(listOf(), configurationProvider.username ?: "")
    )
    val stateStream = _stateStream.asStateFlow()

    private var state: ChatViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    private var database: FirebaseFirestore

    init {
        database = FirebaseFirestore.getInstance()
        listenMessages()
    }

    private fun listenMessages() {
        database.collection(BuildConfig.KEY_COLLECTION_CHAT)
            .whereEqualTo(BuildConfig.KEY_SENDER_ID, "sender1")
            .whereEqualTo(BuildConfig.KEY_RECEIVER_ID, "receiver2")
            .addSnapshotListener(this)
        database.collection(BuildConfig.KEY_COLLECTION_CHAT)
            .whereEqualTo(BuildConfig.KEY_SENDER_ID, "sender2")
            .whereEqualTo(BuildConfig.KEY_RECEIVER_ID, "receiver1")
            .addSnapshotListener(this)
    }

    fun sendMessage(message: String) {
        val senderId = if (configurationProvider.username == "mike") {
            "sender1"
        } else {
            "sender2"
        }

        val receiverId = if (configurationProvider.username == "mike") {
            "receiver2"
        } else {
            "receiver1"
        }

        val chatMessage = HashMap<String, Any>()
        chatMessage[BuildConfig.KEY_SENDER_ID] = senderId
        chatMessage[BuildConfig.KEY_RECEIVER_ID] = receiverId
        chatMessage[BuildConfig.KEY_MESSAGE] = message

        database.collection(BuildConfig.KEY_COLLECTION_CHAT).add(chatMessage)
    }

    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
        if (error != null) {
            return
        }
        if (value != null) {
            for (documentChange: DocumentChange in value.documentChanges) {
                val msgs = state.messages.toMutableList()
                msgs.add(
                    ChatMessage(
                        senderId =
                        documentChange.document.getString(BuildConfig.KEY_SENDER_ID) ?: "",
                        receiverId =
                        documentChange.document.getString(BuildConfig.KEY_RECEIVER_ID) ?: "",
                        message =
                        documentChange.document.getString(BuildConfig.KEY_MESSAGE) ?: ""
                    )
                )

                state = state.copy(messages = msgs.toList())
            }
        }
    }
}