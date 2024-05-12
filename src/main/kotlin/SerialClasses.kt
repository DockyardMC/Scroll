import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseComponent(
    open val extra: MutableList<BaseComponent>? = null,
    open val keybind: String? = null,
    open val text: String? = null,
    open val translate: String? = null,

    open val color: String? = null,
    open val bold: Boolean? = null,
    open val italic: Boolean? = null,
    open val underline: Boolean? = null,
    open val strikethrough: Boolean? = null,
    open val obfuscated: Boolean? = null,
    open val font: String? = null,
    open val insertion: String? = null,
    open val hoverEvent: HoverEvent? = null,
    open val clickEvent: ClickEvent? = null
)

class TextComponent(
    override val text: String,
    override val extra: MutableList<BaseComponent>? = null,
    override val color: String? = null,
    override val bold: Boolean? = null,
    override val italic: Boolean? = null,
    override val underline: Boolean? = null,
    override val strikethrough: Boolean? = null,
    override val obfuscated: Boolean? = null,
    override val font: String? = null,
    override val insertion: String? = null,
    @SerialName("hoverEvent")
    override val hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    override val clickEvent: ClickEvent? = null
): BaseComponent()

class KeybindComponent(
    override val keybind: String,
    override val extra: MutableList<BaseComponent>? = null,
    override val color: String? = null,
    override val bold: Boolean? = null,
    override val italic: Boolean? = null,
    override val underline: Boolean? = null,
    override val strikethrough: Boolean? = null,
    override val obfuscated: Boolean? = null,
    override val font: String? = null,
    override val insertion: String? = null,
    @SerialName("hoverEvent")
    override val hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    override val clickEvent: ClickEvent? = null
): BaseComponent()

class TranslatableComponent(
    override val translate: String,
    override val extra: MutableList<BaseComponent>? = null,
    override val color: String? = null,
    override val bold: Boolean? = null,
    override val italic: Boolean? = null,
    override val underline: Boolean? = null,
    override val strikethrough: Boolean? = null,
    override val obfuscated: Boolean? = null,
    override val font: String? = null,
    override val insertion: String? = null,
    @SerialName("hoverEvent")
    override val hoverEvent: HoverEvent? = null,
    @SerialName("click_event")
    override val clickEvent: ClickEvent? = null
): BaseComponent()


@Serializable
class ClickEvent(
    val action: ClickAction,
    val value: String? = null
)

@Serializable
class HoverEvent(
    val action: HoverAction,
    val contents: String? = null
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
