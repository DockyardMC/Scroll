package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.HoverEvent
import io.github.dockyardmc.scroll.extensions.toComponent
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class HoverEventProvider: ClosingNamedFormatProvider("hover", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        component.hoverEvent = when (val action = context.getArgument(0)) {
            "show_text" -> HoverEvent.ShowText(context.getArgument(1).toComponent())
            "show_entity" -> HoverEvent.ShowEntity(context.getArgument(1), context.getArgument(2), context.getArgumentOrNull(3)?.toComponent())

            else -> HoverEvent.ShowText(action.toComponent())
        }
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.hoverEvent = null
    }
}
