package io.github.dockyardmc.scroll

class NewSerializer(val depth: Int = 0) {

    private var components = mutableListOf<BaseComponent>()
    private var tokens = mutableListOf<String>()
    private var rainbowHex = rainbowHex(20)
    private lateinit var innerSerializer: NewSerializer

    fun serialize(text: String) {
        tokens = text.split("<", ">")
        if(depth < 1) innerSerializer = NewSerializer(depth + 1)


    }

}