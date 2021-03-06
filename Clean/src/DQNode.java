package src;


public class DQNode {
 
 // --------------------------------- Variable Declarations
 private String value;
 private int type = -1;
 private DQNode leftChild, rightChild;
 private boolean isTerminal;
 
 public static int TYPE_DDB = 0;
 public static int TYPE_IDB_RULES = 1;
 public static int TYPE_IDB_RULES_LIST = 2;
 public static int TYPE_IDB_RULE = 3;
 public static int TYPE_IDB_BODY = 4;
 public static int TYPE_IDB_BODY_LIST = 5;
 public static int TYPE_LITERAL = 6;
 public static int TYPE_LITERAL_NEGATED = 7;
 public static int TYPE_PREDICATE_REGULAR = 8;
 public static int TYPE_PREDICATE_COMPARISON = 9;
 public static int TYPE_ARGUMENT = 10;
 public static int TYPE_ARGUMENT_LIST = 11;
 public static int TYPE_ARGUMENT_STRING = 12;
 public static int TYPE_ARGUMENT_NUMBER = 13;
 public static int TYPE_ARGUMENT_VARIABLE = 14;
 
 public DQNode() {
   leftChild = null;
   rightChild = null;
 }

 // --------------------------------- Setter Methods 
 public void setTerminal(boolean isTerminal) {
   this.isTerminal = isTerminal;
 }
 
 public void setValue(String value) {
   this.value = value;
 }
 
 public void setNodeType(int type) {
  this.type = type;
 }
 
 public void setLeftChild(DQNode leftChild) {
  this.leftChild = leftChild;
 }
 
 public void setRightChild(DQNode rightChild) {
  this.rightChild = rightChild;
 }

 // --------------------------------- Getter Methods
 public int getNodeType() {
  return type;
 }
 
 public DQNode getLeftChild() {
  return leftChild;
 }
 
 public DQNode getRightChild() {
  return rightChild;
 }
 
 public boolean isTerminal() {
   return isTerminal;
 }
 
 public String getValue() {
   return value;
 }
}
