package src;

import java.util.*; 
public class Rule { 
  
// The next two lines were added to return results of Safety Check 
  public boolean passSafetyCheck = true; 
  public String errorString; 
  
  private String Underscore = Character.toString('_'); 
  
  private Predicate headPredicate; 
  
  private Vector<Predicate> bodyPredicates; 
  private Vector<Predicate> regularBodyPredicates; 
  private Vector<Predicate> comparisonBodyPredicates; 
  
  private Vector<Argument> headPredicateVariables; 
  // new vector object headPredicateVariables is to hold the values of predicate arguments 
  private Vector<Argument> regularBodyPredicateVariables; 
  // new vector object regularBodyPredicateVariables to hold the values of predicate arguments 
  
  public void setHeadPredicate(Predicate headPredicate) { 
    this.headPredicate = headPredicate; 
  } 
  
  public void setBodyPredicates(Vector<Predicate> bodyPredicates) { 
    this.bodyPredicates = bodyPredicates; 
  } 
  
  public void setRegularBodyPredicates(Vector<Predicate> regularBodyPredicates) { 
    this.regularBodyPredicates = regularBodyPredicates; 
  } 
  
  public void setComparisonBodyPredicates(Vector<Predicate> comparisonBodyPredicates) { 
    this.comparisonBodyPredicates = comparisonBodyPredicates; 
  } 
  
  public Vector<Predicate> getBodyPredicates() { 
    return bodyPredicates; 
  } 
  
  public Predicate getHeadPredicate() { 
    return headPredicate; 
  } 
  
  public Vector<Predicate> getRegularBodyPredicates() { 
    return regularBodyPredicates; 
  } 
  
  public Vector<Predicate> getComparsionBodyPredicates() { 
    return comparisonBodyPredicates; 
  } 
  
  // Beginning of Safety Check 
  public void safetyCheck() { 
    
    //  boolean passSafetyCheck = true; 
    for (Argument arg : headPredicate.getArguments()) { 
      if (arg.getArgValue().contains(Underscore)) { 
        passSafetyCheck = false; 
        errorString = "Anonymous variable found in head predicate";
        // "Head Predicate contains an underscore" 
      } 
      else if (arg.isVariable()) { 
        // add headPredicate.arg to headPredicateContains vector object 
        headPredicateVariables.addElement(arg); 
      } 
    } 
    
    //for each loop to load regularBodyPredicates into vector object 
    for (Predicate bp : regularBodyPredicates) { 
      for (Argument arg : bp.getArguments()) { 
        if (arg.getArgValue().contains(Underscore)) { 
          if (bp.isNegated()) {
            passSafetyCheck = false;
            errorString = "NEGATED regular body predicate contains underscore.";
          // a string variable could be set here to provide detail of the error 
          // "Regular Body Predicate contains an underscore" 
          }
        } 
        else if (arg.isVariable()) { 
          if (!bp.isNegated()) { 
            // add regularBodyPredicate.arg to regularBodyPredicateContains 
            regularBodyPredicateVariables.addElement(arg); 
          } 
        } 
      } 
    } 
    
    for (Predicate bp : regularBodyPredicates) { 
      for (Argument arg : bp.getArguments()) { 
        if (bp.isNegated() && arg.isVariable()) { 
          if (!regularBodyPredicateVariables.contains(arg)) { 
            passSafetyCheck = false; 
            errorString = "Negated Regular Body Predicate is not contained in other Predicates";
            // "Negated Regular Body Predicate is not contained in other Predicates" 
          } 
        } 
      } 
    } 
    
    
    for (Argument arg : headPredicate.getArguments()) { 
      if (arg.isVariable()) { 
        if (!regularBodyPredicateVariables.contains(arg)) { 
          passSafetyCheck = false; 
        } 
      } 
    } 
    
  }
  
  public String toString() {
    String toReturn = "";
    toReturn += headPredicate.toString();
    toReturn += " :- ";
    for(int x = 0; x < bodyPredicates.size(); x++) {
      if(x == bodyPredicates.size() -1)
        toReturn += bodyPredicates.get(x).toString() + ".";
      else
        toReturn += bodyPredicates.get(x).toString() + ",\n\t";
    }
      
    return toReturn;
  }
} 
