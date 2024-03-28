


data class FormattingRules(
    val numberSpacesBeforeColon: Int,
    val numberSpaceAfterColon: Int,
    val numberSpaceBeforeAssignation: Int,
    val numberSpaceAfterAssignation: Int,
)

data class FormattingRulesWrapper(
    val rules: FormattingRules
)

