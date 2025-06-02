package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.ClickEvent
import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.HoverEvent
import net.kyori.adventure.nbt.*

object ComponentSerializer {

    fun nbtToComponent(nbt: CompoundBinaryTag): Component {
        val component = Component()

        if (nbt.get("color") != null) component.color = nbt.getString("color")
        if (nbt.get("shadow_color") != null) {
            val shadowColorList = nbt.getList("shadow_color", BinaryTagTypes.DOUBLE)
            component.shadowColor = shadowColorList.map { (it as DoubleBinaryTag).value() }
        }
        if (nbt.get("bold") != null) component.bold = nbt.getBoolean("bold")
        if (nbt.get("font") != null) component.font = nbt.getString("font")
        if (nbt.get("italic") != null) component.italic = nbt.getBoolean("italic")
        if (nbt.get("insertion") != null) component.insertion = nbt.getString("insertion")
        if (nbt.get("keybind") != null) component.keybind = nbt.getString("keybind")
        if (nbt.get("obfuscated") != null) component.obfuscated = nbt.getBoolean("obfuscated")
        if (nbt.get("strikethrough") != null) component.strikethrough = nbt.getBoolean("strikethrough")
        if (nbt.get("text") != null) component.text = nbt.getString("text")
        if (nbt.get("translate") != null) component.translate = nbt.getString("translate")
        if (nbt.get("underlined") != null) component.underlined = nbt.getBoolean("underlined")

        if (nbt.contains("hover_event")) {
            val hoverEventTag = nbt.getCompound("hover_event")
            component.hoverEvent = HoverEvent.fromNbt(hoverEventTag)
        }

        if (nbt.contains("click_event")) {
            val clickEventTag = nbt.getCompound("click_event")
            component.clickEvent = ClickEvent.fromNbt(clickEventTag)
        }

        if (nbt.contains("extra")) {
            val extraList = nbt.getList("extra", BinaryTagTypes.COMPOUND)
            val componentList = mutableListOf<Component>()

            extraList.forEach { tag ->
                when (tag.type()) {
                    BinaryTagTypes.COMPOUND -> componentList.add(nbtToComponent(tag as CompoundBinaryTag))
                    BinaryTagTypes.STRING -> componentList.add(Component(text = (tag as StringBinaryTag).value()))
                    else -> {}
                }
            }

            if (componentList.isNotEmpty()) {
                component.extra = componentList
            }
        }

        return component
    }

    fun componentToNbt(component: Component): CompoundBinaryTag {
        val compound = CompoundBinaryTag.empty()

        if (component.color != null) compound.putString("color", component.color!!)
        if (component.shadowColor != null) {
            val list = ListBinaryTag.listBinaryTag(BinaryTagTypes.DOUBLE, component.shadowColor!!.map { color -> DoubleBinaryTag.doubleBinaryTag(color) })
            compound.put("shadow_color", list)
        }

        if (component.bold != null) compound.putBoolean("bold", component.bold!!)
        if (component.font != null) compound.putString("font", component.font!!)
        if (component.italic != null) compound.putBoolean("italic", component.italic!!)
        if (component.insertion != null) compound.putString("insertion", component.insertion!!)
        if (component.keybind != null) compound.putString("keybind", component.keybind!!)
        if (component.obfuscated != null) compound.putBoolean("obfuscated", component.obfuscated!!)
        if (component.strikethrough != null) compound.putBoolean("strikethrough", component.strikethrough!!)
        if (component.text != null) compound.putString("text", component.text!!)
        if (component.translate != null) compound.putString("translate", component.translate!!)
        if (component.underlined != null) compound.putBoolean("underlined", component.underlined!!)

        val hover = component.hoverEvent
        if (hover != null) {
            compound.put("hover_event", hover.getNbt())
        }

        val click = component.clickEvent
        if (click != null) {
            compound.put("click_event", click.getNbt())
        }

        val extra = component.extra
        if (!extra.isNullOrEmpty()) {
            val extraList = ListBinaryTag.builder(BinaryTagTypes.COMPOUND)
            extra.forEach { child ->
                extraList.add(componentToNbt(child))
            }
            compound.put("extra", extraList.build())
        }


        return compound
    }

}