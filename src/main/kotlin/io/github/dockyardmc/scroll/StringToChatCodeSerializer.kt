package io.github.dockyardmc.scroll

class StringToChatCodeSerializer {

    private var text: String = ""
    private lateinit var component: Component
    private var tokens = mutableListOf<String>()

    fun serialize(string: String) {
        text = string
        tokens = text.split("<", ">")

    }
}

