package io.github.dockyardmc.scroll

fun main() {

    //Testing purposes only

    var test = """
        <white><i>hello <yellow><b>hello <aqua><u>hello<reset>! Welcome, <pink>LukynkaCZE<r>!

        <white>You can write <rainbow>raaaaiiiinbooooow <i><b>raaaaaahhhh<r><white>!!

        <white>You can press and hover over text! <hover|'<gray>Click to open URL: <aqua><u>https://cataas.com/cat'><click|open_url|'https://cataas.com/cat'><lime><b><u>Click Here<r> to see random cat!

        <white>You can also write in <#ff2181>Custom HEX color<white>!!

        <white>And you can use replacables like <#ff6721><keybind|key.jump> <white>and <#ff2121><translate|biome.minecraft.windswept_hills>

        <white>Ohhh and you can write in <font|alt><#9666ff>the enchanting language!!<r> See?
        
    """.trimIndent()

    val from = "#fd91ff"
    val to = "#caff91"
    val times = 10

    repeat(times + 1) {
        val step = it.toFloat() / 10
        test = test.plus("\n<yellow>WOO: <transition|$from|$to|$step>This is pretty cool! <gray>($step)")
    }

    val componentSerializer = ComponentSerializer()
    val comp = componentSerializer.serialize(test)

    // To be pasted to cmd block
    println("/tellraw @a ${comp.toJson()}")
}