package exceptionhandling

//! =======================================================
//! KOTLIN EXCEPTION HANDLING
//! =======================================================
//
// Exception Handling giúp chương trình xử lý lỗi
// thay vì bị crash.
//
// Keywords:
// - try
// - catch
// - finally
// - throw
//
// Common Exceptions:
// - ArithmeticException
// - NumberFormatException
// - NullPointerException
// - IndexOutOfBoundsException
// - IllegalArgumentException
//
// =======================================================


//! =======================================================
//! CUSTOM EXCEPTION
//! =======================================================

class InvalidAgeException(message: String) : Exception(message)


//! =======================================================
//! FUNCTIONS
//! =======================================================

fun divide(a: Int, b: Int): Int {
    return a / b
}

fun parseNumber(text: String): Int {
    return text.toInt()
}

fun validateAge(age: Int) {
    if (age < 18) {
        throw InvalidAgeException("Age must be at least 18.")
    }
}

fun getStudent(index: Int): String {
    val students = listOf("Alice", "Bob", "Charlie")
    return students[index]
}


//! =======================================================
//! MAIN
//! =======================================================

fun main() {

    //! ===================================================
    //! BASIC TRY - CATCH
    //! ===================================================

    println("\n========== TRY CATCH ==========")

    try {
        val result = divide(10, 2)
        println(result)
    } catch (e: Exception) {
        println(e.message)
    }


    //! ===================================================
    //! DIVIDE BY ZERO
    //! ===================================================

    println("\n========== ARITHMETIC EXCEPTION ==========")

    try {
        println(divide(10, 0))
    } catch (e: ArithmeticException) {
        println("Cannot divide by zero.")
    }


    //! ===================================================
    //! NUMBER FORMAT EXCEPTION
    //! ===================================================

    println("\n========== NUMBER FORMAT ==========")

    try {
        println(parseNumber("123"))
        println(parseNumber("abc"))
    } catch (e: NumberFormatException) {
        println("Invalid number.")
    }


    //! ===================================================
    //! INDEX OUT OF BOUNDS
    //! ===================================================

    println("\n========== INDEX OUT OF BOUNDS ==========")

    try {
        println(getStudent(5))
    } catch (e: IndexOutOfBoundsException) {
        println("Index does not exist.")
    }


    //! ===================================================
    //! MULTIPLE CATCH
    //! ===================================================

    println("\n========== MULTIPLE CATCH ==========")

    try {

        val number = "abc".toInt()

        println(10 / number)

    } catch (e: NumberFormatException) {

        println("Invalid number.")

    } catch (e: ArithmeticException) {

        println("Cannot divide by zero.")

    }


    //! ===================================================
    //! FINALLY
    //! ===================================================

    println("\n========== FINALLY ==========")

    try {

        println(divide(10, 0))

    } catch (e: Exception) {

        println("Error occurred.")

    } finally {

        println("Always executed.")

    }


    //! ===================================================
    //! THROW
    //! ===================================================

    println("\n========== THROW ==========")

    try {

        validateAge(16)

    } catch (e: InvalidAgeException) {

        println(e.message)

    }


    //! ===================================================
    //! TRY AS EXPRESSION
    //! ===================================================
    //
    // Kotlin cho phép try trả về giá trị
    //

    println("\n========== TRY AS EXPRESSION ==========")

    val result = try {

        divide(20, 2)

    } catch (e: Exception) {

        0

    }

    println(result)


    //! ===================================================
    //! NULL SAFETY
    //! ===================================================
    //
    // Kotlin ưu tiên tránh Exception bằng Null Safety
    //

    println("\n========== NULL SAFETY ==========")

    val name: String? = null

    println(name?.length)

    println(name ?: "Unknown")


    //! ===================================================
    //! RUN CATCHING
    //! ===================================================
    //
    // Phong cách Kotlin hiện đại
    //

    println("\n========== RUN CATCHING ==========")

    val value = runCatching {

        divide(10, 0)

    }.getOrElse {

        -1

    }

    println(value)


    //! ===================================================
    //! GET OR NULL
    //! ===================================================

    println("\n========== GET OR NULL ==========")

    val number = runCatching {

        parseNumber("abc")

    }.getOrNull()

    println(number)


    //! ===================================================
    //! GET OR DEFAULT
    //! ===================================================

    println("\n========== GET OR DEFAULT ==========")

    val defaultValue = runCatching {

        parseNumber("abc")

    }.getOrDefault(100)

    println(defaultValue)


    //! ===================================================
    //! ON SUCCESS / ON FAILURE
    //! ===================================================

    println("\n========== ON SUCCESS ==========")

    runCatching {

        divide(20, 2)

    }.onSuccess {

        println("Success: $it")

    }.onFailure {

        println(it.message)

    }



    println("\n========== ON FAILURE ==========")

    runCatching {

        divide(20, 0)

    }.onSuccess {

        println(it)

    }.onFailure {

        println("Failure: ${it.message}")

    }

}