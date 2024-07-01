package io.github.dockyardmc.scroll

//Ordered in precise way cause text colors used to be enum in the minecraft client. It still uses that in some packets
//https://wiki.vg/Text_formatting#Colors
enum class LegacyTextColor(val hex: String) {
    BLACK("#000000"),
    DARK_BLUE("#0000AA"),
    GREEN("#00AA00"),
    CYAN("#00AAAA"),
    DARK_RED("#AA0000"),
    PURPLE("#AA00AA"),
    ORANGE("#FFAA00"),
    GRAY("#AAAAAA"),
    DARK_GRAY("#555555"),
    BLUE("#5555FF"),
    LIME("#55FF55"),
    AQUA("#55FFFF"),
    RED("#FF5555"),
    PINK("#FF55FF"),
    YELLOW("#FFFF55"),
    WHITE("#FFFFFF"),
}

object ComponentColorTags {

    val colorTags = mutableMapOf<String, String>(
        "<red>" to LegacyTextColor.RED.hex,
        "<yellow>" to LegacyTextColor.YELLOW.hex,
        "<lime>" to LegacyTextColor.LIME.hex,
        "<aqua>" to LegacyTextColor.AQUA.hex,
        "<blue>" to LegacyTextColor.BLUE.hex,
        "<pink>" to LegacyTextColor.PINK.hex,

        "<dark red>" to LegacyTextColor.DARK_RED.hex,
        "<dark_red>" to LegacyTextColor.DARK_RED.hex,
        "<orange>" to LegacyTextColor.ORANGE.hex,
        "<gold>" to LegacyTextColor.ORANGE.hex,
        "<green>" to LegacyTextColor.GREEN.hex,
        "<cyan>" to LegacyTextColor.CYAN.hex,
        "<dark blue>" to LegacyTextColor.DARK_BLUE.hex,
        "<dark_blue>" to LegacyTextColor.DARK_BLUE.hex,
        "<purple>" to LegacyTextColor.DARK_BLUE.hex,

        "<white>" to LegacyTextColor.WHITE.hex,
        "<gray>" to LegacyTextColor.GRAY.hex,
        "<dark gray>" to LegacyTextColor.DARK_GRAY.hex,
        "<dark_gray>" to LegacyTextColor.DARK_GRAY.hex,
        "<black>" to LegacyTextColor.BLACK.hex,
    )
}