package io.github.dockyardmc.scroll

class MissingFieldException(field: String) :
    RuntimeException("failed to parse: field `$field` is missing")