package br.com.prescript.users

enum class SortDir {
    ASC, DESC;

    companion object {
        fun findOrNull(sortDir: String) =
            entries.find { it.name == sortDir.uppercase() }
    }
}
