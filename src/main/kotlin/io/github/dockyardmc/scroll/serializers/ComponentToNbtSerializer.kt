package io.github.dockyardmc.scroll.serializers

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.extensions.put
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTDouble
import org.jglrxavpok.hephaistos.nbt.NBTType
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound

object ComponentToNbtSerializer {
    fun serializeComponent(c: Component): NBTCompound {
        val nbtWriter = MutableNBTCompound()

        nbtWriter.put("color", c.color)
        if(c.shadowColor != null) {
            val array = NBT.List(NBTType.TAG_Double, c.shadowColor!!.map { NBTDouble((it / 255.0)) }.toList())
            nbtWriter.put("shadow_color", array)
        }

        nbtWriter.put("bold", c.bold)
        nbtWriter.put("font", c.font)
        nbtWriter.put("italic", c.italic)
        nbtWriter.put("insertion", c.insertion)
        nbtWriter.put("keybind", c.keybind)
        nbtWriter.put("obfuscated", c.obfuscated)
        nbtWriter.put("strikethrough", c.strikethrough)
        nbtWriter.put("text", c.text)
        nbtWriter.put("translate", c.translate)
        nbtWriter.put("underlined", c.underlined)

        val hover = c.hoverEvent
        if(hover != null) {
            nbtWriter.put("hover_event", hover.getNbt())
        }

        val click = c.clickEvent
        if(click != null) {
            nbtWriter.put("click_event", click.getNbt())
        }

        val list = c.extra
        val listOut = mutableListOf<NBTCompound>()
        list?.forEach {
            val comp = serializeComponent(it)
            listOut.add(comp)
        }
        nbtWriter.put("extra", listOut)

        val nbtCompound = NBT.Compound { root: MutableNBTCompound ->
            nbtWriter.forEach {
                root.put(it.key, it.value)
            }
        }
        return nbtCompound
    }
}