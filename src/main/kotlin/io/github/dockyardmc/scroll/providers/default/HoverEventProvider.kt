package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.HoverAction
import io.github.dockyardmc.scroll.HoverEvent
import io.github.dockyardmc.scroll.Scroll
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class HoverEventProvider: ClosingNamedFormatProvider("hover", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        val action = HoverAction.valueOf(context.getArgument(0).uppercase())
        val value = Scroll.parse(context.getArgument(1))
        val hoverEvent = HoverEvent(action, value)

        component.hoverEvent = hoverEvent
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.hoverEvent = null
    }
}