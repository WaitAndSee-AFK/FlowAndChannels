package org.example.lesson4.dictionary

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

//private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
//private val scope = CoroutineScope(dispatcher)

object Repository {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val API_KEY = "+jpzlK35DINTppE37wzR4Q==gbRaJ1erPXSjy0Cr"
    private const val HEADER_KEY = "X-Api-Key"

    private val json = Json{ ignoreUnknownKeys = true }

    suspend fun loadDefinition(word: String): List<String> {
        return withContext(Dispatchers.IO) {
            var connection: HttpURLConnection? = null
            try {
                val urlString = BASE_URL + word
                val url = URL(urlString)
                connection = (url.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(HEADER_KEY, API_KEY)
                }
                val response = connection.inputStream.bufferedReader().readText()
                json.decodeFromString<Definition>(response).mapDefinitionToList()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun Definition.mapDefinitionToList(): List<String> {
        return this.definition.split(Regex("\\d. ")).map { it.trim() }.filter { it.isNotEmpty() }
    }
}


