package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.*
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTDouble
import org.jglrxavpok.hephaistos.nbt.NBTString

object NbtToComponentSerializer {

    fun serializeNbt(nbt: NBTCompound): Component {
        val component = Component()

        component.color = nbt.getString("color")
        component.color = nbt.getString("shadow_color")
        component.shadowColor = nbt.getList<NBTDouble>("shadow_color")?.asListView()?.map { it.getValue() }
        component.bold = nbt.getBoolean("bold")
        component.font = nbt.getString("font")
        component.italic = nbt.getBoolean("italic")
        component.insertion = nbt.getString("insertion")
        component.keybind = nbt.getString("keybind")
        component.obfuscated = nbt.getBoolean("obfuscated")
        component.strikethrough = nbt.getBoolean("strikethrough")
        component.text = nbt.getString("text")
        component.translate = nbt.getString("translate")
        component.underlined = nbt.getBoolean("underlined")

        val hover = nbt.getCompound("hover_event")
        if (hover != null) {
            component.hoverEvent = HoverEvent.fromNbt(hover)
        }

        val click = nbt.getCompound("click_event")
        if (click != null) {
            component.clickEvent = ClickEvent.fromNbt(click)
        }

        val list = nbt.getList<NBT>("extra")

        val listOut = mutableListOf<Component>()
        list?.forEach {
            if (it is NBTCompound) {
                listOut.add(serializeNbt(it))
            }
            if(it is NBTString) {
                listOut.add(Component(text = it.value))
            }
        }
        if (listOut.isNotEmpty()) component.extra = listOut

        return component
    }
}