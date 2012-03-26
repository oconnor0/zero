/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.compiler.parser;

public class Token {
  public enum Type {
    // grouped tokens
    NAME,
    INTEGER,
    // exact tokens
    LET,
    VAL,
    FN,
    IN,
    IF,
    THEN,
    ELSE,
    END,
    MATCH,
    WITH,
    TYPE,
    EQUALS,
    PLUS,
    MINUS,
    ASTERISK,
    SLASH,
    BACKSLASH,
    COMMA,
    DOT,
    COLON,
    SEMICOLON,
    RPAREN,
    LPAREN,
    VBAR,
    RARROW,
    LARROW,
    BIARROW,
    ERROR,
    EOF;

    public static Type fromString(final String s) {
      switch(s) {
        case "let":   return LET;
        case "val":   return VAL;
        case "fn":    return FN;
        case "if":    return IF;
        case "then":  return THEN;
        case "else":  return ELSE;
        case "end":   return END;
        case "type":  return TYPE;
        case "match": return MATCH;
        case "with":  return WITH;
        case "=":     return EQUALS;
        case "+":     return PLUS;
        case "-":     return MINUS;
        case "*":     return ASTERISK;
        case "/":     return SLASH;
        case "\\":    return BACKSLASH;
        case ".":     return DOT;
        case ":":     return COLON;
        case ";":     return SEMICOLON;
        case "(":     return LPAREN;
        case ")":     return RPAREN;
        case "|":     return VBAR;
        case "->":    return RARROW;
        case "<-":    return LARROW;
        case "<->":   return BIARROW;
        case "\0":    return EOF;
      }
      throw new ParseException(String.format("Could not convert \"%s\" to a Token.Type", s));
    }
  }

  public static final Token EOF = new Token(Type.EOF, "\0");

  private Type   type;
  private String text;
  // private Span span;

  public Token(final Type type, final String text) {
    this.type = type;
    this.text = text;
  }

  public Type getType() {
    return type;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return String.format("%s \"%s\"", type, text);
  }
}

/*
keyword
name
operator
((f x) y)

( ) { } [ ] < > << >> | |
(| |) {| |} {| |} {[ ]}

a(5)
a[5]
a{5}
a.(5)
a.[5]
a.{5}

![a]

type [a] list
type list [a]
type 'a list
type list 'a
type List a
type a List

module Map k v = struct
  abstract type t k v
  type T k v
  type Map k v
  type t 'k 'v
  type t [k,v]
end

Map.Map k v

val get : Map.t k v -> k -> v
*/