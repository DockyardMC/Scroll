package io.github.dockyardmc.scroll.extensions

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.serializers.ComponentSerializer
import net.kyori.adventure.nbt.BinaryTag
import net.kyori.adventure.nbt.CompoundBinaryTag
import net.kyori.adventure.nbt.TagStringIO

fun CompoundBinaryTag.toComponent(): Component {
    return ComponentSerializer.nbtToComponent(this)
}

fun CompoundBinaryTag.contains(key: String): Boolean {
    return get(key) != null
}

fun BinaryTag.toSNBT(): String {
    return TagStringIO.get().asString(this)
}