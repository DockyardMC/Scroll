package io.github.dockyardmc.scroll.extensions

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.serializers.NbtToComponentSerializer
import net.kyori.adventure.nbt.CompoundBinaryTag

fun CompoundBinaryTag.toComponent(): Component {
    return NbtToComponentSerializer.serializeNbt(this)
}