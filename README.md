![Scroll Banner](https://github.com/user-attachments/assets/77ca5f70-0ad0-48a9-9542-4c0bdc976a16)

---

[![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fmvn.devos.one%2Freleases%2Fio%2Fgithub%2Fdockyardmc%2Fscroll%2Fmaven-metadata.xml&style=for-the-badge&logo=maven&logoColor=%23FFFFFF&label=Latest%20Version&color=%23afff87)](https://mvn.devos.one/#/releases/io/github/dockyardmc/dockyard)
[![Static Badge](https://img.shields.io/badge/Language-Kotlin-Kotlin?style=for-the-badge&color=%23963cf4)](https://kotlinlang.org/)

[![wakatime](https://wakatime.com/badge/github/DockyardMC/Scroll.svg?style=for-the-badge)](https://wakatime.com/badge/github/DockyardMC/Scroll)
[![Discord](https://img.shields.io/discord/1242845647892123650?label=Discord%20Server&color=%237289DA&style=for-the-badge&logo=discord&logoColor=%23FFFFFF)](https://discord.gg/SA9nmfMkdc)
[![Static Badge](https://img.shields.io/badge/Donate-Ko--Fi-pink?style=for-the-badge&logo=ko-fi&logoColor=%23FFFFFF&color=%23ff70c8)](https://ko-fi.com/LukynkaCZE)


A Minecraft Component library made for the [DockyardMC](https://github.com/DockyardMC/Dockyard) project. It includes easy to use and learn format to represent text components as strings, similiar to minimessage

## Installation

<img src="https://cdn.worldvectorlogo.com/logos/kotlin-2.svg" width="16px"></img>
**Kotlin DSL**
```kotlin
repositories {
    maven {
        name = "devOS"
        url = uri("https://mvn.devos.one/releases")
    }
}

dependencies {
    implementation("io.github.dockyardmc:scroll:2.3")
}
```
<img src="https://github.com/LukynkaCZE/PrettyLog/assets/48604271/3293feca-7395-4100-8b61-257ba40dbe3c" width="18px"></img>
**Gradle Groovy**
```groovy
repositories {
  maven {
    name "devOS"
    url "https://mvn.devos.one/releases"
  }
}

dependencies {
  implementation 'io.github.dockyardmc:scroll:2.3'
}
```
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
  - `<hover:'text''>` for hover-able text. Formatting applies to inner text as well
  - `<click:action:'text'>` for clickable text. Actions are following
    - `open_url` - following text needs to start with "https://"
    - `run_command` - following text needs to start with "/"
    - `suggest_command` - following text needs to start with "/"
    - `copy_to_clipboard`
- Other
  - `<font:'file_name'>` to change font
  - `<rainbow>` to make the text after rainbow. (Resets when another color is applied or when <reset> is reached)
  - `<transition:#hex1:#hex2:step>` - Color Interpolation, step is float between 0 and 1
  - `<reset>` to reset formatting

In some cases (format and reset) you can use shortened versions
- `<b>` is short of `<bold>`
- `<i>` is short of `<italic>`
- `<o>` is short of `<obfuscated>`
- `<u>` is short of `<underline>`
- `<s>` is short of `<strikethrough>`
- `<r>` is short of `<reset>`

You also end tags by prefixing the tag with `/`
- `<bold>this is bold :D</bold> this is not :(`

⚠️ _Currently, there is no support for custom tags_

---

## Sanitization & Escapes

You can escape tag by putting `\\` at the begging of it

`<lime>Please login using /login \\<password>` will result to `"Please login using /login <password>"`

You can sanitize string using `String.scrollSanitized()` This is recommended for any player input

`"Player123: omg <red>red color and <bold>bold woah".scrollSanitized()` would result to `"Player123: omg \\<red>red color and \\<bold>bold woah"` 

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
