package net.meilcli.koach.gradle

class Dependency(
    val group: String,
    val name: String,
    val version: String
) : MutableMap<String, String> by mutableMapOf() {

    init {
        put("group", group)
        put("name", name)
        put("version", version)
    }
}