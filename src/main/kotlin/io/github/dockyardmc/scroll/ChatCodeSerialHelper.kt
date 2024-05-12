package io.github.dockyardmc.scroll

object ChatCodeSerialHelper {

    val defaultColors = mutableMapOf<String, String>(
        "<red>" to "§c",
        "<yellow>" to "§e",
        "<lime>" to "§a",
        "<aqua>" to "§b",
        "<blue>" to "§9",
        "<pink>" to "§d",

        "<dark red>" to "§4",
        "<dark_red>" to "§4",
        "<orange>" to "§6",
        "<gold>" to "§6",
        "<green>" to "§2",
        "<cyan>" to "§3",
        "<dark blue>" to "§1",
        "<dark_blue>" to "§1",
        "<purple>" to "§5",

        "<white>" to "§f",
        "<gray>" to "§7",
        "<dark gray>" to "§8",
        "<dark_gray>" to "§8",
        "<black>" to "§0",
    )

    val defaultModifiers = mutableMapOf<String, String>(
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