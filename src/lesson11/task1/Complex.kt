@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    val splitS = s.split("+", "-").filter { it.isNotEmpty() }
    val x = splitS[0].toDouble()
    val y = splitS[1].split("i")[0].toDouble()
    return if (s[0] == '-') {
        val sWithOutFirst = s.drop(1)
        if (sWithOutFirst.contains("-")) Complex(-x, -y)
        else Complex(-x, y)
    } else if (s.contains("-")) Complex(x, -y)
    else Complex(x, y)
}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(re * other.re - im * other.im, other.re * im + re * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) /
                (other.re * other.re + other.im * other.im), (im * other.re - re * other.im) /
                (other.re * other.re + other.im * other.im)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && re == other.re && im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        var complexString = ""
        complexString = if (im > 0.0) "$re+$im" + "i"
        else
            if (im < 0.0) "$re$im" + "i"
            else "$re"
        return complexString
    }
}
