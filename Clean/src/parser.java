package src;


import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Fri Apr 06 13:36:02 EDT 2012
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\020\000\002\002\004\000\002\002\004\000\002\003" +
    "\003\000\002\003\004\000\002\004\011\000\002\005\003" +
    "\000\002\005\005\000\002\006\004\000\002\006\003\000" +
    "\002\007\006\000\002\007\005\000\002\010\003\000\002" +
    "\010\005\000\002\011\003\000\002\011\003\000\002\011" +
    "\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\041\000\004\017\005\001\002\000\004\012\043\001" +
    "\002\000\004\010\012\001\002\000\004\002\011\001\002" +
    "\000\006\012\uffff\017\005\001\002\000\004\012\ufffe\001" +
    "\002\000\004\002\000\001\002\000\010\014\014\016\016" +
    "\020\017\001\002\000\006\007\041\011\ufff6\001\002\000" +
    "\012\006\ufff3\007\ufff3\011\ufff3\015\ufff3\001\002\000\004" +
    "\011\020\001\002\000\012\006\ufff4\007\ufff4\011\ufff4\015" +
    "\ufff4\001\002\000\012\006\ufff2\007\ufff2\011\ufff2\015\ufff2" +
    "\001\002\000\004\005\021\001\002\000\014\013\025\014" +
    "\014\016\016\017\027\020\017\001\002\000\004\006\040" +
    "\001\002\000\006\006\ufffc\007\036\001\002\000\006\006" +
    "\ufff9\007\ufff9\001\002\000\012\014\014\016\016\017\027" +
    "\020\017\001\002\000\004\015\033\001\002\000\004\010" +
    "\030\001\002\000\010\014\014\016\016\020\017\001\002" +
    "\000\004\011\032\001\002\000\006\006\ufff8\007\ufff8\001" +
    "\002\000\010\014\014\016\016\020\017\001\002\000\006" +
    "\006\ufff7\007\ufff7\001\002\000\006\006\ufffa\007\ufffa\001" +
    "\002\000\014\013\025\014\014\016\016\017\027\020\017" +
    "\001\002\000\004\006\ufffb\001\002\000\006\012\ufffd\017" +
    "\ufffd\001\002\000\010\014\014\016\016\020\017\001\002" +
    "\000\004\011\ufff5\001\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\041\000\010\002\005\003\003\004\006\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\003\007\004\006\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\010\014\011\012\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\012\005\021\006\022" +
    "\007\023\011\025\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\006\007\034\011\025\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\010\030\011" +
    "\012\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\011\033\001\001\000\002\001\001\000\002\001\001\000" +
    "\012\005\036\006\022\007\023\011\025\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\010\041\011\012\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




 public void report_error(String message, Object info) {
   StringBuffer m = new StringBuffer("Error");
   if (info instanceof java_cup.runtime.Symbol) {
     java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
     if (s.left >= 0) {
       m.append(" in line "+(s.left+1));
       if (s.right >= 0)
         m.append(", column "+(s.right+1));
     }
   }
   m.append(" : "+message);
   System.err.println(m);
 }

 public void report_fatal_error(String message, Object info)
   throws Exception {
   report_error(message, info);
   throw new Exception();
   //System.exit(1);
 }


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // arg ::= VARIABLE 
            {
              DQNode RESULT =null;
  int n1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int n1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  String n1 = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_ARGUMENT_VARIABLE);
  n.setValue(n1.toUpperCase());
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("arg",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // arg ::= STRING 
            {
              DQNode RESULT =null;
  int n1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int n1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  Object n1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_ARGUMENT_STRING);
  n.setValue(n1.toString().toUpperCase());
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("arg",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // arg ::= NUMBER 
            {
              DQNode RESULT =null;
  int n1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int n1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  Integer n1 = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_ARGUMENT_NUMBER);
  n.setValue(n1.toString());
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("arg",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // arg_list ::= arg COMMA arg_list 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
  int c2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c2 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_ARGUMENT_LIST);
  n.setLeftChild(c1);
  n.setRightChild(c2);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("arg_list",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // arg_list ::= arg 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_ARGUMENT);
  n.setLeftChild(c1);
  RESULT = n;

        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("arg_list",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // predicate ::= arg COMPARISON arg 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
  int n1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int n1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  Object n1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  int c2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c2 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_PREDICATE_COMPARISON);
  n.setLeftChild(c1);
  n.setRightChild(c2);
  n.setValue(n1.toString());
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("predicate",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // predicate ::= NAME LPAREN arg_list RPAREN 
            {
              DQNode RESULT =null;
  int n1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
  int n1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
  String n1 = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_PREDICATE_REGULAR);
  n.setValue(n1.toUpperCase());
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("predicate",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // literal ::= predicate 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_LITERAL);
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // literal ::= NOTOP predicate 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_LITERAL_NEGATED);
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // idb_body ::= literal COMMA idb_body 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
  int c2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c2 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_IDB_BODY_LIST);
  n.setLeftChild(c1);
  n.setRightChild(c2);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("idb_body",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // idb_body ::= literal 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_IDB_BODY);
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("idb_body",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // idb_rule ::= NAME LPAREN arg_list RPAREN IMPLIES idb_body PERIOD 
            {
              DQNode RESULT =null;
  int nameleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).left;
  int nameright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).right;
  String name = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
  int c2left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int c2right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  DQNode c2 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_IDB_RULE);
  n.setValue(name.toUpperCase());
  n.setLeftChild(c1);
  n.setRightChild(c2);
  RESULT = n;

 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("idb_rule",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // idb_rules ::= idb_rule idb_rules 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  int c2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c2 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_IDB_RULES_LIST);
  n.setLeftChild(c1);
  n.setRightChild(c2);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("idb_rules",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // idb_rules ::= idb_rule 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_IDB_RULES);
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("idb_rules",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= ddb EOF 
            {
              Object RESULT =null;
  int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  DQNode start_val = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // ddb ::= idb_rules DOLLAR 
            {
              DQNode RESULT =null;
  int c1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
  int c1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
  DQNode c1 = (DQNode)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
  
  DQNode n = new DQNode();
  n.setNodeType(DQNode.TYPE_DDB);
  n.setLeftChild(c1);
  RESULT = n;
 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("ddb",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

