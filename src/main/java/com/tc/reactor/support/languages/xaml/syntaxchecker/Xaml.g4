grammar Xaml;

// Tokens
OPEN: '<';
CLOSE: '>';
CLOSE_TAG: '</';
SELF_CLOSING: '/>';
NAME: [a-zA-Z_:][a-zA-Z0-9_:\-]*;
STRING: '"' .*? '"';
WS: [ \t\r\n] -> skip;

// Root Rule for the XAML File
xamlFile: element* EOF;

// Element Rule (supports Hamilton and Tecan-specific elements)
element
    : OPEN tagName (attributeList)? (CLOSE element* CLOSE_TAG | SELF_CLOSING)
    ;

// Tag Name Rule
tagName: NAME;

// Attributes List
attributeList: attribute*;

// Attribute Rule
attribute: NAME '=' STRING;

// Namespaces (specific to Hamilton and Tecan XAML)
namespace: 'xmlns' (':' NAME)? '=' STRING;