package io.github.dockyardmc.scroll

import io.github.dockyardmc.scroll.providers.FormatProvider
import io.github.dockyardmc.scroll.providers.default.*

object Scroll {

    val defaultFormatProviders = listOf<FormatProvider>(
        HexColorProvider(),
        ShadowColorProvider(),
        BoldProvider(),
        ItalicsProvider(),
        UnderlinedProvider(),
        StrikethroughProvider(),
        ObfuscatedProvider(),
        NamedColorProvider(),
        FontProvider(),
        ResetProvider(),
        TranslateProvider(),
        KeybindProvider(),
        ClickEventProvider(),
        HoverEventProvider(),
    )

    private val defaultParser = ScrollParser(defaultFormatProviders)

    fun parse(input: String): Component {
        return defaultParser.parse(input)
    }
}
