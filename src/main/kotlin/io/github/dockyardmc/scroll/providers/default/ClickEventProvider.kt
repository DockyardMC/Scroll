package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.ClickAction
import io.github.dockyardmc.scroll.ClickEvent
import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class ClickEventProvider : ClosingNamedFormatProvider("click", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        val action = ClickAction.valueOf(context.getArgument(0).uppercase())
        val value = context.getArgument(1)
        val clickEvent = ClickEvent(action, value)

        component.clickEvent = clickEvent
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.clickEvent = null
    }
}