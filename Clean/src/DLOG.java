package src;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import edu.gsu.cs.dbengine.*;

public class DLOG {
  public static Scanner in;
  public static boolean evalError;
  public final static String DB_PATH = JOptionPane.showInputDialog("Specify path for database[e.g. \"datafiles/company\"]: ");
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    //initialize db
    Relation.initializeDatabase(DB_PATH);
    
    System.out.println("Usage: ");
    System.out.println("\tType input for direct input query, terminated with $");
    System.out.println("\tTo use file for input, format is @example.txt;");
    System.out.println("\tValid File List:");
    try {
      File dir = new File("tests");
      String[] children = dir.list();
      
      for(String file : children) 
        System.out.println("\t\t" + file);
    } catch(Exception e) {}
    in = new Scanner(System.in);
    while (true) {
      System.out.print("DQ> ");
      String input = in.nextLine().trim();
      if (input.equalsIgnoreCase("exit"))
        break;
      else if(input.startsWith("@") && input.endsWith(";")) {
        try {
          System.out.println("Drawing input from: tests/" + input.substring(1,input.length()-1));
          in = new Scanner(new File("tests/" +input.substring(1,input.length()-1)));
          input = "";
          while(in.hasNextLine())
            input = input + in.nextLine();
          System.out.println(input);
          in = new Scanner(System.in);
        } catch(Exception e) {
          e.printStackTrace();
          System.out.println("Error: Invalid filename.");
          System.exit(1);
        }
      }
      String tempString = input;
      while(!tempString.endsWith("$")) {
        tempString = in.nextLine();
        input += tempString;
      }
      try {
        StringReader reader = new StringReader(input);
        parser p = new parser(new Lexer(reader));
        DQNode tree = (DQNode) p.parse().value;
        Program program = generateProgram(tree);
        System.out.println("------------- INPUT QUERY -------------");
        System.out.println(program);
        System.out.println("---------------------------------------");
        
        answerPredicateCheck(program);
        safetyCheck(program);
        schemaCheck(program);
        recursiveQueryCheck(program);
        
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("FATAL ERROR... EXITING");
        break;
      }
    }
  }
  
  static String readInput() {
    try {
      StringBuffer buffer = new StringBuffer();
      System.out.flush();
      int c = System.in.read();
      while (c != ';' && c != -1) {
        if (c != '\n')
          buffer.append((char) c);
        else {
          buffer.append(" ");
          System.out.print("DQ> ");
          System.out.flush();
        }
        c = System.in.read();
      }
      return buffer.toString().trim();
    } catch (IOException e) {
      return "";
    }
  }
  
  public static Program generateProgram(DQNode headNode) {
    Program program = new Program();
    Vector<Rule> rules = new Vector<Rule>();
    
    
    DQNode parentNode = headNode.getLeftChild();
    DQNode currentNode = parentNode.getRightChild();
    while(true) {
      Rule toAdd = generateRule(parentNode.getLeftChild());
      rules.add(toAdd);
      
      //if currentNode = null, then structure is ddb => idb_rule, rather than ddb => idb_rule idb_rules
      if(currentNode != null) {
        parentNode = currentNode;
        currentNode = currentNode.getRightChild();
      } else
        break;
    }
    
    program.setRules(rules);
    return program;
  }
  
  public static Rule generateRule(DQNode node) {
    Rule toReturn = new Rule();
    
    //create/generate the head predicate
    Predicate headPredicate = new Predicate();
    DQNode leftChild = node.getLeftChild();
    headPredicate.setPredName(node.getValue());
    headPredicate.setArguments(generateArgList(leftChild));
    
    //initialize the lists of all predicates and sub-groups
    Vector<Predicate> bodyPredicates = new Vector<Predicate>();
    Vector<Predicate> regularBodyPredicates = new Vector<Predicate>();
    Vector<Predicate> comparisonBodyPredicates = new Vector<Predicate>();
    
    //reference nodes for each iteration, starting with right child of idb_rule and subsequent right child
    DQNode parentNode = node.getRightChild();
    DQNode currentNode = null;
    try {
      currentNode = parentNode.getRightChild();
    } catch(Exception e) {}
    
    //temporary predicate variable
    Predicate toAdd = null;
    while(true) {
      //------------------handle LEFT node of idb_body
      toAdd = generatePredicate(parentNode);
      bodyPredicates.add(toAdd);
      if(toAdd.isComparison())
        comparisonBodyPredicates.add(toAdd);
      else
        regularBodyPredicates.add(toAdd);
      //----------------------------------end LEFT side
      
      //------------------handle RIGHT node of idb_body
      //if currentNode is not null, there is an additional idb_body
      if(currentNode != null) {
        //reassign parent/current nodes to prepare for next iteration
        parentNode = currentNode;
        currentNode = currentNode.getRightChild();
      }
      else
        break;
      //----------------------------------end RIGHT side
    }
    
    //set parameters of the Rule toReturn
    toReturn.setHeadPredicate(headPredicate);
    toReturn.setBodyPredicates(bodyPredicates);
    toReturn.setRegularBodyPredicates(regularBodyPredicates);
    toReturn.setComparisonBodyPredicates(comparisonBodyPredicates);
    
    return toReturn;
  }
  
  public static Predicate generatePredicate(DQNode node) {
    Predicate toReturn = new Predicate();
    DQNode literal = node.getLeftChild();
    DQNode leftChild = node.getLeftChild().getLeftChild();
    if(leftChild == null) {
      return toReturn;
    }
    else if(leftChild.getNodeType() == DQNode.TYPE_PREDICATE_REGULAR) {
      //node is a regular type
      toReturn.setPredName(leftChild.getValue());
      toReturn.setArguments(generateArgList(leftChild.getLeftChild()));
      toReturn.setComparison(false);
    } else {
      //node is a comparison type
      toReturn.setLeftOperand(generateArgument(leftChild.getLeftChild()));
      toReturn.setRightOperand(generateArgument(leftChild.getRightChild()));
      toReturn.setComparisonOperator(leftChild.getValue());
      toReturn.setComparison(true);
    }
    if(literal.getNodeType() == DQNode.TYPE_LITERAL_NEGATED)
      toReturn.setNegated(true);
    return toReturn;
  }
  
  public static Vector<Argument> generateArgList(DQNode node) {
    Vector<Argument> argList = new Vector<Argument>();
    
    DQNode parentNode = node;
    DQNode leftChild = node.getLeftChild();
    DQNode rightChild = null;
    
    while(true) {
      argList.add(generateArgument(leftChild));
      try { rightChild = parentNode.getRightChild(); } catch(Exception e) {}
      if(rightChild == null)
        break;
      else {
        parentNode = rightChild;
        leftChild = parentNode.getLeftChild();
        rightChild = null;
      }
    }
    
    return argList;
  }
  
  public static Argument generateArgument(DQNode node) {
    Argument argument = new Argument();
    if(node.getNodeType() == DQNode.TYPE_ARGUMENT_STRING || node.getNodeType() == DQNode.TYPE_ARGUMENT_NUMBER)
      argument.setConstant(true);
    else
      argument.setConstant(false);
    if(node.getValue() != null) {
      if(node.getValue().equals("_"))
        argument.setUnderscore(true);
      else
        argument.setUnderscore(false);
    }
    argument.setArgValue(node.getValue());
    return argument;
  }
  
  public static boolean passAPCheck = false;
  public static void answerPredicateCheck(Program program) {
    passAPCheck = false;
    
    for(int x = 0; x < program.getRules().size(); x++) {
      Predicate answerPred = program.getRules().get(x).getHeadPredicate();
      
      //check for answer predicate in all head predicates, if found, passes test
      if(answerPred.getPredName().equals("ANSWER")) {
        if(passAPCheck) {
          passAPCheck = false;
          System.out.println("Error: Duplicate ANSWER predicate found in head predicate definitions - Rule " + (x+1));
          break;
        }
        passAPCheck = true;
      }
    }
    answerPredicateCheck2(program);
    if(passAPCheck)
      System.out.println("Answer Predicate Check...\t\t\tOK");
    else {
      System.out.println("Answer Predicate Check...\t\t\tFAILED");
      System.out.println("Exiting...");
      System.exit(1);
    }
  }
  
  public static void answerPredicateCheck2(Program program) {
    passAPCheck = true;
    Vector<Predicate> headPredicates = new Vector<Predicate>();
    for(Rule rule : program.getRules())
      headPredicates.add(rule.getHeadPredicate());
    for(int x = 0; x < program.getRules().size(); x++) {
      Rule currentRule = program.getRules().get(x);
      Vector<Predicate> predicates = currentRule.getRegularBodyPredicates();
      for(Predicate p : predicates) {
        if(p.getPredName().equals("ANSWER")) {
          System.out.println("Semantic Error ANSWER predicate should not appear in rule body.");
          passACheck = false;
        }
      }
    }
  }
  
  public static void safetyCheck(Program program) {
    boolean passCheck = true;
    for(int x = 0; x < program.getRules().size(); x++) {
      Rule currentRule = program.getRules().get(x);
      currentRule.safetyCheck();
      if(!currentRule.passSafetyCheck) {
        passCheck = false;
        System.out.println("Safety Check: " + currentRule.errorString + ". - Rule " + (x+1));
      }
    }
    System.out.println("Safety Check...\t\t\t\t" + (passCheck ? "OK" : "FAILED"));
    if(!passCheck) {
      System.out.println("Safety Check Failed... Exiting...");
      System.exit(1);
    }
  }
  
  public static void schemaCheck(Program program) {
    //Check 1: Regular Body Predicate Check: Chris Manning
    regularBodyPredicateCheck(program);
    if(passRBPCheck) {
      System.out.println("Regular Body Predicate Check...\t\tOK");
    }
    else {
      System.out.println("Regular Body Predicate Check...\t\tFAILED");
      System.out.println("Exiting...");
      System.exit(1);
    }
    
    //Check 2: Head Predicate Check: Chris Manning
    headPredicateCheck(program);
    if(passHPCheck) {
      System.out.println("Head Predicate Check...\t\t\tOK");
    }
    else {
      System.out.println("Head Predicate Check...\t\t\tFAILED");
      System.out.println("Exiting...");
      System.exit(1);
    }
    
    arityCheck(program);
    if(passACheck) 
      System.out.println("Arity Check...\t\t\t\tOK");
    else {
      System.out.println("Arity Check...\t\t\t\tFAILED");
      System.out.println("Exiting...");
      System.exit(1);
    }
    
    recursiveQueryCheck(program);
    if(passRQCheck)
      System.out.println("Recursive Query Check...\t\t\tOK");
    else {
      System.out.println("Recursive Query Check...\t\t\tFAILED");
      System.out.println("Exiting...");
      System.exit(1);
    }
    
    typeCheck(program);
    if(passTCheck)
      System.out.println("Type Check...\t\t\t\tOK");
    else {
      System.out.println("Type Check...\t\t\t\tFAILED");
      //System.out.println("Exiting...");
      //System.exit(1);
    }
  }
  
  public static boolean passTCheck = true;
  public static void typeCheck(Program program) {
    populateTypes(program);
    
  }
  
  public static void populateTypes(Program program) {
    //create sorted list
    LinkedList<IDBPredicate> collection = new LinkedList<IDBPredicate>(predicateMap.values());
    Collections.sort(collection);
    
    
  }
  
  public static boolean passACheck = true;
  public static void arityCheck(Program program) {
    passACheck = true;
    
    //construct list of head predicates
    Vector<Predicate> headPredicates = new Vector<Predicate>();
    for(Rule rule : program.getRules())
      headPredicates.add(rule.getHeadPredicate());
    
    //iterate through all rules
    for(int x = 0; x < program.getRules().size(); x++) {
      
      //get current rule
      Rule currentRule = program.getRules().get(x);
      
      //construct list of regular body predicates per rule
      Vector<Predicate> predicates = currentRule.getRegularBodyPredicates();
      for(Predicate e : predicates) {  
        //check to make sure relation exists first
        if(Relation.relationExists(e.getPredName())) {
          //make sure number of arguments in body predicates equal the number of arguments in database
          if(e.getArity() != Relation.getRelation(e.getPredName()).getAttributes().size()) {
            System.out.println("Semantic Error: Database Arity Check");
            passACheck = false;
          }
        }
      }
      
      for(Predicate en : predicates) {  
        for(Predicate ne : headPredicates) {           
          if(!Relation.relationExists(en.getPredName()) && !Relation.relationExists(ne.getPredName())) {
            if(en.getArity() != ne.getArity() && en.getPredName().equals(ne.getPredName())) {
              System.out.println("Semantic Error: IDB Arity Check");
              passACheck = false;        
            }
          }
        }
      }
    }
  }
  
  public static HashMap<String,IDBPredicate> predicateMap;
  public static boolean passRQCheck = true;
  public static void recursiveQueryCheck(Program program) {
    passRQCheck = true;
    predicateMap = new HashMap<String, IDBPredicate>(); 
    //populate all idbpredicates/stratum to 0 in predicateMap
    for(Rule rule : program.getRules()) {
      Predicate hp = rule.getHeadPredicate();
      IDBPredicate tp = new IDBPredicate();
      tp.setStratum(0);
      tp.setPredicateName(hp.getPredName());
      predicateMap.put(hp.getPredName(),tp);
    }
    
    
    boolean changesOccured = true;
    while(changesOccured) {
      changesOccured = false;
      for(Rule rule : program.getRules()) {
        Predicate hp = rule.getHeadPredicate();
        
        IDBPredicate curIDB = predicateMap.get(hp.getPredName());
        if(curIDB.getStratum() > program.getRules().size()) {
          //fail test, recursive query found.
          System.out.println("Error: Recursive query found.");
          passRQCheck = false;
          break;
        }
        
        for(Predicate p : rule.getRegularBodyPredicates()) {
          //exclude all db predicates
          if(!Relation.relationExists(p.getPredName())) {
            //if the stratum of the head predicate is less than that of one contained,
            //set stratum to the contained's stratum + 1 to indicate larger depth
            //changesOccured = true, therefore repeat loop
            int tStratum = predicateMap.get(p.getPredName()).getStratum();
            if(curIDB.getStratum() <= tStratum) {
              changesOccured = true;
              curIDB.setStratum(tStratum + 1);
            }
          }
        }
      }
    }
  }
  
  public static boolean passRBPCheck = true;
  public static void regularBodyPredicateCheck(Program program) {
    passRBPCheck = true;
    //make new list with all head predicates
    Vector<Predicate> headPredicates = new Vector<Predicate>();
    for(Rule rule : program.getRules())
      headPredicates.add(rule.getHeadPredicate());
    
    //check the regular body predicates of each rule
    for(int x = 0; x < program.getRules().size(); x++) {
      Rule currentRule = program.getRules().get(x);
      Vector<Predicate> predicates = currentRule.getRegularBodyPredicates();
      for(Predicate p : predicates) {
        if(!headPredicates.contains(p) && !Relation.relationExists(p.getPredName())) {
          System.out.println("Regular Body Predicate exists in Rule Body but does not exist in Database or New Rule Definitions - Rule " + (x+1));
          passRBPCheck = false;
        }
      }
    }
  }
  
  public static boolean passHPCheck = true;
  public static void headPredicateCheck(Program program) {
    passHPCheck = true;
    Vector<Predicate> headPreds = new Vector<Predicate>();
    for(int x = 0; x < program.getRules().size(); x++) {
      Predicate headPred = program.getRules().get(x).getHeadPredicate();
      
      //make sure none of them are database predicates
      if(Relation.relationExists(headPred.getPredName())) {
        passHPCheck = false;
        System.out.println("Head Predicate, " + headPred.getPredName() + ", already exists in Database. - Rule " + (x+1));
      }
    }
    for(Predicate p : headPreds) {
      for(Predicate q : headPreds) {
        if(p.getPredName().equals(q.getPredName())) {
          if(p.getArity() != q.getArity()) {
            passHPCheck = false;
            System.out.println("Error in Head Predicate Arity.");
          }
        }
      }
    }
  }
}
