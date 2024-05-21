package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.extensions.toComponent

fun main() {
    val comp = "<yellow>A Minecraft <rainbow><u>Component Library<yellow> for the <#3d91ff><bold>DockyardMC<yellow> project!".toComponent()
    println("/tellraw @a ${comp.toJson()}")
}

