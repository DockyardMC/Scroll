package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.buildJsonObject

object JsonToComponentSerializer {
    fun serialize(json: String): Component {
        return Json.decodeFromString(ComponentDeserializer(), json)
    }
}

internal class ComponentDeserializer : JsonTransformingSerializer<Component>(Component.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement = when (element) {
        is JsonPrimitive -> buildJsonObject {
            put("text", element)
        }

        else -> element
    }
}