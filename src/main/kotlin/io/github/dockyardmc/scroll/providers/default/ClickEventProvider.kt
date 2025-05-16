package io.github.dockyardmc.scroll.providers.default

import io.github.dockyardmc.scroll.ClickEvent
import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.providers.ClosingNamedFormatProvider
import io.github.dockyardmc.scroll.providers.FormatProviderContext

class ClickEventProvider : ClosingNamedFormatProvider("click", listOf()) {

    override fun formatNormal(context: FormatProviderContext, component: Component) {
        component.clickEvent = when (context.getArgument(0)) {
            "open_url" -> ClickEvent.OpenUrl(context.getArgument(1))
            "open_file" -> ClickEvent.OpenFile(context.getArgument(1))
            "run_command" -> ClickEvent.RunCommand(context.getArgument(1))
            "suggest_command" -> ClickEvent.SuggestCommand(context.getArgument(1))
            "change_page" -> ClickEvent.ChangePage(context.getArgument(1).toInt())
            "copy_to_clipboard" -> ClickEvent.CopyToClipboard(context.getArgument(1))
            "show_dialog" -> ClickEvent.ShowDialog(context.getArgument(1))
            "custom" -> ClickEvent.Custom(
                context.getArgument(1),
                context.getArgument(2)
            )

            else -> return
        }
    }

    override fun formatClosing(context: FormatProviderContext, component: Component) {
        component.clickEvent = null
    }
}
