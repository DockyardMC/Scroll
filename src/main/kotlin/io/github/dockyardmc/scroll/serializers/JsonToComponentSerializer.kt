package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import kotlinx.serialization.json.Json

object JsonToComponentSerializer {
    fun serialize(json: String): Component {
        return Json.decodeFromString<Component>(json)
    }
}