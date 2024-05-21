# Scroll 📜
A Minecraft Component library made for the [DockyardMC](https://github.com/DockyardMC/Dockyard) project

`<yellow>A Minecraft <rainbow><u>Component Library<yellow> for the <#3d91ff><bold>DockyardMC<yellow> project!`

![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/304281ed-f72b-4036-9c66-69d929fe5644)

---

## Installation

<img src="https://cdn.worldvectorlogo.com/logos/kotlin-2.svg" width="16px"></img>
**Kotlin DSL**
```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/DockyardMC/Scroll")
        credentials {
            project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER")
            project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("io.github.dockyardmc:scroll:<version>")
}
```
<img src="https://github.com/LukynkaCZE/PrettyLog/assets/48604271/3293feca-7395-4100-8b61-257ba40dbe3c" width="18px"></img>
**Gradle**
```groovy
repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/DockyardMC/Scroll")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_USER")
            password = project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
  implementation 'io.github.dockyardmc:scroll:<version>'
}
```

**Note:** You will need to set `gpr.user` to your github username and `gpr.token` to your github access token in `gradle.properties` [(Authenticating to Github Packages)](https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages#authenticating-to-github-packages). Alternativly you can also set `GITHUB_USER` and `GITHUB_TOKEN` in your environment variable

---

## Usage

You can create a different type of components using the following syntax

**_Normal Text Component_**
```kotlin
val textComponent = TextComponent(
    text = "woah red bold text",
    color = TextColor.RED,
    bold = true
    // ..other styling
)
```
![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/563c8062-4766-4d9d-9181-7bf11d36a684)



**_Keybind Component_** (Reads the current keybinds from client)
```kotlin
val keybindComponent = KeybindComponent(
    keybind = "key.jump"
    // ..other styling
)
```
![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/1921623c-9357-4fc8-a042-ed3f3a2dfb14)

**_Translatable Component_** (Reads the language file from client)
```kotlin
val translatableComponent = TranslatableComponent(
    translate = "advancements.husbandry.safely_harvest_honey.description"
    // ..other styling
)
```
![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/3fd17122-6ecd-4f5e-b287-f588250829c5)

---
You can also create component that contains other components
```kotlin
val bigBoiComponent = Components.new(mutableListOf(
    TextComponent(text = "Im looking to "),
    TextComponent(text = "buy ", color = TextColor.YELLOW, bold = true),
    TextComponent(text = "your "),
    TextComponent(text = "finest potions ", color = "#9436ff", italics = true)
    TextComponent(text = "so I can jump high when I press "),
    KeybindComponent(keybind = "key.jump", color = TextColor.YELLOW, underlined = true)
))
```
![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/88e2fd91-ce19-492a-a033-52b68529316f)

---
### String to Components
You can also write your components using string format
```kotlin
val component = "<yellow>HE'S <red><bold>ALLERGIC<yellow> TO BEANS!".toComponent()
```
![obrazek](https://github.com/DockyardMC/Scroll/assets/48604271/f7d969c3-c9ab-426e-ad10-00027015a485)

The following tags are valid:

- Colors:
  - `<color>` for predefined color (ex. red, orange, lime, aqua)
  - `<#hex>` for custom hex color (must include the # at the start)
- Format
  - `<bold>`
  - `<italic>`
  - `<obfuscated>`
  - `<underline>`
  - `<strikethrough>`
- Events
  - `<hover|'text''>` for hover-able text. Formatting applies to inner text as well
  - `<click|action|'text'>` for clickable text. Actions are following
    - `open_url` - following text needs to start with "https://"
    - `run_command` - following text needs to start with "/"
    - `suggest_command` - following text needs to start with "/"
    - `copy_to_clipboard`
- Other
  - `<font|'file_name'>` to change font
  - `<rainbow>` to make the text after rainbow. (Resets when another color is applied or when <reset> is reached)
  - `<transition|#hex1|#hex2|step>` - Color Interpolation, step is float between 0 and 1
  - `<reset>` to reset formatting

In some cases (format and reset) you can use shortened versions
- `<b>` is short of `<bold>`
- `<i>` is short of `<italic>`
- `<o>` is short of `<obfuscated>`
- `<u>` is short of `<underline>`
- `<s>` is short of `<strikethrough>`
- `<r>` is short of `<reset>`

⚠️ _Currently, there is no support for custom tags_

---

## Serialization
You can convert string **to Component** using `"string".toComponent()` method or using `StringToComponentSerializer().serialize("string")`

---

You can convert component **to NBT** using `Component.toNbt()` or using `ComponentToNbtSerializer.serialize(component)`. This will give you instance of [Hephaistos](https://github.com/Minestom/Hephaistos) NBT Compound

---

You can convert component to Json using `Component.toJson()` or using `ComponentToJsonSerializer.serialize(component)`

---

You can convert Json to component using `JsonToComponentSerializer.serialize(json)`

---

⚠️ _**There is currently no integration with either vanilla minecraft server components or adventure/minimessage**_
