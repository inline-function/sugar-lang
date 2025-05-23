grammar Sugar;
ELSE : 'else';
VAL : 'val';
VAR : 'var';
FUN : 'fun';
CLASS : 'class';
INT     :   [0-9]+ ;
DEC     :   [0-9]+ '.' [0-9]+ ;
STRING  :   '"' (ESC | ~["\\])* '"' ;
ID      :   ([\p{L}+] | DIGIT | '_' | '$')+ | ('_' | '$' | '`' | '#' | '%' | '&' | '@' | '*' | '+' | '-' | '~' | '/' | ':' | '.' | '<' | '>' | '?' | '\\' | '^' | '|' | '=' | '!' | '\'' | ',')+ ;
WS      :   ([ \t\r\n]+|'//' ~[\r\n]*|'/*' .*? '*/') -> skip ;
fragment LETTER
        :   [a-zA-Z] ;
fragment DIGIT
        :   [0-9] ;
fragment ESC
        :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE
        :   'u' HEX HEX HEX HEX ;
fragment HEX
        :   [0-9a-fA-F] ;

//整个文件
file : (top+ EOF) | EOF;
//注解
//数字字面量
number : INT | DEC;
//顶层语句
top : variable | function | class;
//子程序体
body : '{' stmt* '}';
//泛型列表声明
typeParamList : '<' ID (',' ID)* '>';
//泛型列表
typeArgList : '<' type (',' type)* '>';
//变量声明
variable : ((VAL | VAR) ID '=' expr) | ((VAL | VAR) ID ':' type ('=' expr)?);
//参数声明
parameter : ID ':' type ('=' expr)?;
//函数声明
function : FUN ID typeParamList? '(' (parameter (',' parameter)*)? ')' (':' type)? body?;
//类型声明
class : CLASS ID typeParamList? (':' (type(','type)*))? ('{' (variable|function)* '}')?;
//类型表达式
type : (ID | '(' type ')' | functionType | applyType) nullableType?;
//应用泛型的函数
applyType : ID typeArgList;
//可空类型(右递归式)
nullableType : '?';
//函数类型
functionType : '(' (type (',' type)*)? ')' '=>' type;
//语句
stmt : expr | top;
//表达式
expr : (number | STRING | '(' expr ')' | ID | lambda) name? invoke?;
//匿名函数
lambda : '{' parameter (',' parameter)* '=>' stmt* '}' |
         '{' (ID (',' ID)* '=>')? stmt* '}';
//调用语句(右递归文法)
invoke : typeArgList? '(' (expr (',' expr)*)? ')' name? invoke?;
//名称访问语句(右递归文法)
name : '.' ID name? invoke?;