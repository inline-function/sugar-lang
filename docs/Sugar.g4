grammar Sugar;
ELSE : 'else';
VAL : 'val';
VAR : 'var';
FUN : 'fun';
CLASS : 'class';
NL : '\n'+;
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
file : NL? top (NL top)* EOF NL?;
//注解
annotation : '@' ID ( '(' NL? expr NL? ')' )?;
//修饰符
modifier : (NL? annotation)* NL? ID*;
//数字字面量
number : INT | DEC;
//顶层语句
top : variable | function | class;
//子程序体
body : '{' NL? stmt (NL stmt)* NL? '}';
//泛型列表声明
typeParamList : '<' NL? ID ( NL? ',' NL? ID)* '>';
//泛型列表
typeArgList : '<' NL? type ( NL? ',' NL? type)* '>';
//变量声明
variable :(modifier? (VAL | VAR) ID NL? '=' NL? expr) |
          (modifier? (VAL | VAR) ID ':' type (NL? '=' NL? expr)?);
//参数声明
parameter : NL? ID ':' type (NL? '=' NL? expr)?;
//函数声明
function : modifier? FUN ID typeParamList? '('NL? (NL? parameter (NL? ',' NL? parameter)*)? NL? ')' (':' type)? NL? body?;
//类型声明
class : modifier? CLASS ID typeParamList? (NL? ':' (NL? type(NL? ',' NL? type)*))? ('{'NL? (variable|function) (NL variable|function)* NL? '}')?;
//类型表达式
type : (ID | '(' NL? type NL? ')' | functionType | applyType) nullableType?;
//应用泛型的函数
applyType : ID typeArgList;
//可空类型(右递归式)
nullableType : '?';
//函数类型
functionType : '(' (NL? type (NL? ',' NL? type)*)? ')' '=>' NL? type;
//语句
stmt :  expr | top ;
//表达式
expr : (number | STRING | '(' NL? expr NL? ')' | ID | lambda) name? invoke?;
//匿名函数
lambda : '{' NL? parameter (NL? ',' NL? parameter)* NL? '=>' NL? stmt? (NL stmt)* NL? '}' |
         '{' NL? ( NL? ID (NL? ',' NL? ID)* NL? '=>')? NL? stmt? (NL stmt)* NL? '}';
//调用语句(右递归文法)
invoke : typeArgList? '(' NL? (NL? expr (NL? ',' NL? expr)*)? NL? ')' invoke? name?;
//名称访问语句(右递归文法)
name : NL? '.' ID name? invoke?;