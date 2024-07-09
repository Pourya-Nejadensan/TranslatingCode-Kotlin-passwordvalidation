import java.security.SecureRandom

object ValidationPassword {

    private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:',.<>?/"

    @JvmStatic
    fun generateRandomSecurePassword(): String {
        val random = SecureRandom()
        val sb = StringBuilder(8)

        do {
            sb.setLength(0)
            for (i in 0 until 8) {
                sb.append(CHARACTERS[random.nextInt(CHARACTERS.length)])
            }
        } while (!isPasswordSecure(sb.toString()))

        return sb.toString()
    }

    private fun isPasswordSecure(password: String): Boolean {
        return isPasswordEightChar(password) &&
                hasPasswordDigit(password) &&
                hasPasswordUpperAndLowerLetter(password) &&
                hasPasswordSpecialChar(password)
    }

    @JvmStatic
    fun isPasswordEightChar(password: String): Boolean {
        return password.length >= 8
    }

    @JvmStatic
    fun hasPasswordDigit(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    @JvmStatic
    fun hasPasswordUpperAndLowerLetter(password: String): Boolean {
        return hasUpperCase(password) && hasLowerCase(password)
    }

    private fun hasLowerCase(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    private fun hasUpperCase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    @JvmStatic
    fun isPasswordCommon(password: String, commonPasswords: Array<String>): Boolean {
        return commonPasswords.contains(password)
    }

    @JvmStatic
    fun hasPasswordSpecialChar(password: String): Boolean {
        return password.any { !it.isLetterOrDigit() }
    }
}
