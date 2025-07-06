package util

fun runPrompt(rule: String) {
    Runtime.getRuntime().exec(rule)
}

fun shutdownPrompt(second: Int): String {
    return "shutdown -s -t $second"
}

const val PROMPT_SHUTDOWN_SET = "shutdown -s -t 3600"
const val PROMPT_SHUTDOWN_CANCEL = "shutdown -a"