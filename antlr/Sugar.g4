grammar Sugar;
ELSE : 'else';
VAR : 'var';
FUN : 'fun';
CLASS : 'class';
GET : 'get';
SET : 'set';
NL : ('\n' | '\r' | '\r\n')+;
INT     :   [0-9]+ ;
DEC     :   [0-9]+ '.' [0-9]+ ;
STRING  :   '"' (ESC | ~["\\])* '"' ;
ID      :   ([\p{L}+] | DIGIT | '_' | '$')+ | ('_' | '$' | '`' | '#' | '%' | '&' | '@' | '*' | '+' | '-' | '~' | '/' | ':' | '.' | '<' | '>' | '?' | '\\' | '^' | '|' | '=' | '!' | '\'' | ',')+ ;
WS      :   (([ \t]+) | ('//' (('\n' | '\r' | '\r\n') | EOF) )| ('/*' .*? '*/')) -> skip ;
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
file : (NL? top (NL top)* NL? EOF) | (NL? EOF);
//注解
annotation : '@' ID ( '(' NL? expr NL? ')' )?;
//修饰符
modifier : (NL? annotation)* NL? ID*;
//数字字面量
number : INT | DEC;
//顶层语句
top : variable | function | class;
//子程序体
body : ('{' NL? stmt (NL stmt)* NL? '}') | ('{' NL? '}');
//泛型列表声明
typeParamList : '<' NL? typeParam ( NL? ',' NL? typeParam)* NL? '>';
//类型参数
typeParam : modifier? ID (':' type)?;
//上文列表声明
aboveList : '[' NL? type ( NL? ',' NL? type)* NL? ']';
//下文列表声明
belowList : '{' NL? type ( NL? ',' NL? type)* NL? '}';
//接收者
receiver : type '.';
//修改器
setter : modifier? SET typeParamList? aboveList? belowList? ((NL? '=' NL? expr) | (NL? body));
//获取器
getter : modifier? GET typeParamList? aboveList? belowList? ((NL? '=' NL? expr) | (NL? body));
//泛型列表
typeArgList : '<' NL? type ( NL? ',' NL? type)* '>';
//变量声明
variable :modifier? VAR receiver? ID typeParamList? aboveList? belowList?
          ( ( NL? '=' NL? expr) | ( ':' type (NL? '=' NL? expr)?) )
          NL? getter? NL? setter?;
//参数声明
parameter : NL? modifier? NL? ID ':' type (NL? '=' NL? expr)?;
//函数声明
function : modifier? FUN ID typeParamList? aboveList? belowList? '('NL? (NL? parameter (NL? ',' NL? parameter)*)? NL? ')' (':' type)? body?;
//类型声明
class : modifier? CLASS ID typeParamList? (NL? ':' (NL? type(NL? ',' NL? type)*))? ('{'NL? (variable|function) (NL variable|function)* NL? '}')?;
//类型表达式
type : modifier? (( '(' NL? type NL? ')' | commonType | functionType | applyType | tupleType) nullableType?);
//一般类型
commonType : modifier? ID;
//应用泛型的函数
applyType : modifier? ID typeArgList;
//可空类型(右递归式)
nullableType : '?';
//函数类型
functionType : modifier? '(' (NL? type (NL? ',' NL? type)*)? ')' '=>' NL? type;
//元组类型
tupleType : modifier? '(' NL? type ( NL? ',' NL? type)* ')';
//语句
stmt :  expr | top ;
//表达式
expr : (number | STRING | '(' NL? expr NL? ')' | ID | lambda ) name? invoke? assign?;
//赋值语句(右递归文法)
assign : NL? '=' NL? expr;
//匿名函数
lambda : '{' NL? parameter (NL? ',' NL? parameter)* NL? '=>' NL? stmt? (NL stmt)* NL? '}' |
         '{' NL? ( NL? ID (NL? ',' NL? ID)* NL? '=>')? NL? stmt? (NL stmt)* NL? '}';
//调用语句(右递归文法)
invoke : typeArgList? '(' NL? (NL? expr (NL? ',' NL? expr)*)? NL? ')' invoke? name? assign?;
//名称访问语句(右递归文法)
name : NL? '.' ID name? invoke? assign?;