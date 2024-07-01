package io.github.dockyardmc.scroll

fun rainbowHex(numSteps: Int, lightness: Float = 1f): List<String> {
    val colors = mutableListOf<String>()
    for (i in 0..<numSteps) {
        val hue = i.toFloat() / numSteps
        val rgb = CustomColor.fromHsv(hue, 1f, 1f)
        val lightAdjusted = rgb.adjustLightness(lightness)
        val (r, g, b) = lightAdjusted
        val hexColor = String.format("#%02X%02X%02X", r, g, b)
        colors.add(hexColor)
    }
    return colors
}

fun hexInterpolation(startColor: String, endColor: String, step: Float): String {
    val startRGB = startColor.replace("#", "").chunked(2).map { it.toInt(16) }
    val endRGB = endColor.replace("#", "").chunked(2).map { it.toInt(16) }

    val interpolatedRGB = startRGB.zip(endRGB) { start, end ->
        (start + (end - start) * step).toInt()
    }

    val interpolatedColor = StringBuilder("#")
    for (value in interpolatedRGB) {
        val hex = value.coerceIn(0, 255).toString(16).padStart(2, '0')
        interpolatedColor.append(hex)
    }

    return interpolatedColor.toString()
}