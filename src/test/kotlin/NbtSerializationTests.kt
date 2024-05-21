import io.github.dockyardmc.scroll.ComponentColorTags
import io.github.dockyardmc.scroll.extensions.toComponent
import io.github.dockyardmc.scroll.extensions.put
import org.jglrxavpok.hephaistos.nbt.NBT
import kotlin.test.Test
import kotlin.test.assertEquals

class NbtSerializationTests {

    @Test
    fun testStringToNbt() {
        val input = "<#ff54aa>omg haiiii bestie AsoDesu_ <pink><b>:3<r> <keybind|key.jump> to jump. <translate|advMode.mode> should be advMode.mode"
        val expected = mutableListOf(
            NBT.Compound {
                it.put("text", "omg haiiii bestie AsoDesu_ ")
                it.put("color", "#ff54aa")
            },
            NBT.Compound {
                it.put("color", ComponentColorTags.colorTags["<pink>"])
                it.put("bold", true)
                it.put("text", ":3")
            },
            NBT.Compound {
                it.put("text", " ")
            },
            NBT.Compound {
                it.put("keybind", "key.jump")
            },
            NBT.Compound {
                it.put("text", " to jump. ")
            },
            NBT.Compound {
                it.put("translate", "advMode.mode")
            },
            NBT.Compound {
                it.put("text", " should be advMode.mode")
            })

        assertEquals(
            NBT.Compound {
                it.put("extra", expected)
                it.put("text", "")
            }.toSNBT(),
            input.toComponent().toNBT().toSNBT())
    }
}