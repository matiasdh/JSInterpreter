package com.language.parser;

import java_cup.runtime.*;
import com.language.model.literals.*;

%%

%cup
%line
%unicode
%column

%class Scanner
%{
	private SymbolFactory sf;
	private StringBuffer string = new StringBuffer();

	public Scanner(java.io.InputStream r, SymbolFactory sf) {
		this(r);
		this.sf=sf;
	}

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%eofval{
    return symbol(ParserSym.EOF);
%eofval}

ReservedWords = for | if | else | var | while | true | false | null

%include boolean/scanner_definition.jflex
%include comments/scanner_definition.jflex
%include string/scanner_definition.jflex
%include numeric/scanner_definition.jflex

Identifier = [:jletter:] [:jletterdigit:]*


%%

 <YYINITIAL> {

 "function"				{ return symbol(ParserSym.FUNCTION, "function"); } 
 
 "for"					{ return symbol(ParserSym.FOR, "for"); }
 
 "if"					{ return symbol(ParserSym.IF, "if"); }
 
 "else"					{ return symbol(ParserSym.ELSE, "else"); }
 
 "break"				{ return symbol(ParserSym.BREAK, "break"); }
 
 "continue"				{ return symbol(ParserSym.CONTINUE, "continue"); }
 
 "var"					{ return symbol(ParserSym.VAR, "var"); }
 
 "return"				{ return symbol(ParserSym.RETURN, "return"); }
 
 "{"					{ return symbol(ParserSym.OPEN_BLOCK, "{"); }
 
 "}"					{ return symbol(ParserSym.CLOSE_BLOCK, "}"); }
 
  /* literals */
	\"                  { string.setLength(0); yybegin(STRING); }
	
	\'                  { string.setLength(0); yybegin(SSTRING); }
	
	/* parenthesis */
	"(" 				{ return symbol(ParserSym.LPAREN, "("); }
	
	")" 				{ return symbol(ParserSym.RPAREN, ")"); }
	
/* boolean */	
	"true"				{ return symbol(ParserSym.BOOLEAN, true); }
	
	"false"				{ return symbol(ParserSym.BOOLEAN, false); }
	
	"&&"				{ return symbol(ParserSym.AND, "&&"); }
	
	"||"				{ return symbol(ParserSym.OR, "||"); }
	
	
/* operators */	
	"!"					{ return symbol(ParserSym.NOT, "!"); }
	
	"=="				{ return symbol(ParserSym.EQUALS, "=="); }
	
	"!="				{ return symbol(ParserSym.DISTINCT, "!="); }
	
	">"					{ return symbol(ParserSym.GREATER, ">"); }
	
	"<"					{ return symbol(ParserSym.LESS, "<"); }
	
	">="				{ return symbol(ParserSym.GREATER_OR_EQUAL, ">="); }
	
	"<="				{ return symbol(ParserSym.LESS_OR_EQUAL, "<="); }
	
	"="					{ return symbol(ParserSym.EQUAL, "="); }
		
	"++"				{ return symbol(ParserSym.DOUBLE_PLUS, "++"); }
	
	"--"				{ return symbol(ParserSym.DOUBLE_MINUS, "--"); }

	"+"					{ return symbol(ParserSym.PLUS, "+"); }
	
	"-"					{ return symbol(ParserSym.MINUS, "-"); }
	
	"*"					{ return symbol(ParserSym.TIMES, "*"); }
	
	"/"					{ return symbol(ParserSym.OBELUS, "/"); }

	/*NULL*/
	
	"null"				{ return symbol(ParserSym.NULL, "null"); }
	
	/*NaN*/
	
	"NaN"				{ return symbol(ParserSym.NaN, NumericLiteral.getNaN()); }
	
    /*ARRAY*/
        
    \[                  { return symbol(ParserSym.OPEN_ARRAY, "["); }
    
    \]                  { return symbol(ParserSym.CLOSE_ARRAY, "]"); }
    
    ","					{ return symbol(ParserSym.COMMA, ","); }
    
    "."					{ return symbol(ParserSym.PTO, "."); }
    
    ";"					{ return symbol(ParserSym.PTO_COMMA, ";"); }
    
    "{"					{ return symbol(ParserSym.OPEN_BLOCK, "{"); }
    
    "}"					{ return symbol(ParserSym.CLOSE_BLOCK, "}"); }

	{WhiteSpace}        { /* ignore */ }
    
    {LineTerminator}    { /* ignore */ }
    
    {Comment}           { /* ignore */ }
    
	%include numeric/scanner_user_code.jflex

	{Identifier}		{ return symbol(ParserSym.IDENTIFIER, yytext()); }

}

%include string/scanner_user_code.jflex

