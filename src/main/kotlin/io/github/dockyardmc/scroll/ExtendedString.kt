package io.github.dockyardmc.scroll


fun String.split(start: String, end: String): MutableList<String> {
    val result = mutableListOf<String>()

    var out = ""
    var open = false
    var insideQuotes = false
    this.forEachIndexed { index, it ->
        if(insideQuotes && it != '\'') { out = "$out${it}"; return@forEachIndexed }
        if(it == '\'') insideQuotes = !insideQuotes

        if(it == '<') {
            open = true
            if(out.isNotEmpty()) {
                result.add(out)
            }
            out = "$it"
            return@forEachIndexed
        }
        out = "$out${it}"

        if(it == '>') {
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