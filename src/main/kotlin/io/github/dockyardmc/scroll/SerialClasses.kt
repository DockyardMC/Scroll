package io.github.dockyardmc.scroll

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseComponent(
    open var extra: MutableList<BaseComponent>? = null,
    open val keybind: String? = null,
    open val text: String? = null,
    open val translate: String? = null,
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
)

class TextComponent(
    override var text: String,
    override var extra: MutableList<BaseComponent>? = null,
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
): BaseComponent()

class KeybindComponent(
    override val keybind: String,
    override var extra: MutableList<BaseComponent>? = null,
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
): BaseComponent()

class TranslatableComponent(
    override val translate: String,
    override var extra: MutableList<BaseComponent>? = null,
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
): BaseComponent()


@Serializable
class ClickEvent(
    val action: ClickAction,
    val value: String? = null
)

@Serializable
class HoverEvent(
    val action: HoverAction,
    val contents: BaseComponent? = null
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
