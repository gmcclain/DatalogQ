import java_cup.runtime.*;
%%
%class Lexer
%line
%column
%cup
%{
 private Symbol symbol(int type) {
   return new Symbol(type, yyline, yycolumn);
 }
 private Symbol symbol(int type, Object value) {
   return new Symbol(type, yyline, yycolumn, value);
 }
%}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Number = [0-9]+ | [0-9]+"."[0-9]+ | "."[0-9]+
Variable = [_A-Z][_A-Za-z0-9]*
Name = [a-z][_A-Za-z0-9]*
Notop = [nN][oO][tT]
Comparison = = | < | > | <> | <= | >=
String = ['][^'\r\n]*[']
%%
/* ------------------------Lexical Rules Section---------------------- */
<YYINITIAL> {
   ":-"               { return symbol(sym.IMPLIES); }
   "."                { return symbol(sym.PERIOD); }
   ","                { return symbol(sym.COMMA); }
   "("                { return symbol(sym.LPAREN); }
   ")"                { return symbol(sym.RPAREN); }
   "$"                { return symbol(sym.DOLLAR); }
   {Comparison}       { return symbol(sym.COMPARISON, new String(yytext())); }
   {Notop}            { return symbol(sym.NOTOP); }
   {String}           { return symbol(sym.STRING, new String(yytext())); }
   {Number}          { return symbol(sym.NUMBER, new Integer(yytext())); }
   {Name}             { return symbol(sym.NAME, new String(yytext()));}
   {Variable}         { return symbol(sym.VARIABLE, new String(yytext()));}
   {WhiteSpace}       { /* just skip what was found, do nothing */ }
}
[^]                    { System.out.println("Syntax Error - Scanning Error");
                        return symbol(sym.ERROR); }