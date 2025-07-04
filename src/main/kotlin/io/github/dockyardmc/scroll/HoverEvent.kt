package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.extensions.toComponent
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
sealed class HoverEvent {
    abstract val type: String

    open fun getNbt(): CompoundBinaryTag {
        return CompoundBinaryTag.builder()
            .putString("action", type)
            .build()
    }

    companion object {
        /**
         * @throws MissingFieldException if there's a field missing
         * @throws UnsupportedOperationException if `action` is not supported
         */
        fun fromNbt(nbt: CompoundBinaryTag): HoverEvent {
            return when (val action = nbt.getString("action")) {
                "show_text" -> {
                    val valueTag = nbt.getCompound("value")
                    ShowText(valueTag.toComponent())
                }

                "show_entity" -> {
                    val id = nbt.getString("id")
                    val uuid = nbt.getString("uuid")
                    val nameTag = nbt.getCompound("name")
                    ShowEntity(id, uuid, nameTag.toComponent())
                }

                else -> throw UnsupportedOperationException("HoverEvent action '$action' is not supported")
            }
        }

    }

    @Serializable
    @SerialName("show_text")
    class ShowText(val value: Component) : HoverEvent() {
        constructor(value: String) : this(value.toComponent())

        @Transient
        override val type: String = "show_text"

        override fun getNbt(): CompoundBinaryTag {
            return super.getNbt().put("value", value.toNBT())
        }
    }

    @SerialName("show_entity")
    class ShowEntity(val id: String, val uuid: String, val name: Component? = null) : HoverEvent() {
        @Transient
        override val type: String = "show_entity"

        override fun getNbt(): CompoundBinaryTag {
            var nbt = super.getNbt()
            nbt = nbt.putString("id", id)
            nbt = nbt.putString("uuid", uuid)
            if (name != null) nbt = nbt.put("name", name.toNBT())

            return nbt
        }
    }
}
