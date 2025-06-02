package io.github.dockyardmc.scroll

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonClassDiscriminator
import net.kyori.adventure.nbt.CompoundBinaryTag

@Suppress("MemberVisibilityCanBePrivate")
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("action")
sealed class ClickEvent {
    companion object {
        /**
         * @throws MissingFieldException if there's a field missing
         * @throws UnsupportedOperationException if `action` is not supported
         */
        fun fromNbt(nbt: CompoundBinaryTag): ClickEvent {
            // Implementation would need to be updated for the adventure-nbt library
            // This is a placeholder for the conversion logic
            return when (val action = nbt.getString("action")) {
                "open_url" -> OpenUrl(nbt.getString("url"))
                "open_file" -> OpenFile(nbt.getString("path"))
                "run_command" -> RunCommand(nbt.getString("command"))
                "suggest_command" -> SuggestCommand(nbt.getString("command"))
                "change_page" -> ChangePage(nbt.getInt("page"))
                "copy_to_clipboard" -> CopyToClipboard(nbt.getString("value"))
                "show_dialog" -> ShowDialog(nbt.getString("dialog"))
                "custom" -> Custom(nbt.getString("id"), nbt.getString("payload"))
                else -> throw UnsupportedOperationException("ClickEvent action '$action' is not supported")
            }
        }

    }

    abstract val action: String

    open fun getNbt(): CompoundBinaryTag {
        return CompoundBinaryTag.builder()
            .putString("action", action)
            .build()
    }


    @Serializable
    @SerialName("open_url")
    class OpenUrl(val url: String) : ClickEvent() {
        @Transient
        override val action: String = "open_url"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("url", url)
        }
    }


    @Serializable
    @SerialName("open_file")
    class OpenFile(val path: String) : ClickEvent() {
        @Transient
        override val action: String = "open_file"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("path", path)
        }
    }


    @Serializable
    @SerialName("run_command")
    class RunCommand(val command: String) : ClickEvent() {
        @Transient
        override val action: String = "run_command"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("command", command)
        }
    }


    @Serializable
    @SerialName("suggest_command")
    class SuggestCommand(val command: String) : ClickEvent() {
        @Transient
        override val action: String = "suggest_command"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("command", command)
        }
    }


    @Serializable
    @SerialName("change_page")
    class ChangePage(val page: Int) : ClickEvent() {
        @Transient
        override val action: String = "change_page"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putInt("page", page)
        }
    }


    @Serializable
    @SerialName("copy_to_clipboard")
    class CopyToClipboard(val value: String) : ClickEvent() {
        @Transient
        override val action: String = "copy_to_clipboard"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("value", value)
        }
    }


    @Serializable
    @SerialName("show_dialog")
    class ShowDialog(val dialog: String) : ClickEvent() {
        @Transient
        override val action: String = "show_dialog"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().putString("dialog", dialog)
        }
    }


    @Serializable
    @SerialName("custom")
    class Custom(val id: String, val payload: String) : ClickEvent() {
        @Transient
        override val action: String = "custom"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt()
                .putString("id", id)
                .putString("payload", payload)
        }
    }

}