/**
 * SORTING TRONG KOTLIN - CÁC CÁCH SẮP XẾP
 *
 * 1. sort() - Sắp xếp tại chỗ (MutableList/Array)
 * 2. sorted() - Sắp xếp trả về List mới
 * 3. sortBy() - Sắp xếp theo tiêu chí
 * 4. sortWith() - Sắp xếp với Comparator tùy chỉnh
 */

fun main() {

    // ==========================================
    // 1. SẮP XẾP CƠ BẢN
    // ==========================================

    // 1.1. sort() - Sắp xếp tăng dần (thay đổi trực tiếp)
    val numbers = mutableListOf(5, 2, 8, 1, 9, 3)
    numbers.sort()
    println("sort(): $numbers") // [1, 2, 3, 5, 8, 9]

    // 1.2. sortDescending() - Sắp xếp giảm dần
    val numbers2 = mutableListOf(5, 2, 8, 1, 9, 3)
    numbers2.sortDescending()
    println("sortDescending(): $numbers2") // [9, 8, 5, 3, 2, 1]

    // 1.3. sorted() - Trả về List mới (không thay đổi gốc)
    val original = listOf(5, 2, 8, 1, 9, 3)
    val sorted = original.sorted()
    println("original: $original") // [5, 2, 8, 1, 9, 3]
    println("sorted(): $sorted")   // [1, 2, 3, 5, 8, 9]

    // 1.4. sortedDescending() - Trả về List giảm dần
    val sortedDesc = original.sortedDescending()
    println("sortedDescending(): $sortedDesc") // [9, 8, 5, 3, 2, 1]

    // ==========================================
    // 2. SẮP XẾP VỚI OBJECT
    // ==========================================

    data class Person(val name: String, val age: Int)

    val people = mutableListOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 22),
        Person("David", 28)
    )

    // 2.1. sortBy() - Sắp xếp theo 1 thuộc tính
    people.sortBy { it.age }
    println("sortBy age: ${people.map { "${it.name}(${it.age})" }}")
    // [Charlie(22), Alice(25), David(28), Bob(30)]

    // 2.2. sortByDescending() - Sắp xếp giảm dần theo thuộc tính
    people.sortByDescending { it.age }
    println("sortByDescending age: ${people.map { "${it.name}(${it.age})" }}")
    // [Bob(30), David(28), Alice(25), Charlie(22)]

    // 2.3. sortWith() - Sắp xếp với Comparator
    people.sortWith(compareBy { it.name })
    println("sortWith by name: ${people.map { it.name }}")
    // [Alice, Bob, Charlie, David]

    // 2.4. sortWith() - Nhiều tiêu chí
    people.sortWith(compareBy<Person> { it.age }.thenBy { it.name })
    println("sortWith age then name: ${people.map { "${it.name}(${it.age})" }}")
    // [Charlie(22), Alice(25), David(28), Bob(30)]

    // ==========================================
    // 3. SẮP XẾP VỚI STRING
    // ==========================================

    val fruits = mutableListOf("banana", "apple", "cherry", "date", "elderberry")

    // 3.1. Sắp xếp theo alphabet
    fruits.sort()
    println("sort strings: $fruits") // [apple, banana, cherry, date, elderberry]

    // 3.2. Sắp xếp theo độ dài
    fruits.sortBy { it.length }
    println("sort by length: $fruits") // [date, apple, banana, cherry, elderberry]

    // 3.3. Sắp xếp theo chữ cái cuối
    fruits.sortBy { it.last() }
    println("sort by last char: $fruits") // [banana(a), elderberry(y), cherry(y)...]

    // ==========================================
    // 4. SẮP XẾP VỚI ARRAY
    // ==========================================

    val arr = arrayOf(5, 2, 8, 1, 9, 3)

    // 4.1. sort() - Sắp xếp tại chỗ
    arr.sort()
    println("Array sorted: ${arr.joinToString()}") // 1, 2, 3, 5, 8, 9

    // 4.2. sortedArray() - Trả về array mới
    val arr2 = arrayOf(5, 2, 8, 1, 9, 3)
    val sortedArr = arr2.sortedArray()
    println("Original: ${arr2.joinToString()}")     // 5, 2, 8, 1, 9, 3
    println("Sorted: ${sortedArr.joinToString()}")  // 1, 2, 3, 5, 8, 9

    // 4.3. sortedArrayDescending()
    val sortedArrDesc = arr2.sortedArrayDescending()
    println("Sorted desc: ${sortedArrDesc.joinToString()}") // 9, 8, 5, 3, 2, 1

    // ==========================================
    // 5. SẮP XẾP NGƯỢC
    // ==========================================

    val list = mutableListOf(1, 2, 3, 4, 5)
    list.reverse()
    println("reverse(): $list") // [5, 4, 3, 2, 1]

    // ==========================================
    // 6. SẮP XẾP VÀ GIỮ NGUYÊN VỊ TRÍ TƯƠNG ĐỐI
    // ==========================================

    data class Item(val id: Int, val value: String)
    val items = mutableListOf(
        Item(2, "B"),
        Item(1, "A"),
        Item(3, "C"),
        Item(2, "D")
    )

    // sortBy ổn định - giữ thứ tự của các phần tử bằng nhau
    items.sortBy { it.id }
    println("Stable sort: ${items.map { "${it.id}-${it.value}" }}")
    // [1-A, 2-B, 2-D, 3-C] - 2-B vẫn đứng trước 2-D (giữ nguyên thứ tự ban đầu)

    // ==========================================
    // 7. TÓM TẮT NHANH
    // ==========================================

    println("\n========== TÓM TẮT ==========")
    println("""
        | MutableList/Array (sửa trực tiếp):
        |   sort()              -> tăng dần
        |   sortDescending()    -> giảm dần
        |   sortBy { }          -> theo tiêu chí
        |   sortByDescending { }-> theo tiêu chí giảm dần
        |   reverse()           -> đảo ngược
        |
        | List (trả về mới):
        |   sorted()            -> tăng dần
        |   sortedDescending()  -> giảm dần
        |   sortedBy { }        -> theo tiêu chí
        |   sortedByDescending{ }-> theo tiêu chí giảm dần
        |   reversed()          -> đảo ngược
        |
        | Array (trả về mới):
        |   sortedArray()       -> tăng dần
        |   sortedArrayDescending() -> giảm dần
    """.trimMargin())
}