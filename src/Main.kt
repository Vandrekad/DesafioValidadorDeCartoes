/**
 * Identifica a bandeira de um cartão de crédito com base no número fornecido.
 *
 * @param cardNumber O número do cartão de crédito, podendo conter espaços.
 * @return Uma string representando a bandeira do cartão ou "Bandeira desconhecida" se não for reconhecida.
 */
fun identifyCardBrand(cardNumber: String): String {
    val number = cardNumber.replace(" ", "")
    return when {
        // Mastercard: começa com 51-55, 16 dígitos
        number.matches(Regex("5[1-5][0-9]{14}")) -> "Mastercard"
        // Visa: começa com 4, 16 dígitos
        number.matches(Regex("4[0-9]{15}")) -> "Visa"
        // American Express: começa com 34 ou 37, 15 dígitos
        number.matches(Regex("3[47][0-9]{13}")) -> "American Express"
        // Diners Club: começa com 300-305, 14 dígitos
        number.matches(Regex("30[0-5][0-9]{11}")) -> "Diners Club"
        // Discover: começa com 6011, 16 dígitos
        number.matches(Regex("6011[0-9]{12}")) -> "Discover"
        // EnRoute: começa com 2014 ou 2149, 15 dígitos
        number.matches(Regex("(2014|2149)[0-9]{11,12}")) -> "EnRoute"
        // JCB: começa com 35, 16 dígitos
        number.matches(Regex("35[0-9]{14}")) -> "JCB"
        // Voyager: começa com 8699, 15 dígitos
        number.matches(Regex("8699[0-9]{11}")) -> "Voyager"
        // HiperCard: começa com 6062, 16 dígitos
        number.matches(Regex("6062[0-9]{12}")) -> "HiperCard"
        // Aura: começa com 50, 16 dígitos
        number.matches(Regex("50[0-9]{14}")) -> "Aura"
        else -> "Bandeira desconhecida"
    }
}

/**
 * Verifica se um número de cartão de crédito é válido usando o algoritmo de Luhn.
 *
 * @param cardNumber O número do cartão de crédito, podendo conter espaços.
 * @return `true` se o número do cartão for válido, caso contrário `false`.
 */
fun isValidCardNumber(cardNumber: String): Boolean {
    val number = cardNumber.replace(" ", "")
    if (!number.all { it.isDigit() }) return false
    var sum = 0
    var alternate = false
    for (i in number.length - 1 downTo 0) {
        var n = number[i].toString().toInt()
        if (alternate) {
            n *= 2
            if (n > 9) n -= 9
        }
        sum += n
        alternate = !alternate
    }
    return sum % 10 == 0
}

/**
 * Função principal que testa a validação e identificação de bandeiras de cartões de crédito.
 *
 * Exibe os resultados no console para uma lista de números de cartões.
 */
fun main() {
    val cards = listOf(
        "5232 7661 6131 6539", // Mastercard (válido)
        "4556 0130 5031 2704", // Visa (válido)
        "3717 926525 53960",   // American Express (válido)
        "3004 219620 0535",    // Diners Club (válido)
        "6011 3545 3984 7215", // Discover (válido)
        "2149 9150468 3907",   // EnRoute (válido)
        "3566 3656 5462 7832", // JCB (válido)
        "86996 1472 21833 1",  // Voyager (válido)
        "6062 8299 6943 9003", // HiperCard (válido)
        "5079 8669 8887 2569", // Aura (válido)
        // Números inválidos
        "1234 5678 9012 3456", // Inválido
        "4111 1111 1111 1112", // Inválido (Visa, mas dígito errado)
        "5555 5555 5555 4440", // Inválido (Mastercard, mas dígito errado)
        "6011 0009 9013 9425", // Inválido (Discover, mas dígito errado)
        "3000 0000 0000 05"    // Inválido (Diners Club, mas dígito errado)
    )
    for (card in cards) {
        val isValid = isValidCardNumber(card)
        val brand = identifyCardBrand(card)
        if (isValid && brand != "Bandeira desconhecida") {
            println("Cartão: $card => Bandeira: $brand | Válido: $isValid")
        } else if (!isValid) {
            println("Cartão: $card => Válido: $isValid")
        } else {
            println("Cartão: $card => Bandeira: desconhecida | Válido: $isValid")
        }
    }
}