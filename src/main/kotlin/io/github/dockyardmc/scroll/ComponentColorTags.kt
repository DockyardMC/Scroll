package io.github.dockyardmc.scroll

object ComponentColorTags {

    val colorTags = mutableMapOf<String, String>(
        "<red>" to "#FF5555",
        "<yellow>" to "#FFFF55",
        "<lime>" to "#55FF55",
        "<aqua>" to "#55FFFF",
        "<blue>" to "#5555FF",
        "<pink>" to "#FF55FF",

        "<dark red>" to "#AA0000",
        "<dark_red>" to "#AA0000",
        "<orange>" to "#FFAA00",
        "<gold>" to "#FFAA00",
        "<green>" to "#00AA00",
        "<cyan>" to "#00AAAA",
        "<dark blue>" to "#0000AA",
        "<dark_blue>" to "#0000AA",
        "<purple>" to "#AA00AA",

        "<white>" to "#FFFFFF",
        "<gray>" to "#AAAAAA",
        "<dark gray>" to "#555555",
        "<dark_gray>" to "#555555",
        "<black>" to "#000000",
    )

    val formatTags = mutableMapOf<String, String>(
        "<o>" to "§k",
        "<obf>" to "§k",
        "<obfuscated>" to "§k",
        "<bold>" to "§l",
        "<b>" to "§l",
        "<s>" to "§k",
        "<strike>" to "§k",
        "<u>" to "§u",
        "<underline>" to "§u",
        "<i>" to "§i",
        "<italic>" to "§i",
        "<r>" to "§r",
        "<reset>" to "§r",
    )
}