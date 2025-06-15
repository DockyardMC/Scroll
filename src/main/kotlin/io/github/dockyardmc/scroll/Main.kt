package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.extensions.toComponent
import io.github.dockyardmc.scroll.serializers.ComponentSerializer
import io.github.dockyardmc.scroll.serializers.JsonToComponentSerializer
import net.kyori.adventure.nbt.TagStringIO

fun main() {
    val hover = "<hover:show_text:'hi :3'>test"
    val nbt = ComponentSerializer.componentToNbt(hover.toComponent())
    val snbt = TagStringIO.get().asString(nbt)
    println(snbt)
}

//{"hoverEvent":{"action":"show_text","contents":"hi :3"},"text":"test"}