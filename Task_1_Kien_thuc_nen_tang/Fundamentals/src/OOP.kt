// ! ============================================
// ! 1. TÍNH ĐÓNG GÓI (ENCAPSULATION)
// ! ============================================

// * Che giấu trạng thái bên trong của đối tượng và chỉ cho phép truy cập qua các phương thức công khai
// * Các bổ từ truy cập: public (mặc định), private, protected, internal

class BankAccount(val owner: String, private var balance: Double) {

    // ? Getter công khai để xem số dư, nhưng không được sửa trực tiếp từ bên ngoài
    fun getBalance(): Double = balance

    // * Phương thức nạp tiền (có kiểm tra điều kiện an toàn)
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("$owner nạp thành công $$amount. Số dư hiện tại: $$balance")
        } else {
            // ! Cảnh báo: số tiền không hợp lệ
            println("Số tiền không hợp lệ!")
        }
    }

    // * Phương thức rút tiền
    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            println("Rút thành công $$amount. Số dư hiện tại: $$balance")
        } else {
            // ! Cảnh báo: giao dịch thất bại
            println("Rút tiền không thành công!")
        }
    }
}

// ! ============================================
// ! 2. TÍNH KẾ THỪA (INHERITANCE)
// ! ============================================

// * Cho phép một lớp con kế thừa và tái sử dụng mã nguồn của lớp cha
// * Trong Kotlin, mọi class đều mặc định bị khóa (final)
// * Để class khác có thể kế thừa, bắt buộc phải thêm từ khóa "open"

open class Animal(val name: String) {
    // ? Hàm cũng phải có từ khóa 'open' thì lớp con mới có thể ghi đè
    open fun makeSound() {
        println("$name phát ra âm thanh")
    }
}

// Lớp con Chicken kế thừa từ Animal thông qua dấu hai chấm
class Chicken(name: String) : Animal(name) {
    override fun makeSound() {
        println("$name: Cục ta cục tác! 🐔")
    }
}

class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("$name: Gâu gâu! 🐕")
    }
}

// ! ============================================
// ! 3. TÍNH ĐA HÌNH (POLYMORPHISM)
// ! ============================================

// * 1 hành động có thể được thực hiện theo nhiều cách khác nhau
// * Một lời gọi hàm nhưng cách xử lý khác nhau tùy vào đối tượng thực tế

fun playSound(animal: Animal) {
    // ? Truyền vào animal nào thì tự động phát ra tiếng kêu của loại đó
    animal.makeSound()
}

// ! ============================================
// ! 4. TÍNH TRỪU TƯỢNG (ABSTRACTION)
// ! ============================================

// * Tập trung vào "lớp này làm gì" thay vì "làm như thế nào"
// * Che giấu các chi tiết cài đặt phức tạp
// * Sử dụng 'interface' hoặc 'abstract class'

interface Vehicle {
    // ? Hàm trừu tượng không có body
    fun startEngine()

    // * Hàm có implementation mặc định
    fun honk() {
        println("Bíp bíp! 📯")
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

// ! ============================================
// ! 5. HÀM TEST
// ! ============================================

// ? Test tính đóng gói
fun testEncapsulation() {
    println("=== TEST TÍNH ĐÓNG GÓI ===")
    val account = BankAccount("Quzkhanh", 1000.0)
    account.deposit(500.0)
    account.withdraw(300.0)
    println("Số dư cuối cùng: ${account.getBalance()}\n")
}

// ? Test tính kế thừa và đa hình
fun testInheritanceAndPolymorphism() {
    println("=== TEST TÍNH KẾ THỪA & ĐA HÌNH ===")
    val animals = listOf(
        Chicken("Gà con"),
        Dog("Mực"),
        Animal("Động vật")
    )

    animals.forEach { playSound(it) }
    println()
}

// ? Test tính trừu tượng
fun testAbstraction() {
    println("=== TEST TÍNH TRỪU TƯỢNG ===")
    val vehicles = listOf(Car(), Motorbike())

    vehicles.forEach {
        vehicle ->
        vehicle.startEngine()
        vehicle.honk()
    }
    println()
}

// ! ============================================
// ! 6. MAIN - ĐIỂM VÀO CHƯƠNG TRÌNH
// ! ============================================

fun main() {
    testEncapsulation()
    testInheritanceAndPolymorphism()
    testAbstraction()
}