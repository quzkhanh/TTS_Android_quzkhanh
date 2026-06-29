//Generics là gì? (n,adj): chung chung, không có thương hiệu riêng
//* Kiểu đại diện: T
//? Generics cho phép class hoặc function làm việc với nhiều kiểu dữ liệu khác nhau mà vẫn đảm bảo type-safe.
//? Function cũng có thể generic.
/*
//        fun <T> printValue(value: T) {
//            println(value)
//        }
//
//        fun main() {
//            printValue(10)
//            printValue("Hello")
//            printValue(true)
//        }
*/
//! Thay vì chỉ làm việc với Int hoặc String, ta dùng một kiểu đại diện (T).

class Box<T> (val value: T)

fun main() {
    val intBox = Box(100)
    val stringBox = Box("Hello")

    println(intBox.value)
    println(stringBox.value)
}

