package io.github.dockyardmc.scroll

import BaseComponent
import ClickEvent
import HoverEvent
import KeybindComponent
import TextComponent
import TranslatableComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

fun main() {
//    val test = "<red>hello <orange>yes <yellow>hi <dark red><italic>:3 <r>How are you doing <yellow><bold><italic>bestieee<r>?? Im actually doing pretty &e<b>&ogood&f myself! <dark gray>[<red><u>Click For More Chat Log<dark_gray>]"
//    val serializer = ComponentSerializer()
//    val comp = serializer.serialize(test)
//    val json = Json.encodeToJsonElement<BaseComponent>(comp)
//    println(json)

    val test = Components.new(mutableListOf(
        TextComponent(
            text = "Fun fact! You can press ",
            color = "#ddbfff"
        ),
        KeybindComponent(
            keybind = "key.jump",
            bold = true,
            color = "#f2ff42",
            hoverEvent = HoverEvent(
                action = HoverAction.SHOW_TEXT,
                contents = "why you looking here? :P"
            ),
            clickEvent = ClickEvent(
                action = ClickAction.SUGGEST_COMMAND,
                value = "/say @s hi :3"
            )
        ),
        TextComponent(
            text = " to jump!\n",
            color = "#ddbfff"
        ),
        TextComponent(
            text = "Normal Translatable test: "
        ),
        TranslatableComponent(
            translate = "deathScreen.title",
            color = "#ff4281",
        ),
    ))

    val json = Json.encodeToJsonElement<BaseComponent>(test)
    println("/tellraw @a $json")
}