package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.serializers.ComponentToJsonSerializer
import io.github.dockyardmc.scroll.serializers.ComponentToNbtSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jglrxavpok.hephaistos.nbt.NBTCompound

@Serializable
open class Component(
    open var extra: MutableList<Component>? = null,
    open var keybind: String? = null,
    open var text: String? = null,
    open var translate: String? = null,
    open var color: String? = null,
    open var bold: Boolean? = null,
    open var italic: Boolean? = null,
    open var underlined: Boolean? = null,
    open var strikethrough: Boolean? = null,
    open var obfuscated: Boolean? = null,
    open var font: String? = null,
    open var insertion: String? = null,
    open var hoverEvent: HoverEvent? = null,
    open var clickEvent: ClickEvent? = null
) {
    fun toNBT(): NBTCompound {
        return ComponentToNbtSerializer.serializeComponent(this)
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
}


class TextComponent(
    override var text: String?,
    override var extra: MutableList<Component>? = null,
    override var color: String? = null,
    override var bold: Boolean? = null,
    override var italic: Boolean? = null,
    override var underlined: Boolean? = null,
    override var strikethrough: Boolean? = null,
    override var obfuscated: Boolean? = null,
    override var font: String? = null,
    override var insertion: String? = null,
    @SerialName("hoverEvent")
    override var hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    override var clickEvent: ClickEvent? = null
): Component()

class KeybindComponent(
    override var keybind: String?,
    override var extra: MutableList<Component>? = null,
    override var color: String? = null,
    override var bold: Boolean? = null,
    override var italic: Boolean? = null,
    override var underlined: Boolean? = null,
    override var strikethrough: Boolean? = null,
    override var obfuscated: Boolean? = null,
    override var font: String? = null,
    override var insertion: String? = null,
    @SerialName("hoverEvent")
    override var hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    override var clickEvent: ClickEvent? = null
): Component()

class TranslatableComponent(
    override var translate: String?,
    override var extra: MutableList<Component>? = null,
    override var color: String? = null,
    override var bold: Boolean? = null,
    override var italic: Boolean? = null,
    override var underlined: Boolean? = null,
    override var strikethrough: Boolean? = null,
    override var obfuscated: Boolean? = null,
    override var font: String? = null,
    override var insertion: String? = null,
    override var hoverEvent: HoverEvent? = null,
    override var clickEvent: ClickEvent? = null
): Component()

@Serializable
class ClickEvent(
    val action: ClickAction,
    val value: String? = null
)

@Serializable
class HoverEvent(
    val action: HoverAction,
    val contents: Component? = null
)

@Serializable
enum class ClickAction {
    @SerialName("open_url")
    OPEN_URL,
    @SerialName("run_command")
    RUN_COMMAND,
    @SerialName("suggest_command")
    SUGGEST_COMMAND,
    @SerialName("copy_to_clipboard")
    COPY_TO_CLIPBOARD
}

@Serializable
enum class HoverAction {
    @SerialName("show_text")
    SHOW_TEXT,
    @SerialName("show_item")
    SHOW_ITEM
}
