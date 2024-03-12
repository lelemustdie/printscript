package org.example.lexer.stringDivider

class StringDividerImpl : StringDivider {
    override fun stringToList(input: String): List<List<String>> {
        val lines = input.split("\n")
        val listOfWordLists = mutableListOf<List<String>>()
        for (line in lines) {
            val words = line.split(" ")
            listOfWordLists.add(words)
        }
        return listOfWordLists
    }
}