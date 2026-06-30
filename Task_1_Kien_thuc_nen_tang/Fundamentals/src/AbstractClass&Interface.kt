// =====================
// Abstract Class
// =====================

abstract class Person(
    val name: String,
    val age: Int
) {

    fun introduce() {
        println("Hi, my name is $name. I'm $age years old.")
    }

    abstract fun work()
}

// =====================
// Interfaces
// =====================

interface Study {
    fun study()
}

interface Teach {
    fun teach()
}

// =====================
// Student
// =====================

class Student(
    name: String,
    age: Int
) : Person(name, age), Study {

    override fun work() {
        println("$name is doing homework.")
    }

    override fun study() {
        println("$name is studying Kotlin.")
    }
}

// =====================
// Teacher
// =====================

class Teacher(
    name: String,
    age: Int
) : Person(name, age), Teach {

    override fun work() {
        println("$name is preparing lessons.")
    }

    override fun teach() {
        println("$name is teaching Kotlin.")
    }
}

// =====================
// Main
// =====================

fun main() {

    val student = Student("Alice", 20)
    val teacher = Teacher("Bob", 35)

    student.introduce()
    student.study()
    student.work()

    println()

    teacher.introduce()
    teacher.teach()
    teacher.work()
}