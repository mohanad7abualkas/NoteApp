import java.io.File

fun main() {
    val notesFile = File("notes.txt")

    while (true) {
        println("اختر الإجراء:")
        println("1. إنشاء ملاحظة جديدة")
        println("2. عرض الملاحظات")
        println("3. حذف ملاحظة")
        println("4. تعديل ملاحظة")
        println("5. الخروج")

        val choice = readLine()

        when (choice) {
            "1" -> createNote()
            "2" -> displayNotes()
            "3" -> deleteNote()
            "4" -> editNote()
            "5" -> {
                println("شكراً لاستخدامك التطبيق.")
                return
            }
            else -> println("خيار غير صالح. يرجى المحاولة مرة أخرى.")
        }
    }
}

fun createNote() {
    println("أدخل الملاحظة الجديدة:")
    val note = readLine()

    val notesFile = File("notes.txt")
    notesFile.appendText("$note\n")

    println("تمت إضافة الملاحظة بنجاح.")
}

fun displayNotes() {
    val notesFile = File("notes.txt")
    if (notesFile.exists()) {
        val notes = notesFile.readLines()
        if (notes.isNotEmpty()) {
            println("قائمة الملاحظات:")
            notes.forEachIndexed { index, note ->
                println("${index + 1}. $note")
            }
        } else {
            println("لا توجد ملاحظات.")
        }
    } else {
        println("لا توجد ملاحظات.")
    }
}

fun deleteNote() {
    println("أدخل رقم الملاحظة التي تريد حذفها:")
    val noteNumber = readLine()?.toIntOrNull()

    val notesFile = File("notes.txt")
    val notes = notesFile.readLines().toMutableList()

    if (noteNumber != null && noteNumber in 1..notes.size) {
        val deletedNote = notes.removeAt(noteNumber - 1)
        notesFile.writeText(notes.joinToString("\n"))
        println("تم حذف الملاحظة: $deletedNote")
    } else {
        println("رقم الملاحظة غير صالح.")
    }
}

fun editNote() {
    println("أدخل رقم الملاحظة التي تريد تعديلها:")
    val noteNumber = readLine()?.toIntOrNull()

    val notesFile = File("notes.txt")
    val notes = notesFile.readLines().toMutableList()

    if (noteNumber != null && noteNumber in 1..notes.size) {
        println("أدخل المحتوى الجديد للملاحظة:")
        val newNoteContent = readLine()

        notes[noteNumber - 1] = newNoteContent.orEmpty()
        notesFile.writeText(notes.joinToString("\n"))
        println("تم تعديل الملاحظة بنجاح.")
    } else {
        println("رقم الملاحظة غير صالح.")
    }
}
