grammar Sugar;
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
IF : 'if';
ELSE : 'else';
file : top* EOF;
number : INT | DEC;
top : variable | function | class;
body : '{' stmt* '}';
variable : ID '=' expr | ID ':' type ('=' expr)?;
function : ID '(' (variable (',' variable)*)? ')' (':' type)? body?;
class : ID ('|>' type)+ '{' (variable|function)* '}';
type : ID | '(' type ')';
stmt : expr | top;
expr : (number | STRING | name | '(' expr ')' | if) invoke_?;
if : IF expr body (ELSE body)?;
invoke_ : '(' (expr (',' expr)*)? ')' invoke_?;
name : ID ('.' ID)*?;