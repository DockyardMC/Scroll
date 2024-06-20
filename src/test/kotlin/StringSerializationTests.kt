import io.github.dockyardmc.scroll.*
import io.github.dockyardmc.scroll.extensions.toComponent
import kotlin.test.Test
import kotlin.test.assertEquals

class StringSerializationTests {

    @Test
    fun testBasicSerialization() {
        val input = "<red>This text should be red"
        val expected = Components.new(mutableListOf(
            TextComponent("This text should be red", color = "#FF5555")
        ))

        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testCustomColorSerialization() {
        val input = "<#ff54aa>This text should be cute pink color :3"
        val expected = Components.new(mutableListOf(
            TextComponent("This text should be cute pink color :3", color = "#ff54aa")
        ))

        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testMultipleSerializationComponents() {
        val input = "<red>This <orange>text <yellow>should <lime>be <aqua>multi <pink>colored"
        val expected = Components.new(mutableListOf(
            TextComponent("This ", color = ComponentColorTags.colorTags["<red>"]),
            TextComponent("text ", color = ComponentColorTags.colorTags["<orange>"]),
            TextComponent("should ", color = ComponentColorTags.colorTags["<yellow>"]),
            TextComponent("be ", color = ComponentColorTags.colorTags["<lime>"]),
            TextComponent("multi ", color = ComponentColorTags.colorTags["<aqua>"]),
            TextComponent("colored", color = ComponentColorTags.colorTags["<pink>"]),
        ))
        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testClosingTags() {
        val input = "<bold><underlined><italics><strikethrough><obfuscated>test </obfuscated>test </strikethrough>test </italics>test </underlined>test </bold>test"
        val expected = Components.new(mutableListOf(
            TextComponent("test ", bold = true, underlined = true, italic = true, strikethrough = true, obfuscated = true),
            TextComponent("test ", bold = true, underlined = true, italic = true, strikethrough = true),
            TextComponent("test ", bold = true, underlined = true, italic = true),
            TextComponent("test ", bold = true, underlined = true),
            TextComponent("test ", bold = true),
            TextComponent("test"),
        ))
        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testClosingTagsMini() {
        val input = "<b><u><i><s><o>test </o>test </s>test </i>test </u>test </b>test"
        val expected = Components.new(mutableListOf(
            TextComponent("test ", bold = true, underlined = true, italic = true, strikethrough = true, obfuscated = true),
            TextComponent("test ", bold = true, underlined = true, italic = true, strikethrough = true),
            TextComponent("test ", bold = true, underlined = true, italic = true),
            TextComponent("test ", bold = true, underlined = true),
            TextComponent("test ", bold = true),
            TextComponent("test"),
        ))
        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testFormatting() {
        val inputToExpectedOutput = mutableMapOf<String, Component>(
            "<b>test" to TextComponent("test", bold = true),
            "<bold>test" to TextComponent("test", bold = true),
            "<i>test" to TextComponent("test", italic = true),
            "<italic>test" to TextComponent("test", italic = true),
            "<u>test" to TextComponent("test", underlined = true),
            "<underline>test" to TextComponent("test", underlined = true),
            "<o>test" to TextComponent("test", obfuscated = true),
            "<obfuscated>test" to TextComponent("test", obfuscated = true),
            "<bold><italic><underline><obfuscated>test" to TextComponent("test",
                bold = true,
                italic = true,
                underlined = true,
                obfuscated = true,
                ),
            "<b><i><u><o>test" to TextComponent("test",
                bold = true,
                italic = true,
                underlined = true,
                obfuscated = true,
            ),
            "<bold><italic><underline><obfuscated><reset>test" to TextComponent("test"),
            "<b><i><u><o><r>test" to TextComponent("test"),
        )

        inputToExpectedOutput.forEach { assertEquals(it.key.toComponent().extra!![0].toJson(), it.value.toJson()) }
    }

    @Test
    fun testKeybind() {
        val input = "<keybind|key.jump>"
        val expected = Components.new(mutableListOf(
            KeybindComponent("key.jump")
        ))

        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testGay() {
        val input = "<rainbow>gayyyy"
        val expected = Components.new(mutableListOf(
            TextComponent("g", color = "#FF0000"),
            TextComponent("a", color = "#FF4D00"),
            TextComponent("y", color = "#FF9900"),
            TextComponent("y", color = "#FFE600"),
            TextComponent("y", color = "#CCFF00"),
            TextComponent("y", color = "#80FF00"),
        ))
        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testTranslation() {
        val input = "<translate|advancements.husbandry.safely_harvest_honey.description>"
        val expected = Components.new(mutableListOf(
            TranslatableComponent("advancements.husbandry.safely_harvest_honey.description")
        ))

        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testBigBoi() {
        val input = "<white><i>hello <yellow><b>hello <aqua><u>hello <dark_red><o>hello<reset>! Welcome, <#ff54aa>LukynkaCZE<r>! <keybind|key.jump> to jump. <translate|advMode.mode> should be advMode.mode"
        val expected = Components.new(mutableListOf(
            TextComponent(
                text = "hello ",
                color = ComponentColorTags.colorTags["<white>"],
                italic = true
            ),
            TextComponent(
                text = "hello ",
                color = ComponentColorTags.colorTags["<yellow>"],
                bold = true
            ),
            TextComponent(
                text = "hello ",
                color = ComponentColorTags.colorTags["<aqua>"],
                underlined = true
            ),
            TextComponent(
                text = "hello",
                color = ComponentColorTags.colorTags["<dark_red>"],
                obfuscated = true
            ),
            TextComponent(
                text = "! Welcome, "
            ),
            TextComponent(
                text = "LukynkaCZE",
                color = "#ff54aa"
            ),
            TextComponent(
                text = "! ",
            ),
            KeybindComponent(
                keybind = "key.jump"
            ),
            TextComponent(
                text = " to jump. "
            ),
            TranslatableComponent(
                translate = "advMode.mode"
            ),
            TextComponent(
                text = " should be advMode.mode"
            )
            ))

        assertEquals(expected.toJson(), input.toComponent().toJson())
    }

    @Test
    fun testStyleStripping() {
        val input = "<yellow>hello there! <rainbow><i>LukynkaCZE<yellow>! <lime><u>How are you doing this fine evening?<r>"
        val expected = "hello there! LukynkaCZE! How are you doing this fine evening?"

        assertEquals(expected, input.toComponent().stripStyling())
    }
}