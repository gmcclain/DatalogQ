package src;

import java.util.Vector;

public class IDBPredicate implements Comparable {
  private String predicateName;
  private Vector<Rule> rules;
  private Vector<String> argDataType;
  private int stratum;
  
  public void setPredicateName(String predicateName) {
    this.predicateName = predicateName;
  }
  
  public void setRules(Vector<Rule> rules) {
    this.rules = rules;
  }
  
  public void setArgDataType(Vector<String> argDataType) {
    this.argDataType = argDataType;
  }
  
  public void setStratum(int stratum) {
    this.stratum = stratum;
  }
    
  public String getPredicateName() {
    return predicateName;
  }
  
  public Vector<Rule> getRules() {
    return rules;
  }
  
  public Vector<String> getArgDataType() {
    return argDataType;
  }
  
  public int getStratum() {
    return stratum;
  }
  
  public int compareTo(Object o) {
    if(!(o instanceof IDBPredicate))
      return 0;
    IDBPredicate compare = (IDBPredicate)o;
    return getStratum() - compare.getStratum();
  }
}