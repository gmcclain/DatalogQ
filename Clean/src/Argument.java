package src;

public class Argument {
 private boolean isConstant = false;
 private boolean isUnderscore = false;
 private boolean isVariable = false;
 private String argDataType;
 private String argName;
 private String argValue;

 public void setConstant(boolean isConstant) {
   this.isConstant = isConstant;
 }

 public void setUnderscore(boolean isUnderscore) {
   this.isUnderscore = isUnderscore;
 }

 public void setArgDataType(String argDataType) {
   this.argDataType = argDataType;
 }

 public void setArgName(String argName) {
   this.argName = argName;
 }

 public void setArgValue(String argValue) {
   this.argValue = argValue;
 }

 public boolean isConstant() {
   return isConstant;
 }

 public boolean isUnderscore() {
   return isUnderscore;
 }

 public boolean isVariable() {
  return isVariable;
 }

 public String getArgDataType() {
   return argDataType;
 }

 public String getArgName() {
   return argName;
 }

 public String getArgValue() {
   return argValue;
 }
 
 public String toString() {
   return argValue;
 }
}
