grammar Hsl;

// Lexer rules (Tokens)

NEWLINE             : [\n]+ -> skip;
WHITE_SPACE         : [ \t\r]+ -> skip;
COMMENT             : '//' ~[\r\n]* -> skip;
COLON               : ':';
BINARY_OPERATOR     : '+' | '-' | '*' | '/' | '&&' | '||' | '=' | '<>' | '<=' | '>=';
UNARY_OPERATOR      : '-' | '!';
SEMICOLON           : ';';
LBRACE              : '{';
RBRACE              : '}';
LPAREN              : '(';
RPAREN              : ')';
PERIOD              : '.';
LSQUARE             : '[';
RSQUARE             : ']';
COMMA               : ',';
AMPERSAND           : '&';
NUMBER_LEX          : '0x' [0-9a-fA-F]+ | [0-9]+ ('.' [0-9]+)?;
HEX_LEX             : '('?'0x'[0-9]+')'?;
STRING_LEX          : '"' (~["\\])* '"';
CSTRING_LEX         : '"' (~["\\] | '\\' .)* '"';
CRLF                : '\r\n';
DEBUG               : 'debug';
ECHO                : 'echo';
INCLUDE             : '#include';
DEFINE              : '#define';
IFDEF               : '#ifdef';
IFNDEF              : '#ifndef';
ENDIF               : '#endif';
PRAGMA              : 'pragma';
ONCE                : 'once';
GLOBAL              : 'global';
BREAK               : 'break';
RETURN              : 'return';
ABORT               : 'abort';
PAUSE               : 'pause';
ONERROR             : 'onerror';
GOTO                : 'goto';
RESUME              : 'resume';
NEXT                : 'next';
LOCK                : 'lock';
UNLOCK              : 'unlock';
SYNCHRONIZED        : 'synchronized';
PRIVATE             : 'private';
STATIC              : 'static';
CONST               : 'const';
VARIABLE            : 'variable';
SEQUENCE            : 'sequence';
STRING              : 'string';
DEVICE              : 'device';
RESOURCE            : 'resource';
DIALOG              : 'dialog';
OBJECT              : 'object';
TIMER               : 'timer';
EVENT               : 'event';
FILE                : 'file';
FUNCTION            : 'function';
METHOD              : 'method';
STRUCT              : 'struct';
CHAR                : 'char';
SHORT               : 'short';
LONG                : 'long';
FLOAT               : 'float';
VOID                : 'void';
IF                  : 'if';
ELSE                : 'else';
WHILE               : 'while';
FOR                 : 'for';
LOOP                : 'loop';
NAMESPACE           : 'namespace';
ID_LEX              : [a-zA-Z_][a-zA-Z0-9_]*;


// Parser Rules

hslFile
    : statementList EOF
    ;

statementList
    : (statement | controlLine)*
    ;

controlLine
    : DEBUG '='  NUMBER_LEX SEMICOLON
    | ECHO  '='  NUMBER_LEX SEMICOLON
    | INCLUDE  cString SEMICOLON
    | DEFINE  id  constant SEMICOLON
    | IFDEF  id SEMICOLON
    | IFNDEF  id SEMICOLON
    | ENDIF SEMICOLON
    | PRAGMA  (ONCE | GLOBAL) SEMICOLON
    ;

statement
    : simpleStatement SEMICOLON
    | compoundStatement
    | flowControlStatement SEMICOLON
    | controlStatement SEMICOLON
    | errorHandler
    ;

simpleStatement
    : assignmentExpression
    | sequenceExpression
    | stringExpression
    | deviceExpression
    | resourceExpression
    | dialogExpression
    | objectExpression
    | arrayExpression
    | timerExpression
    | eventExpression
    | fileExpression
    | expression
    | declaration
    ;

compoundStatement
    : ifStatement
    | iterationStatement
    | namespaceDefinition
    | functionDefinition
    | block
    ;

block
    : LBRACE statementList RBRACE
    ;

flowControlStatement
    : BREAK
    | RETURN LPAREN expression RPAREN
    | RETURN
    | ABORT
    | PAUSE
    | ONERROR GOTO id
    | ONERROR GOTO '0'
    | ONERROR RESUME NEXT
    | RESUME NEXT
    | LOCK
    | UNLOCK
    ;

controlStatement
    : '<<'  cString
    | '<<'  stringId
    ;

functionDefinition
    : (STATIC | CONST | GLOBAL | PRIVATE)* FUNCTION id formalList? returnType? block
    ;

formalList
    : LPAREN (parameter (COMMA parameter)*)? RPAREN
    ;

declaration
    : declSpecifiers? structure
    | declSpecifiers? array
    | declSpecifiers? functionDefinition
    | declSpecifiers? type AMPERSAND? id COMMA? LPAREN? NUMBER_LEX? RPAREN? declaration? returnType?
    ;

declSpecifiers
    : (declSpecifier)*
    ;

namespaceDefinition
    : namespaceId block
    ;

structure
    : STRUCT  id  LBRACE tagDeclarationList SEMICOLON RBRACE
    | STRUCT  LBRACE tagDeclarationList SEMICOLON RBRACE  id
    | STRUCT  id LBRACE tagDeclarationList SEMICOLON RBRACE  id
    ;

array
    : varDec  LSQUARE expression RSQUARE
    ;

tagDeclarationList
    : (tagDeclaration SEMICOLON)* tagDeclaration
    ;

tagDeclaration
    : varDec
    | varDec  LSQUARE NUMBER_LEX RSQUARE
    ;

varDec
    : storage  id
    ;

storage
    : CHAR
    | SHORT
    | LONG
    | FLOAT
    ;

returnType
    : type
    | VOID
    ;

errorHandler
    : id COLON block
    ;

assignmentExpression
    : id  '='  STRING_LEX '+'? STRING_LEX?
    | id  '='  NUMBER_LEX
    | id  '='  simpleStatement
    ;

sequenceExpression
    : sequenceId '=' sequenceExpression
    | sequenceId '++'
    | sequenceId '--'
    ;

stringExpression
    : stringId '=' stringExpression
    | stringId '=' functionReference
    ;

deviceExpression
    : deviceId '=' deviceExpression
    | deviceId
    ;

resourceExpression
    : resourceId
    ;

dialogExpression
    : dialogId
    ;

objectExpression
    : objectId '=' objectExpression
    | objectId PERIOD id '=' expression
    ;

arrayExpression
    : arrayId LSQUARE expression RSQUARE
    | arrayId '=' arrayId
    ;

timerExpression
    : timerId '=' timerExpression
    | timerId
    ;

eventExpression
    : eventId '=' eventExpression
    | eventId
    ;

fileExpression
    : fileId
    ;

functionCall
    : id LPAREN argumentList? RPAREN
    ;

expression
    : leftExpr BINARY_OPERATOR rightExpr
    | UNARY_OPERATOR expression
    | functionCall
    | atom
    ;

argumentList
    : expression (COMMA expression)*
    ;

leftExpr
    : atom
    ;

rightExpr
    : atom
    ;

atom
    : id
    | constant
    | cString
    | LPAREN expression RPAREN
    | functionCall
    ;

ifStatement
    : IF LPAREN expression RPAREN statement (ELSE statement)?
    ;

iterationStatement
    : WHILE LPAREN expression RPAREN statement
    | FOR LPAREN optInitExpression SEMICOLON optExpression SEMICOLON optForExpression RPAREN statement
    ;

optInitExpression
    : simpleStatement?
    ;

optExpression
    : expression?
    ;

optForExpression
    : simpleStatement?
    ;

functionReference
    : funcId LPAREN bindings RPAREN
    ;

bindings
    : (simpleStatement COMMA)* simpleStatement
    ;

sequenceId
    : id PERIOD id
    ;

deviceId
    : id
    ;

resourceId
    : id
    ;

dialogId
    : id
    ;

objectId
    : id
    ;

arrayId
    : id LSQUARE RSQUARE
    ;

timerId
    : id
    ;

eventId
    : id
    ;

fileId
    : id
    ;

stringId
    : id
    ;

funcId
    : id
    ;

protoId
    : id
    ;

typeId
    : id
    ;

namespaceId
    : NAMESPACE id
    ;

constant
    : NUMBER_LEX
    | STRING_LEX
    ;

cString
    : CSTRING_LEX
    ;

id
    : ID_LEX
    ;

declSpecifier
    : SYNCHRONIZED
    | PRIVATE
    | STATIC
    | CONST
    ;

type
    : VARIABLE
    | STRING
    | OBJECT
    | TIMER
    ;

parameter
    : type id
    ;