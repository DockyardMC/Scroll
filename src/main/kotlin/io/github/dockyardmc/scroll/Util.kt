package io.github.dockyardmc.scroll

import kotlin.math.roundToInt

fun hsvToRgb(h: Float, s: Float, v: Float): Triple<Int, Int, Int> {
    val h_i = (h * 6).toInt()
    val f = h * 6 - h_i
    val p = v * (1 - s)
    val q = v * (1 - f * s)
    val t = v * (1 - (1 - f) * s)
    val (r, g, b) = when (h_i) {
        0 -> Triple(v, t, p)
        1 -> Triple(q, v, p)
        2 -> Triple(p, v, t)
        3 -> Triple(p, q, v)
        4 -> Triple(t, p, v)
        else -> Triple(v, p, q)
    }
    return Triple((r * 255).roundToInt(), (g * 255).roundToInt(), (b * 255).roundToInt())
}

fun rainbowHex(numSteps: Int): List<String> {
    val colors = mutableListOf<String>()
    for (i in 0..<numSteps) {
        val hue = i.toFloat() / numSteps
        val (r, g, b) = hsvToRgb(hue, 1f, 1f)
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