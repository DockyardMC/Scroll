package io.github.dockyardmc.scroll


class Serializer(val depth: Int = 0) {

    private var components = mutableListOf<BaseComponent>()
    private var tokens = mutableListOf<String>()
    private var rainbowHex = rainbowHex(20)
    private lateinit var innerSerializer: Serializer

    fun resetFormatting(component: BaseComponent, includingColor: Boolean = false) {
        component.bold = null
        component.italic = null
        component.underlined = null
        component.obfuscated = null
        component.strikethrough = null
        if(includingColor) component.color = null
    }

    fun serialize(string: String): BaseComponent {
        tokens = string.split("<", ">")
        if(depth < 1) innerSerializer = Serializer(depth + 1)

        var color: String? = null
        var bold: Boolean? = null
        var italic: Boolean? = null
        var underline: Boolean? = null
        var obfuscated: Boolean? = null
        var strikethrough: Boolean? = null

        var hoverEvent: HoverEvent? = null
        var clickEvent: ClickEvent? = null

        val component = BaseComponent()

        var rainbow = false
        var currentRainbowIndex = 0
        tokens.forEach {
            if(it.startsWith("<") && it.endsWith(">")) {
                if(tokenIsColor(it)) {
                    component.color = getTokenColor(it)
                    resetFormatting(component)
                }

                if(it.startsWith("<#")) {
                    val hex = it.replace("<", "").replace(">", "")
                    component.color = hex
                    resetFormatting(component)
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
                    resetFormatting(component)
                }

                if(it.startsWith("<keybind")) {
                    val split = it.split("|")
                    val key = split[1].replace("'", "").removeSuffix(">")
                    components.add(BaseComponent(
                        keybind = key,
                        color = color,
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

                if(it.startsWith("<translate")) {
                    val split = it.split("|")
                    val value = split[1].replace("'", "").removeSuffix(">")
                    components.add(BaseComponent(
                        translate = value,
                        color = color,
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
                    val comp = innerSerializer.serialize(hoverText)
                    hoverEvent = HoverEvent(
                        action = HoverAction.SHOW_TEXT,
                        contents = comp
                    )
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

                    println(color)

                    color = hexInterpolation(colorFrom, colorTo, step)
                }

                return@forEach
            }

            if(rainbow) {
                it.forEach { letter ->
                    if(currentRainbowIndex > 19) currentRainbowIndex = 0
                    val customColor = rainbowHex[currentRainbowIndex]
                    components.add(BaseComponent(
                        text = letter.toString(),
                        color = customColor,
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

            components.add(BaseComponent(
                text = it,
                color = color,
                bold = bold,
                italic = italic,
                underlined = underline,
                obfuscated = obfuscated,
                strikethrough = strikethrough,
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