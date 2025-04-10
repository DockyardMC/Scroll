package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.CustomColor
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class ShadowColorProvider: ClosingNamedFormatProvider("shadow", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        val shadowColor = CustomColor.fromHex(context.getArgument(0))
        val alpha = context.getArgumentOrNull(1)?.toInt() ?: 255.0
        component.shadowColor = listOf(shadowColor.r.toDouble(), shadowColor.g.toDouble(), shadowColor.b.toDouble(), alpha.toDouble())
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.shadowColor = null
    }

}