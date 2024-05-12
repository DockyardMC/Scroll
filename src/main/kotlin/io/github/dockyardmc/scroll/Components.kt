package io.github.dockyardmc.scroll

import BaseComponent

object Components {

    fun new(components: MutableList<BaseComponent>): BaseComponent {
        return BaseComponent(
            text = "",
            extra = components
        )
    }
}