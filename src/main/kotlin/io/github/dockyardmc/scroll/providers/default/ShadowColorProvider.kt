package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class ShadowColorProvider: ClosingNamedFormatProvider("shadow", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        val shadowColor = context.getArgument(0)
        component.shadowColor = shadowColor
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.shadowColor = null
    }

}