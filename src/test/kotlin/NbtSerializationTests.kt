import io.github.dockyardmc.scroll.ScrollUtil
import io.github.dockyardmc.scroll.extensions.toComponent
import io.github.dockyardmc.scroll.extensions.toSNBT
import io.github.dockyardmc.scroll.serializers.ComponentSerializer
import net.kyori.adventure.nbt.BinaryTagTypes
import net.kyori.adventure.nbt.CompoundBinaryTag
import net.kyori.adventure.nbt.DoubleBinaryTag
import net.kyori.adventure.nbt.ListBinaryTag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NbtSerializationTests {

    @Test
    fun nbtToComponentTest() {
        val nbt = CompoundBinaryTag.builder()
            .put(
                "hover_event", CompoundBinaryTag.builder()
                    .putString("action", "show_text")
                    .put(
                        "value", CompoundBinaryTag.builder()
                            .put(
                                "extra", CompoundBinaryTag.builder()
                                    .put(
                                        "extra", CompoundBinaryTag.builder()
                                            .putString("text", "Click to open the store URL")
                                            .putString("color", ScrollUtil.colorTags["<yellow>"]!!)
                                            .build()
                                    )
                                    .putString("text", "")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .putString("text", "CLICK HERE")
            .putBoolean("bold", true)
            .put(
                "click_event", CompoundBinaryTag.builder()
                    .putString("action", "open_url")
                    .putString("url", "https://store.mccisland.net")
                    .build()
            )
            .putBoolean("underline", true)
            .putString("color", ScrollUtil.colorTags["<lime>"]!!)
            .build()

        val expected = "<lime><b><u><hover:show_text:'<yellow>Click to open the store URL'><click:open_url:https://store.mccisland.net>CLICK HERE".toComponent()

        val final = CompoundBinaryTag.builder()
            .put(
                "extra", ListBinaryTag.builder(BinaryTagTypes.COMPOUND)
                    .add(nbt)
                    .build()
            )
            .putString("text", "")
            .build()

        assertEquals(expected.getAllComponents().size, ComponentSerializer.nbtToComponent(final).getAllComponents().size)
    }

    @Test
    fun hoverAndClickEventTest() {
        val input = "<lime><b><u><hover|'<yellow>Click to open the store URL'><click|open_url|https://store.mccisland.net>CLICK HERE"

        val expectedCompound = CompoundBinaryTag.builder()
            .put(
                "hover_event", CompoundBinaryTag.builder()
                    .putString("action", "show_text")
                    .put(
                        "contents", CompoundBinaryTag.builder()
                            .put(
                                "extra", CompoundBinaryTag.builder()
                                    .put(
                                        "extra", CompoundBinaryTag.builder()
                                            .putString("text", "Click to open the store URL")
                                            .putString("color", ScrollUtil.colorTags["<yellow>"]!!)
                                            .build()
                                    )
                                    .putString("text", "")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .putString("text", "CLICK HERE")
            .putBoolean("bold", true)
            .put(
                "click_event", CompoundBinaryTag.builder()
                    .putString("action", "open_url")
                    .putString("url", "https://store.mccisland.net")
                    .build()
            )
            .putBoolean("underline", true)
            .putString("color", ScrollUtil.colorTags["<lime>"]!!)
            .build()

        val expectedList = ListBinaryTag.builder(BinaryTagTypes.COMPOUND)
            .add(expectedCompound)
            .build()

        val final = CompoundBinaryTag.builder()
            .put("extra", expectedList)
            .putString("text", "")
            .build()

        val nbt = input.toComponent().toNBT()
        println("${input.toComponent().toNBT()}")
        println("$nbt")
        assertEquals(final.size(), nbt.size())
        for (key in final.keySet()) {
            assertTrue(nbt.keySet().contains(key))
        }
    }

    @Test
    fun testStringToNbt() {
        val input = "<#ff54aa>omg haiiii bestie <shadow:#ba70ff>AsoDesu_ </shadow><pink><b>:3<r> <keybind:key.jump> to jump. <translate:advMode.mode> should be advMode.mode"

        val expectedList = ListBinaryTag.builder(BinaryTagTypes.COMPOUND)
            .add(
                CompoundBinaryTag.builder()
                    .putString("text", "omg haiiii bestie ")
                    .putString("color", "#ff54aa")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("color", "#ff54aa")
                    .put(
                        "shadow_color", ListBinaryTag.builder(BinaryTagTypes.DOUBLE)
                            .add(DoubleBinaryTag.doubleBinaryTag(186.0 / 255.0))
                            .add(DoubleBinaryTag.doubleBinaryTag(112.0 / 255.0))
                            .add(DoubleBinaryTag.doubleBinaryTag(255.0 / 255.0))
                            .add(DoubleBinaryTag.doubleBinaryTag(255.0 / 255.0))
                            .build()
                    )
                    .putString("text", "AsoDesu_ ")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("color", ScrollUtil.colorTags["<pink>"]!!)
                    .putBoolean("bold", true)
                    .putString("text", ":3")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("text", " ")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("keybind", "key.jump")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("text", " to jump. ")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("translate", "advMode.mode")
                    .build()
            )
            .add(
                CompoundBinaryTag.builder()
                    .putString("text", " should be advMode.mode")
                    .build()
            )
            .build()

        val expected = CompoundBinaryTag.builder()
            .put("extra", expectedList)
            .putString("text", "")
            .build()

        assertEquals(
            expected.toSNBT(),
            input.toComponent().toNBT().toSNBT()
        )
    }
}
