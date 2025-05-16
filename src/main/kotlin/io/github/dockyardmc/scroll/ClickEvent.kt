package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.extensions.put
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonClassDiscriminator
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound

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
        fun fromNbt(nbt: NBTCompound): ClickEvent {
            return when (val action = nbt.getString("action")) {
                "open_url" -> OpenUrl(nbt.getString("url") ?: throw MissingFieldException("url"))
                "open_file" -> OpenFile(nbt.getString("path") ?: throw MissingFieldException("path"))
                "run_command" -> RunCommand(nbt.getString("command") ?: throw MissingFieldException("command"))
                "suggest_command" -> SuggestCommand(nbt.getString("command") ?: throw MissingFieldException("command"))
                "change_page" -> ChangePage(nbt.getInt("page") ?: throw MissingFieldException("page"))
                "copy_to_clipboard" -> CopyToClipboard(nbt.getString("value") ?: throw MissingFieldException("value"))
                "show_dialog" -> ShowDialog(nbt.getString("dialog") ?: throw MissingFieldException("dialog"))
                "custom" -> Custom(
                    nbt.getString("id") ?: throw MissingFieldException("id"),
                    nbt.getString("payload") ?: throw MissingFieldException("payload")
                )

                null -> throw MissingFieldException("action")
                else -> throw UnsupportedOperationException("unknown `action`: `$action`")
            }
        }
    }

    abstract val action: String

    open fun getNbt(): NBTCompound {
        return NBT.Compound { builder ->
            builder.put("action", action)
        }
    }

    @Serializable
    @SerialName("open_url")
    class OpenUrl(val url: String) : ClickEvent() {
        @Transient
        override val action: String = "open_url"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("url", url)
            }
        }
    }

    @Serializable
    @SerialName("open_file")
    class OpenFile(val path: String) : ClickEvent() {
        @Transient
        override val action: String = "open_file"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("path", path)
            }
        }
    }

    @Serializable
    @SerialName("run_command")
    class RunCommand(val command: String) : ClickEvent() {
        @Transient
        override val action: String = "run_command"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("command", command)
            }
        }
    }

    @Serializable
    @SerialName("suggest_command")
    class SuggestCommand(val command: String) : ClickEvent() {
        @Transient
        override val action: String = "suggest_command"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("command", command)
            }
        }
    }

    @Serializable
    @SerialName("change_page")
    class ChangePage(val page: Int) : ClickEvent() {
        @Transient
        override val action: String = "change_page"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("page", page)
            }
        }
    }

    @Serializable
    @SerialName("copy_to_clipboard")
    class CopyToClipboard(val value: String) : ClickEvent() {
        @Transient
        override val action: String = "copy_to_clipboard"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("value", value)
            }
        }
    }

    @Serializable
    @SerialName("show_dialog")
    class ShowDialog(val dialog: String) : ClickEvent() {
        @Transient
        override val action: String = "show_dialog"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("dialog", dialog)
            }
        }
    }

    @Serializable
    @SerialName("custom")
    class Custom(val id: String, val payload: String) : ClickEvent() {
        @Transient
        override val action: String = "custom"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("id", id)
                put("payload", payload)
            }
        }
    }
}

class MissingFieldException(field: String) :
    RuntimeException("failed to parse: field `$field` is missing")