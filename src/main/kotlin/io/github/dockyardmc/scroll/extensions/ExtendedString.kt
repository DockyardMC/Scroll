package io.github.dockyardmc.scroll.extensions

import io.github.dockyardmc.scroll.Component
import io.github.dockyardmc.scroll.serializers.StringToComponentSerializer

fun String.toComponent(): Component {
    return StringToComponentSerializer().serialize(this)
}

fun String.scrollSanitized(): String {
    var out = ""
    this.forEachIndexed { index, char ->
        out += (if(char.toString() == "<") "\\<" else char.toString())
    }
    return out
}

fun String.stripComponentTags(): String {
    return this.toComponent().stripStyling()
}

fun String.split(start: String, end: String): MutableList<String> {
    val result = mutableListOf<String>()

    var out = ""
    var open = false
    var insideQuotes = false
    this.forEachIndexed { index, it ->
        if(insideQuotes && it != '\'') { out = "$out${it}"; return@forEachIndexed }
        if(it == '\'') insideQuotes = !insideQuotes

        if(it == start[0] && getCharacterBefore(this, index).toString() != "\\") {
            open = true
            if(out.isNotEmpty()) {
                result.add(out)
            }
            out = "$it"
            return@forEachIndexed
        }
        if(it.toString() == "\\" && getCharacterAfter(this, index) == start[0]) return@forEachIndexed
        out = "$out${it}"

        if(it == end[0] && open) {
            open = false
            if(out.isNotEmpty()) {
                result.add(out)
            }
            out = ""
        }
        if(index == this.length - 1) {
            result.add(out)
        }
    }
    return result
}

fun getCharacterBefore(string: String, currentIndex: Int): Char? {
    val index = currentIndex - 1
    val value = if(index < 0) null else string[index]
    return value
}

fun getCharacterAfter(string: String, currentIndex: Int): Char? {
    val index = currentIndex + 1
    val value = if(index > string.toMutableList().size) null else string[index]
    return value
}