package linter

class LinterRules {
    data class LinterRules(
        val idFormat: String,
        val operationInPrintln: Boolean,
    )

    data class LinterRulesWrapper(
        val rules: LinterRules,
    )
}
