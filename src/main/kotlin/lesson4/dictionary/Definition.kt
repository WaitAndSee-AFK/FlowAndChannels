package org.example.lesson4.dictionary

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Definition(

    @SerialName("definition") val definition: String

)