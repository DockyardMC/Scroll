package io.github.dockyardmc.scroll


class ComponentSerializer(private val depth: Int = 0) {

    private var components = mutableListOf<Component>()
    private var tokens = mutableListOf<String>()
    private var rainbowHex = rainbowHex(20)
    private lateinit var innerComponentSerializer: ComponentSerializer

    private var color: String? = null
    private var bold: Boolean? = null
    private var italic: Boolean? = null
    private var underline: Boolean? = null
    private var obfuscated: Boolean? = null
    private var strikethrough: Boolean? = null

    private var hoverEvent: HoverEvent? = null
    private var clickEvent: ClickEvent? = null

    private var font: String? = null

    private var rainbow = false
    private var currentRainbowIndex = 0

    private fun resetFormatting(includingColor: Boolean = false, includingEvents: Boolean = false) {
        bold = null
        italic = null
        underline = null
        obfuscated = null
        strikethrough = null
        if(includingColor) color = null
        if(includingEvents) {
            hoverEvent = null
            clickEvent = null
        }
    }

    fun serialize(string: String): Component {
        tokens = string.split("<", ">")
        if(depth < 1) innerComponentSerializer = ComponentSerializer(depth + 1)


        tokens.forEach {
            if(it.startsWith("<") && it.endsWith(">")) {
                if(tokenIsColor(it)) {
                    color = getTokenColor(it)
                    resetFormatting()
                }

                if(it.startsWith("<#")) {
                    val hex = it.replace("<", "").replace(">", "")
                    color = hex
                    resetFormatting()
                }

                when(it) {
                    "<b>",
                    "<bold>" -> bold = true
                    "<i>",
                    "<italic>" -> italic = true
                    "<u>",
                    "<underline>" -> underline = true
                    "<o>",
                    "<obfuscated>" -> obfuscated = true
                    "<s>",
                    "<strikethrough>" -> strikethrough = true
                    "<r>",
                    "<reset>" -> {
                        color = null
                        bold = null
                        italic = null
                        underline = null
                        obfuscated = null
                        strikethrough = null
                        rainbow = false
                        currentRainbowIndex = 0
                        hoverEvent = null
                        clickEvent = null
                        font = null
                    }
                    "<rf>",
                    "<reset format>",
                    "<resetf>",
                    "<resetformat>" -> {
                        bold = null
                        italic = null
                        underline = null
                        obfuscated = null
                        strikethrough = null
                        currentRainbowIndex = 0
                    }
                    "</hover>" -> hoverEvent = null
                    "</click>" -> clickEvent = null
                }

                if(it == "<rainbow>") {
                    rainbow = true
                    resetFormatting()
                }

                if(it.startsWith("<keybind")) {
                    val split = it.split("|")
                    val key = split[1].replace("'", "").removeSuffix(">")
                    components.add(Component(
                        keybind = key,
                        color = color,
                        bold = bold,
                        font = font,
                        italic = italic,
                        underlined = underline,
                        obfuscated = obfuscated,
                        strikethrough = strikethrough,
                        hoverEvent = hoverEvent,
                        clickEvent = clickEvent
                    ))
                    return@forEach
                }

                if(it.startsWith("<translate")) {
                    val split = it.split("|")
                    val value = split[1].replace("'", "").removeSuffix(">")
                    components.add(Component(
                        translate = value,
                        color = color,
                        font = font,
                        bold = bold,
                        italic = italic,
                        underlined = underline,
                        obfuscated = obfuscated,
                        strikethrough = strikethrough,
                        hoverEvent = hoverEvent,
                        clickEvent = clickEvent
                    ))
                    return@forEach
                }

                if(it.startsWith("<hover")) {
                    val split = it.split("|")
                    var hoverText = split[1].replace("'", "")
                    hoverText = hoverText.removeSuffix(">")
                    val comp = innerComponentSerializer.serialize(hoverText)
                    hoverEvent = HoverEvent(
                        action = HoverAction.SHOW_TEXT,
                        contents = comp
                    )
                }

                if(it.startsWith("<font")) {
                    val split = it.split("|")
                    var fontValue: String? = split[1].removeSuffix(">")
                    if(!fontValue!!.contains(":")) fontValue = "minecraft:$fontValue"
                    if(fontValue == "minecraft:default") fontValue = null
                    font = fontValue
                }

                if(it.startsWith("<click")) {
                    val split = it.split("|")
                    val action = split[1].uppercase()
                    var value = split[2].replace("'", "")
                    value = value.removeSuffix(">")
                    clickEvent = ClickEvent(
                        action = ClickAction.valueOf(action),
                        value = value
                    )
                }

                // <transition|#fd91ff|#caff91|0>
                if(it.startsWith("<transition")) {
                    val split = it.split("|")
                    val colorFrom = split[1]
                    val colorTo = split[2]
                    val step = split[3].removeSuffix(">").toFloat()

                    color = hexInterpolation(colorFrom, colorTo, step)
                }

                return@forEach
            }

            if(rainbow) {
                it.forEach { letter ->
                    if(currentRainbowIndex > 19) currentRainbowIndex = 0
                    val customColor = rainbowHex[currentRainbowIndex]
                    components.add(Component(
                        text = letter.toString(),
                        color = customColor,
                        font = font,
                        bold = bold,
                        italic = italic,
                        underlined = underline,
                        obfuscated = obfuscated,
                        strikethrough = strikethrough,
                        hoverEvent = hoverEvent,
                        clickEvent = clickEvent
                    ))
                    currentRainbowIndex++
                }
                return@forEach
            }

            components.add(Component(
                text = it,
                color = color,
                bold = bold,
                italic = italic,
                underlined = underline,
                obfuscated = obfuscated,
                strikethrough = strikethrough,
                font = font,
                hoverEvent = hoverEvent,
                clickEvent = clickEvent
            ))
        }
        return Components.new(components)
    }


    private fun tokenIsColor(token: String): Boolean {
        return ComponentColorTags.colorTags.containsKey(token)
    }

    private fun getTokenColor(token: String): String {
        return ComponentColorTags.colorTags[token]!!
    }
}