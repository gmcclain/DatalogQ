package src;

import java.util.*;
public class Program {

 private Vector<Rule> rules;
 
 public void setRules(Vector<Rule> rules) {
   this.rules = rules;
 }
 
 public Vector<Rule> getRules() {
   return rules;
 }
 
 public String toString() {
   String disp = rules.get(0).toString();
   for(int x = 1; x < rules.size(); x++)
     disp += "\n" + rules.get(x);
   disp += "$";
   return disp;
 }
}
