; Fibonacci.ll - LLVM IR 示例：计算斐波那契数列并打印结果
; ==========================================================

; 声明外部函数：printf，参数为格式化字符串和可变参数，返回i32
declare i32 @printf(i8*, ...)

; 定义函数：计算斐波那契数
; 参数：i32类型的n
define i32 @fib(i32 %n) {
    ; 基本块：entry（入口）
    %entry = block

    ; 条件判断：n <= 1时返回n
    %cond = icmp sle i32 %n, 1
    br i1 %cond, label %then, label %else

    ; 基本块：then（n <= 1时执行）
    %then = block
    ret i32 %n

    ; 基本块：else（n > 1时执行）
    %else = block
    ; 递归计算fib(n-1)和fib(n-2)
    %n1 = sub i32 %n, 1
    %n2 = sub i32 %n, 2
    %f1 = call i32 @fib(i32 %n1)
    %f2 = call i32 @fib(i32 %n2)
    ; 计算fib(n-1) + fib(n-2)
    %sum = add i32 %f1, %f2
    ret i32 %sum
}

; 主函数
define i32 @main() {
    ; 打印提示信息
    %fmt = getelementptr inbounds [40 x i8], [40 x i8]* @.str, i64 0, i64 0
    call i32 @printf(i8* %fmt)

    ; 计算斐波那契数n=10
    %n = add i32 0, 10
    %result = call i32 @fib(i32 %n)

    ; 打印结果
    %fmt2 = getelementptr inbounds [30 x i8], [30 x i8]* @.str2, i64 0, i64 0
    call i32 @printf(i8* %fmt2, i32 %result)

    ; 返回0
    ret i32 0
}

; 字符串常量
@.str = constant [40 x i8] c"计算斐波那契数列第10项的值...\0A\00"
@.str2 = constant [30 x i8] c"斐波那契(10) = %d\0A\00"