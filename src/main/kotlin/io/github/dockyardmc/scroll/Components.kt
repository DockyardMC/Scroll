package io.github.dockyardmc.scroll

object Components {

    fun new(components: MutableList<BaseComponent>): BaseComponent {
        return BaseComponent(
            text = "",
            extra = components
        )
    }
}