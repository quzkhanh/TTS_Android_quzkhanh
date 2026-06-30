package oop
// ! =========================================================================
// ! OOP.KT - TỔNG HỢP CÁC TÍNH CHẤT OOP TRONG KOTLIN
// ! =========================================================================
// * Đây là file tổng hợp kiến thức về Lập trình Hướng đối tượng trong Kotlin
// ? Mục tiêu: Hiểu và áp dụng 4 tính chất + các khái niệm nâng cao

// =========================================================================
// 1. TÍNH ĐÓNG GÓI (ENCAPSULATION)
// =========================================================================
// * Che giấu trạng thái bên trong, chỉ truy cập qua phương thức công khai
// * Các bổ từ truy cập: public, private, protected, internal

class BankAccount(val owner: String, private var balance: Double) {
    // ? Getter công khai để đọc số dư
    fun getBalance(): Double = balance

    // * Kiểm tra điều kiện an toàn khi nạp/rút
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("✅ $owner nạp thành công $$amount. Số dư: $$balance")
        } else {
            // ! Cảnh báo: Số tiền không hợp lệ
            println("❌ Số tiền không hợp lệ!")
        }
    }

    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            println("✅ Rút thành công $$amount. Số dư: $$balance")
        } else {
            // ! Cảnh báo: Giao dịch thất bại
            println("❌ Rút tiền không thành công!")
        }
    }
}

// =========================================================================
// 2. TÍNH KẾ THỪA (INHERITANCE)
// =========================================================================
// * Lớp con kế thừa và tái sử dụng mã nguồn từ lớp cha
// * Mặc định class là final, cần từ khóa 'open' để cho phép kế thừa

open class Animal(val name: String) {
    // ? Cần 'open' để cho phép override
    open fun makeSound() {
        println("🔊 $name phát ra âm thanh")
    }
}

class Chicken(name: String) : Animal(name) {
    override fun makeSound() {
        println("🐔 $name: Cục ta cục tác!")
    }
}

class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("🐕 $name: Gâu gâu!")
    }
}

class Cat(name: String) : Animal(name) {
    override fun makeSound() {
        println("🐱 $name: Meo meo!")
    }
}

// =========================================================================
// 3. TÍNH ĐA HÌNH (POLYMORPHISM)
// =========================================================================
// * Cùng 1 hành động nhưng xử lý khác nhau tùy đối tượng
// * Lợi ích: Code linh hoạt, dễ mở rộng

fun makeAnimalSound(animal: Animal) {
    // ? Truyền animal nào thì phát âm thanh tương ứng
    animal.makeSound()
}

// =========================================================================
// 4. TÍNH TRỪU TƯỢNG (ABSTRACTION)
// =========================================================================
// * Tập trung vào "làm gì" thay vì "làm như thế nào"
// * Interface hoặc Abstract class định nghĩa contract

interface Vehicle {
    // ? Hàm trừu tượng không có implementation
    fun startEngine()

    // * Hàm có implementation mặc định
    fun honk() {
        println("📯 Bíp bíp!")
    }
}

class Car : Vehicle {
    override fun startEngine() {
        println("🚗 Động cơ ô tô đang khởi động...")
    }
}

class Motorbike : Vehicle {
    override fun startEngine() {
        println("🏍️ Động cơ xe máy đang khởi động...")
    }
}

// =========================================================================
// 5. LỚP VÀ ĐỐI TƯỢNG (CLASSES & OBJECTS)
// =========================================================================

// !--- 5.1. Class cơ bản (Primary Constructor) ---
// * Định nghĩa thuộc tính ngay trong hàm khởi tạo
class Person(val name: String, var age: Int) {
    // * Khối init chạy ngay khi tạo object
    init {
        println("🆕 Đối tượng Person mới: $name")
    }

    // * Phương thức của class
    fun introduce() {
        println("👋 Xin chào, mình là $name, $age tuổi.")
    }

    // ? Hàm hỗ trợ in thông tin
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

// !--- 5.2. Data Class ---
// * Tự động sinh các hàm: toString(), equals(), hashCode(), copy()
// * Thường dùng làm Model/DTO
data class User(
    val id: Int,
    val username: String,
    val email: String
) {
    // ? Có thể thêm hàm mở rộng nếu cần
    fun displayInfo() = "User #$id: $username ($email)"
}

// !--- 5.3. Object (Singleton Pattern) ---
// * Tự động tạo 1 instance duy nhất của class
// * Thường dùng cho: Config, Database Connection, Logger
object AppConfig {
    const val DATABASE_NAME = "MyAndroidAppDB"
    const val MAX_LOGIN_RETRIES = 5
    private const val API_BASE_URL = "https://api.example.com"

    fun showConfig() {
        println("📋 Configuration:")
        println("  - Database: $DATABASE_NAME")
        println("  - Max Retries: $MAX_LOGIN_RETRIES")
        println("  - API URL: $API_BASE_URL")
    }
}

// =========================================================================
// 6. HÀM TEST TỔNG HỢP
// =========================================================================
// * Mỗi tính chất có hàm test riêng để dễ kiểm tra

fun testEncapsulation() {
    println("\n" + "=".repeat(60))
    println("🧪 TEST: TÍNH ĐÓNG GÓI")
    println("=".repeat(60))

    val account = BankAccount("Quzkhanh", 1000.0)
    account.deposit(500.0)
    account.withdraw(300.0)
    println("💰 Số dư cuối: ${account.getBalance()}")
}

fun testInheritanceAndPolymorphism() {
    println("\n" + "=".repeat(60))
    println("🧪 TEST: KẾ THỪA & ĐA HÌNH")
    println("=".repeat(60))

    val animals = listOf(
        Chicken("Gà con"),
        Dog("Mực"),
        Cat("Tom")
    )

    // * Đa hình: cùng hàm nhưng hành vi khác nhau
    animals.forEach { makeAnimalSound(it) }
}

fun testAbstraction() {
    println("\n" + "=".repeat(60))
    println("🧪 TEST: TRỪU TƯỢNG")
    println("=".repeat(60))

    val vehicles = listOf(Car(), Motorbike())
    vehicles.forEach {
        it.startEngine()
        it.honk()
    }
}

fun testClassesAndObjects() {
    println("\n" + "=".repeat(60))
    println("🧪 TEST: LỚP & ĐỐI TƯỢNG")
    println("=".repeat(60))

    // Class thông thường
    val person = Person("Khánh", 22)
    person.introduce()
    person.age = 23
    println("📝 Sau cập nhật: $person")

    // Data Class
    val user1 = User(1, "quzkhanh", "khanh@gmail.com")
    println("📧 User: $user1")
    val user2 = user1.copy(email = "khanhnew@gmail.com")
    println("📧 Copy và chỉnh sửa: $user2")
    println("ℹ️ Info: ${user2.displayInfo()}")

    // Object (Singleton)
    AppConfig.showConfig()
}

// =========================================================================
// 7. HÀM MAIN - ĐIỂM VÀO CHƯƠNG TRÌNH
// =========================================================================

fun main() {
    // ? Chạy tất cả test hoặc comment để chạy từng phần
    testEncapsulation()
    testInheritanceAndPolymorphism()
    testAbstraction()
    testClassesAndObjects()

    // * Kết luận
    println("\n" + "=".repeat(60))
    println("✅ HOÀN THÀNH TẤT CẢ TEST!")
    println("=".repeat(60))
}