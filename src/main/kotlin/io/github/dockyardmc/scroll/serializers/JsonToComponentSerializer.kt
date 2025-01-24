package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

object JsonToComponentSerializer {

    fun serialize(json: String): Component {
        val serializer = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return Json.decodeFromJsonElement(serializer.parseToJsonElement(json))
    }
}