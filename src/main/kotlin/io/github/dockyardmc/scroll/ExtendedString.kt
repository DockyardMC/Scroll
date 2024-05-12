package io.github.dockyardmc.scroll


fun String.split(start: String, end: String): MutableList<String> {
    val result = mutableListOf<String>()
    var startIndex = 0

    this.forEachIndexed { _, _ ->
        val startIdx = this.indexOf(start, startIndex)
        if (startIdx == -1) {
            result.add(this.substring(startIndex))
            return@forEachIndexed
        }

        val endIndex = this.indexOf(end, startIdx + start.length)
        if (endIndex == -1) {
            result.add(this.substring(startIndex))
            return@forEachIndexed
        }

        if (startIdx != startIndex) {
            result.add(this.substring(startIndex, startIdx))
        }
        result.add(this.substring(startIdx, endIndex + end.length))
        startIndex = endIndex + end.length
    }
    return result
}