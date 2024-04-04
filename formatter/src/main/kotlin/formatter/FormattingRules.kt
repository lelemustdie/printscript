package org.example.formatter

data class FormattingRules(
    val numberSpacesBeforeColon: Int,
    val numberSpaceAfterColon: Int,
    val numberSpaceBeforeAssignation: Int,
    val numberSpaceAfterAssignation: Int,
    val numberNewLinesBeforePrint: Int,
)

data class FormattingRulesWrapper(
    val rules: FormattingRules,
)
