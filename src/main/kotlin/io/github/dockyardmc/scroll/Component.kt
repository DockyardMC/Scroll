package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.serializers.ComponentToJsonSerializer
import io.github.dockyardmc.scroll.serializers.ComponentSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.nbt.CompoundBinaryTag

@Serializable
open class Component(
    var extra: MutableList<Component>? = null,
    var keybind: String? = null,
    var text: String? = null,
    var translate: String? = null,
    var color: String? = null,
    @SerialName("shadow_color")
    var shadowColor: List<Double>? = null,
    var bold: Boolean? = null,
    var italic: Boolean? = null,
    var underlined: Boolean? = null,
    var strikethrough: Boolean? = null,
    var obfuscated: Boolean? = null,
    var font: String? = null,
    var insertion: String? = null,
    @SerialName("hover_event")
    var hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    var clickEvent: ClickEvent? = null,
) {
    companion object {
        fun compound(components: MutableList<Component>): Component {
            return Component(
                text = "",
                extra = components
            )
        }
    }

    override fun toString(): String {
        return this.stripStyling()
    }

    fun toNBT(): CompoundBinaryTag {
        return ComponentSerializer.componentToNbt(this)
    }

    fun toJson(): String {
        return ComponentToJsonSerializer.serialize(this)
    }

    fun stripStyling(): String {
        return buildString {
            getAllComponents().forEach {
                append(it.text)
            }
        }
    }

    fun getAllComponents(): MutableList<Component> {
        val recursiveComponentList = mutableListOf<Component>()
        getComponentRecursive(this, recursiveComponentList)
        return recursiveComponentList
    }

    private fun getComponentRecursive(component: Component, componentList: MutableList<Component>) {
        component.extra?.forEach {
            componentList.add(it)
            getComponentRecursive(it, componentList)
        }
    }

    fun resetFormatting(includingFont: Boolean, ignoreShadow: Boolean = false) {
        this.color = null
        if (!ignoreShadow) this.shadowColor = null
        this.strikethrough = null
        this.underlined = null
        this.font = null
        this.italic = null
        this.bold = null

        if (includingFont) {
            this.font = null
            this.hoverEvent = null
            this.obfuscated = null
            this.insertion = null
            this.keybind = null
            this.translate = null
            this.clickEvent = null
        }
    }
}