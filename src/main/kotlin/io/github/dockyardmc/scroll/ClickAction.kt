package io.github.dockyardmc.scroll

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ClickAction {
    @SerialName("open_url")
    OPEN_URL,
    @SerialName("run_command")
    RUN_COMMAND,
    @SerialName("suggest_command")
    SUGGEST_COMMAND,
    @SerialName("copy_to_clipboard")
    COPY_TO_CLIPBOARD
}