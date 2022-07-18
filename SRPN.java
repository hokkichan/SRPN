import java.util.*;

/** 
 * This java program serves to replicate a legacy system of a "Saturated 
 * Reverse Polish Notation (SRPN) Calculator", available on 
 * https://replit.com/@bathuniversity/sprn-to-emulate.
 * 
 * SRPN is a reverse Polish notation calculator equipped with an extra feature 
 * that arithmetic calculations are saturated, i.e. when the calculator reaches 
 * a maximum value that can be stored in a variable, it would stay at the 
 * maximum rather than wrapping around.
 */

/**
 * The SRPN calculator contains the below functions:
 * 
 * (A) Operands
 * 
 * Integer (Base 10) : Positive/negative integers under decimal numeral system.
 * 
 * Integer (Base 8) : Positive/negative integers under octal numeral system. To
 * input an integer in octal format, start with "0", e.g. "0177".
 * 
 * "r" : Input of a random number in int format, which comes from a list of
 * pseudo-random numbers via a linear additive feedback method. More on the
 * method can be found on https://www.mathstat.dal.ca/~selinger/random/.
 * 
 * ==============================================================================
 * 
 * (B) Operator
 * 
 * "+" : Performing addition on two operands.
 * 
 * "+=" : Performing addition on two operands, pushing the result to stack, and
 * printing out the latest operand inputed.
 * 
 * "-" : Performing subtraction on two operands.
 * 
 * "-=" : Performing subtraction on two operands, pushing the result to stack,
 * and printing out the latest operand inputed.
 * 
 * "*": Performing multiplication on two operands.
 * 
 * "*=" : Performing multiplication on two operands, pushing the result to
 * stack, and printing out the latest operand inputed.
 * 
 * "/" : Performing division on two operands. A divisor of "0" will return a
 * division error.
 * 
 * "/=" : Performing division on two operands, pushing the result to stack, and
 * printing out the latest operand inputed.
 * 
 * "^" : Performing power operation on two operands (base number and exponent).
 * Negative number is not allowed for the exponent and will give error message.
 * 
 * "^=" : Performing power operation on two operands, pushing the result to
 * stack, and printing out the latest operand inputed.
 * 
 * "%" : Performing modulo operation on two operands.
 * 
 * "%=" : Performing modulo operation on two operands, pushing the result to
 * stack, and printing out the latest operand inputed.
 * 
 * ==============================================================================
 * 
 * (C) Other functions
 * 
 * "#" : Opening/closing operator for comment for which enclosing input will not
 * be operated.
 * 
 * "d" : Display the whole stack.
 * 
 * "=" : Peek on the stack and display the top value of the stack.
 * 
 * "." : Detection of decimal values which will throw error when decimal values
 * are involved.
 * 
 * To use the calculator, enter operands first, then the operator, and finally
 * the "=" sign.
 * 
 * Example:
 * 
 * 10 2 + = -> Return 12
 * 
 * 3 3 * 4 4 * + = -> Return 25
 * 
 * Result generated will normally be a 32-bit int, thus values beyond the range
 * would be limited between -2147483648 (-2^31) and 2147483647 (2^31-1), which
 * are values of the minimum and maximum limits of int data type of Java.
 * 
 */

public class SRPN {

  // Declare and initialize an array of random numbers.
  private int randomNum[] = new int[] { 1804289383, 846930886, 1681692777, 1714636915, 1957747793, 424238335, 719885386,
      1649760492, 596516649, 1189641421, 1025202362, 1350490027, 783368690, 1102520059, 2044897763, 1967513926,
      1365180540, 1540383426, 304089172, 1303455736, 35005211, 521595368 };

  // Declare and initialize a counter to display the designated random number from
  // its array.
  private int randomCounter = 0;

  // Boolean value to toggle between comment mode.
  private boolean commentMode = false;

  // Declare and initialize min and max values for calculation output, and max
  // stack size.
  final int MIN_VALUE = -2147483648;
  final int MAX_VALUE = 2147483647;
  final int STACK_MAX_VALUE = 23;

  // Declaration of a stack to hold calculation values.
  public Stack<Integer> stack = new Stack<>();

  // Method for processing of command before passing to process operations.
  public void processCommand(String s) {

    // Identify comment section with "#...#" and designate for further handling.
    String stripped = s.replaceAll("#.*#", "COMMENT_SECTION");

    // Toggle between comment mode for which inputed text will not be processed when
    // the mode is on.
    if (stripped.contains("#")) {
      commentMode = !commentMode;
    }

    // Parse items separated by spaces into discrete operands and operators.
    List<String> items = Arrays.asList(stripped.split("\\s"));

    // Pass the parsed operands and operators to the process operation method.
    if (commentMode == false) {
      for (int i = 0; i < items.size(); i++) {
        processToken(items.get(i));
      }
    }
  }

  // Check the value of output cap the value between min and max values.
  public int checkRange(long value, int min, int max) {
    return (int) Math.min(Math.max(value, min), max);
  }

  // Methods for "+", "-", "*", "/", "^" and "%" operations.

  // Return result for plus operation
  public void plusOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      int result = checkRange(((long) n2 + (long) n1), MIN_VALUE, MAX_VALUE);
      stack.push(result);
    } else {
      System.out.println("Stack underflow.");
    }
  }

  // Return result for minus operation
  public void minusOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      int result = checkRange(((long) n2 - (long) n1), MIN_VALUE, MAX_VALUE);
      stack.push(result);
    } else {
      System.out.println("Stack underflow.");
    }
  }

  // Return result for multiply operation
  public void multiplyOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      int result = checkRange(((long) n1 * (long) n2), MIN_VALUE, MAX_VALUE);
      stack.push(result);
    } else {
      System.out.println("Stack underflow.");
    }
  }

  // Return result for division operation
  public void divisionOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      if (n1 != 0) {
        int result = checkRange(((long) n2 / (long) n1), MIN_VALUE, MAX_VALUE);
        stack.push(result);
      } else {
        System.out.println("Divide by 0.");
        stack.push(n2);
        stack.push(n1);
      }
    } else
      System.out.println("Stack underflow.");
  }

  // Return result for power operation
  public void powerOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      if (n1 >= 0) {
        int result = (int) Math.pow(n2, n1);
        stack.push(result);
      } else {
        System.out.println("Negative power.");
        stack.push(n2);
        stack.push(n1);
      }
    } else {
      System.out.println("Stack underflow.");
    }
  }

  // Return result for modulo operation
  public void moduloOperation() {
    if (stack.size() > 1) {
      int n1 = stack.pop();
      int n2 = stack.pop();
      int result = checkRange(((long) n2 % (long) n1), MIN_VALUE, MAX_VALUE);
      stack.push(result);
    } else {
      System.out.println("Stack underflow.");
    }
  }

  // Method for peeking the top value of the stack.
  public void peekStack() {
    try {
      System.out.println(stack.peek());
    } catch (EmptyStackException e) {
      System.out.println("Stack empty.");
    }
  }

  // Calling declared methods to handle operators of "+", "+=", "-", "-=", "*",
  // "*=", "/", "/=", "^", "^=", "%", "%=", and operands of "#", "d", "=", "r",
  // and integers of decimal and octal format.
  
  public void processToken(String s) {

    if (s.equals("+")) {
      plusOperation();
    }

    else if (s.equals("+=")) {
      peekStack();
      plusOperation();
    }

    else if (s.equals("-")) {
      minusOperation();
    }

    else if (s.equals("-=")) {
      peekStack();
      minusOperation();
    }

    else if (s.equals("*")) {
      multiplyOperation();
    }

    else if (s.equals("*=")) {
      peekStack();
      multiplyOperation();
    }

    else if (s.equals("/")) {
      divisionOperation();
    }

    else if (s.equals("/=")) {
      peekStack();
      divisionOperation();
    }

    else if (s.equals("^")) {
      powerOperation();
    }

    else if (s.equals("^=")) {
      peekStack();
      powerOperation();
    }

    else if (s.equals("%")) {
      moduloOperation();
    }

    else if (s.equals("%=")) {
      peekStack();
      moduloOperation();
    }

    // Operator of "=" will peek on the stack and return its top value.
    else if (s.equals("=")) {
      try {
        System.out.println(stack.peek());
      } catch (EmptyStackException e) {
        System.out.println("Stack empty.");
      }
    }

    // Operand of "r" will return a different random number under a predefined set,
    // following the number of times of calling "r".
    else if (s.equals("r")) {
      if (stack.size() < STACK_MAX_VALUE) {
        stack.push(randomNum[(randomCounter % (STACK_MAX_VALUE - 1))]);
        randomCounter++;
      } else {
        System.out.print("Stack overflow.\n");
      }
    }

    // Operator of "d" will display all values under the stack.
    else if (s.equals("d")) {
      if (stack.size() >= 1) {
        for (int val : stack) {
          System.out.println(val);
        }
      } else {
        System.out.println(MIN_VALUE);
      }
    }

    // Detection of decimal points and return error message.
    else if (s.contains(".")) {
      System.out.println("Unrecognised operator or operand \".\"");
    }

    // Detection of comment section, or start and end of a comment section.
    else if (s.contains("COMMENT_SECTION") || s.contains("#")) {
      System.out.print("");
    }

    // Detection of an octal number input and parse to decimal value for processing.
    // Upon detection of a valid octal number, push to stack for subsequent
    // processing.
    else if ((s.startsWith("0") || s.startsWith("-0")) && Integer.parseInt(s) != 0) {
      if (stack.size() < STACK_MAX_VALUE) {
        try {
          long long_input = Long.parseLong(s, 8);
          int i = checkRange((long_input), MIN_VALUE, MAX_VALUE);
          stack.push(i);
        } catch (NumberFormatException e) {
          // Print error message for unrecognised operator or operand.
          System.out.println("Unrecognised operator or operand \"" + s + "\".");
        } catch (IllegalArgumentException e) {
          System.out.print("");
        }
      } else {
        // Print error message when input will overflow stack.
        System.out.println("Stack overflow.");
      }
    }

    // Upon detection of a valid integer, push to stack for subsequent processing.
    else {
      if (stack.size() < STACK_MAX_VALUE) {
        try {
          long long_input = Long.parseLong(s);
          int i = checkRange((long_input), MIN_VALUE, MAX_VALUE);
          stack.push(i);
        } catch (NumberFormatException e) {
          // Print error message for unrecognised operator or operand.
          System.out.println("Unrecognised operator or operand \"" + s + "\".");
        } catch (IllegalArgumentException e) {
          System.out.print("");
        }
      } else {
        // Print error message when input will overflow stack.
        System.out.println("Stack overflow.");
      }
    }
  }
}
