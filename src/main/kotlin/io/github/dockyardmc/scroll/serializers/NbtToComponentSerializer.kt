package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.ClickEvent
import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.HoverEvent
import net.kyori.adventure.nbt.BinaryTagTypes
import net.kyori.adventure.nbt.CompoundBinaryTag
import net.kyori.adventure.nbt.DoubleBinaryTag
import net.kyori.adventure.nbt.StringBinaryTag

object NbtToComponentSerializer {


}


fun CompoundBinaryTag.contains(key: String): Boolean {
    return get(key) != null
}