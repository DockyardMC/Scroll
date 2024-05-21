package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

object JsonToComponentSerializer {

    fun serialize(json: String): Component {
        return Json.decodeFromJsonElement(Json.parseToJsonElement(json))
    }
}