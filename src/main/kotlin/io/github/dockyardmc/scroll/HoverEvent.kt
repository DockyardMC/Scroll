package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.extensions.put
import io.github.dockyardmc.scroll.extensions.toComponent
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
sealed class HoverEvent {
    abstract val type: String

    open fun getNbt(): NBTCompound {
        return NBT.Compound { builder ->
            builder.put("action", type)
        }
    }

    companion object {
        /**
         * @throws MissingFieldException if there's a field missing
         * @throws UnsupportedOperationException if `action` is not supported
         */
        fun fromNbt(nbt: NBTCompound): HoverEvent {
            return when (val action = nbt.getString("action")) {
                "show_text" -> ShowText(nbt.getCompound("value")?.toComponent() ?: throw MissingFieldException("value"))
                "show_item" -> ShowItem(
                    nbt.getString("id") ?: throw MissingFieldException("id"),
                    nbt.getInt("count") ?: throw MissingFieldException("count")
                )
                "show_entity" -> ShowEntity(
                    nbt.getString("id") ?: throw MissingFieldException("id"),
                    nbt.getString("uuid") ?: throw MissingFieldException("uuid"),
                    nbt.getCompound("name")?.toComponent()
                )

                null -> throw MissingFieldException("action")
                else -> throw UnsupportedOperationException("unknown `action`: `$action`")
            }
        }
    }

    @Serializable
    @SerialName("show_text")
    class ShowText(val value: Component) : HoverEvent() {
        constructor(value: String) : this(value.toComponent())

        @Transient
        override val type: String = "show_text"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("value", value.toNBT())
            }
        }
    }

    // TODO: add components (recursive dependency? :( )
    @Serializable
    @SerialName("show_item")
    class ShowItem(val id: String, val count: Int) : HoverEvent() {
        @Transient
        override val type: String = "show_item"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("id", id)
                put("count", count)
            }
        }
    }

    @SerialName("show_entity")
    class ShowEntity(val id: String, val uuid: String, val name: Component? = null) : HoverEvent() {
        @Transient
        override val type: String = "show_entity"

        override fun getNbt(): NBTCompound {
            return super.getNbt().kmodify {
                put("id", id)
                put("uuid", uuid)
                if(name != null)
                    put("name", name.toNBT())
            }
        }
    }
}
