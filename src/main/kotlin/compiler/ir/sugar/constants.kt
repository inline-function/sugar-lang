package compiler.ir.sugar
val str_type_ir = TypeIR("Str")
val int_type_ir = TypeIR("Int")
val dec_type_ir = TypeIR("Dec")
val bool_type_ir = TypeIR("Bool")
val unit_type_ir = TypeIR("Unit")
const val NULL : UByte = 0x00U
object IRHead {
    const val FILE : UByte = 0x01U
    const val ANNOTATION : UByte = 0x02U
    const val VARIABLE : UByte = 0x03U
}
object AnnotationValueType {
    const val INT : UByte = 0x01U
    const val DEC : UByte = 0x02U
    const val STR : UByte = 0x03U
}
object Mutable {
    const val MUTABLE : UByte = 0x01U
    const val IMMUTABLE : UByte = 0x02U
}