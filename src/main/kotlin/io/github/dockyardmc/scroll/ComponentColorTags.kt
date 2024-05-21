package io.github.dockyardmc.scroll

enum class TextColor(val hex: String) {
    RED("#FF5555"),
    YELLOW("#FFFF55"),
    LIME("#55FF55"),
    AQUA("#55FFFF"),
    BLUE("#5555FF"),
    PINK("#FF55FF"),
    DARK_RED("#AA0000"),
    ORANGE("#FFAA00"),
    GREEN("#00AA00"),
    CYAN("#00AAAA"),
    DARK_BLUE("#0000AA"),
    PURPLE("#AA00AA"),
    WHITE("#FFFFFF"),
    GRAY("#AAAAAA"),
    DARK_GRAY("#555555"),
    BLACK("#000000"),
}

object ComponentColorTags {

    val colorTags = mutableMapOf<String, String>(
        "<red>" to TextColor.RED.hex,
        "<yellow>" to TextColor.YELLOW.hex,
        "<lime>" to TextColor.LIME.hex,
        "<aqua>" to TextColor.AQUA.hex,
        "<blue>" to TextColor.BLUE.hex,
        "<pink>" to TextColor.PINK.hex,

        "<dark red>" to TextColor.DARK_RED.hex,
        "<dark_red>" to TextColor.DARK_RED.hex,
        "<orange>" to TextColor.ORANGE.hex,
        "<gold>" to TextColor.ORANGE.hex,
        "<green>" to TextColor.GREEN.hex,
        "<cyan>" to TextColor.CYAN.hex,
        "<dark blue>" to TextColor.DARK_BLUE.hex,
        "<dark_blue>" to TextColor.DARK_BLUE.hex,
        "<purple>" to TextColor.DARK_BLUE.hex,

        "<white>" to TextColor.WHITE.hex,
        "<gray>" to TextColor.GRAY.hex,
        "<dark gray>" to TextColor.DARK_GRAY.hex,
        "<dark_gray>" to TextColor.DARK_GRAY.hex,
        "<black>" to TextColor.BLACK.hex,
    )
}