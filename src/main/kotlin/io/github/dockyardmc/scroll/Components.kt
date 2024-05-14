package io.github.dockyardmc.scroll

object Components {

    fun new(components: MutableList<Component>): Component {
        return Component(
            text = "",
            extra = components
        )
    }
}