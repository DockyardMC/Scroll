package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

object ComponentToJsonSerializer {
    fun serialize(component: Component): String {

        component.getAllComponents().forEach { childComponent ->
            val new = component.shadowColor?.map { it / 255.0 }
            childComponent.shadowColor = new
        }


        return Json.encodeToJsonElement<Component>(component).toString()
    }
}