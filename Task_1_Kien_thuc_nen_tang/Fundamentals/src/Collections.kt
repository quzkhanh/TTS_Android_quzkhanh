package collections

//! =======================================================
//! KOTLIN COLLECTIONS & GENERICS
//! =======================================================
//
// List          : Ordered, duplicates allowed
// MutableList   : Can add/remove/update
// Set           : Unique elements
// MutableSet    : Mutable Set
// Map           : Key -> Value
// MutableMap    : Mutable Map
//
// Common Operations:
// map
// filter
// find
// any
// all
// count
// sorted
// sortedBy
// groupBy
// associateBy
// first
// last
// sum
// reduce
// fold
//
// =======================================================


//! =======================================================
//! DATA CLASS
//! =======================================================

data class Student(
    val id: Int,
    val name: String,
    val age: Int
)


//! =======================================================
//! GENERIC CLASS
//! =======================================================

class Repository<T> {

    private val items = mutableListOf<T>()

    fun add(item: T) {
        items.add(item)
    }

    fun getAll(): List<T> {
        return items
    }
}


//! =======================================================
//! GENERIC FUNCTION
//! =======================================================

fun <T> printValue(value: T) {
    println(value)
}


//! =======================================================
//! MAIN
//! =======================================================

fun main() {

    //! ===================================================
    //! LIST
    //! ===================================================

    println("\n========== LIST ==========")

    val numbers = listOf(1, 2, 3, 2, 5)

    println(numbers)



    //! ===================================================
    //! MUTABLE LIST
    //! ===================================================

    println("\n========== MUTABLE LIST ==========")

    val mutableNumbers = mutableListOf(1, 2, 3)

    mutableNumbers.add(4)

    mutableNumbers.remove(2)      // Remove by value

    mutableNumbers[0] = 100

    println(mutableNumbers)



    //! ===================================================
    //! SET
    //! ===================================================

    println("\n========== SET ==========")

    val set = setOf(1, 2, 3, 3, 4, 5)

    println(set)



    //! ===================================================
    //! MUTABLE SET
    //! ===================================================

    println("\n========== MUTABLE SET ==========")

    val characters = mutableSetOf("A", "B")

    characters.add("C")

    characters.add("A")

    characters.remove("B")

    println(characters)



    //! ===================================================
    //! MAP
    //! ===================================================

    println("\n========== MAP ==========")

    val studentMap = mapOf<String,Any>(
        "name" to "quzkhanh",
        "age" to 22
    )

    println(studentMap)

    println(studentMap["name"])



    //! ===================================================
    //! MUTABLE MAP
    //! ===================================================

    println("\n========== MUTABLE MAP ==========")

    val scores = mutableMapOf<String, Int>()

    scores["Math"] = 10

    scores["English"] = 9

    scores["Math"] = 8

    println(scores)



    //! ===================================================
    //! GENERIC
    //! ===================================================

    println("\n========== GENERIC ==========")

    printValue(100)

    printValue("Hello Kotlin")

    printValue(true)



    //! ===================================================
    //! GENERIC REPOSITORY
    //! ===================================================

    println("\n========== GENERIC REPOSITORY ==========")

    val repository = Repository<Student>()

    repository.add(Student(1, "Alice", 22))
    repository.add(Student(2, "Bob", 18))
    repository.add(Student(3, "Charlie", 20))

    println(repository.getAll())



    val students = repository.getAll()



    //! ===================================================
    //! FILTER
    //! ===================================================

    println("\n========== FILTER ==========")

    val adults = students.filter {
        it.age >= 20
    }

    println(adults)



    //! ===================================================
    //! MAP
    //! ===================================================

    println("\n========== MAP ==========")

    val names = students.map {
        it.name
    }

    println(names)



    //! ===================================================
    //! FIND
    //! ===================================================

    println("\n========== FIND ==========")

    val bob = students.find {
        it.name == "Bob"
    }

    println(bob)



    //! ===================================================
    //! ANY
    //! ===================================================

    println("\n========== ANY ==========")

    println(
        students.any {
            it.age > 25
        }
    )



    //! ===================================================
    //! ALL
    //! ===================================================

    println("\n========== ALL ==========")

    println(
        students.all {
            it.age >= 18
        }
    )



    //! ===================================================
    //! COUNT
    //! ===================================================

    println("\n========== COUNT ==========")

    println(
        students.count {
            it.age >= 20
        }
    )



    //! ===================================================
    //! SORTED
    //! ===================================================

    println("\n========== SORTED ==========")

    val sortedNumbers = listOf(5, 1, 4, 3, 2)

    println(sortedNumbers.sorted())



    //! ===================================================
    //! SORTED BY
    //! ===================================================

    println("\n========== SORTED BY ==========")

    println(
        students.sortedBy {
            it.age
        }
    )



    //! ===================================================
    //! GROUP BY
    //! ===================================================

    println("\n========== GROUP BY ==========")

    println(
        students.groupBy {
            it.age
        }
    )



    //! ===================================================
    //! ASSOCIATE BY
    //! ===================================================

    println("\n========== ASSOCIATE BY ==========")

    println(
        students.associateBy {
            it.id
        }
    )



    //! ===================================================
    //! FIRST / LAST
    //! ===================================================

    println("\n========== FIRST / LAST ==========")

    println(numbers.first())

    println(numbers.last())



    //! ===================================================
    //! SUM
    //! ===================================================

    println("\n========== SUM ==========")

    println(numbers.sum())



    //! ===================================================
    //! REDUCE
    //! ===================================================
    //
    // Combine all elements into one value.
    // First element is used as accumulator.
    //

    println("\n========== REDUCE ==========")

    val total = numbers.reduce { acc, value ->
        acc + value
    }

    println(total)



    //! ===================================================
    //! FOLD
    //! ===================================================
    //
    // Same as reduce but allows initial value.
    //

    println("\n========== FOLD ==========")

    val totalWithInitial = numbers.fold(100) { acc, value ->
        acc + value
    }

    println(totalWithInitial)



    //! ===================================================
    //! FOREACH
    //! ===================================================

    println("\n========== FOREACH ==========")

    students.forEach {
        println(it)
    }



    //! ===================================================
    //! FOREACH INDEXED
    //! ===================================================

    println("\n========== FOREACH INDEXED ==========")

    students.forEachIndexed { index, student ->
        println("$index -> $student")
    }



    //! ===================================================
    //! CHAIN OPERATIONS
    //! ===================================================
    //
    // Real project style
    //

    println("\n========== CHAIN ==========")

    val result = students
        .filter { it.age >= 20 }
        .sortedBy { it.name }
        .map { it.name.uppercase() }

    println(result)
}

/*
Collections
│
├── List
├── MutableList
├── Set
├── MutableSet
├── Map
├── MutableMap
│
├── forEach
├── forEachIndexed
│
├── map
├── filter
├── find
├── any
├── all
├── count
│
├── sorted
├── sortedBy
│
├── groupBy
├── associateBy
│
├── first
├── last
├── sum
│
├── reduce
├── fold
│
└── Chain Operations (rất quan trọng)
 */