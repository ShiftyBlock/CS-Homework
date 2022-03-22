/* BadAccountException.java */

/**
 *  Implements an exception that should be thrown for illegal withdrawls.
 **/
public class BadTransactionException extends Exception {


  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int num) {
    super("Invalid withdrawl amount: "+num);
  }
}
