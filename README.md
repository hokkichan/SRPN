# SRPN
SRPN (Reverse Polish Notation Calculator)

This java program serves to replicate a legacy system of a "Saturated 
Reverse Polish Notation (SRPN) Calculator", available on 
https://replit.com/@bathuniversity/sprn-to-emulate.
 
SRPN is a reverse Polish notation calculator equipped with an extra feature 
that arithmetic calculations are saturated, i.e. when the calculator reaches 
a maximum value that can be stored in a variable, it would stay at the 
maximum rather than wrapping around.


The SRPN calculator contains the below functions:

(A) Operands

Integer (Base 10) : Positive/negative integers under decimal numeral system.

Integer (Base 8) : Positive/negative integers under octal numeral system. To
input an integer in octal format, start with "0", e.g. "0177".

"r" : Input of a random number in int format, which comes from a list of
pseudo-random numbers via a linear additive feedback method. More on the
method can be found on https://www.mathstat.dal.ca/~selinger/random/.

==============================================================================

(B) Operator

"+" : Performing addition on two operands.

"+=" : Performing addition on two operands, pushing the result to stack, and
printing out the latest operand inputed.

"-" : Performing subtraction on two operands.

"-=" : Performing subtraction on two operands, pushing the result to stack,
and printing out the latest operand inputed.

"*": Performing multiplication on two operands.

"*=" : Performing multiplication on two operands, pushing the result to
stack, and printing out the latest operand inputed.

"/" : Performing division on two operands. A divisor of "0" will return a
division error.

"/=" : Performing division on two operands, pushing the result to stack, and
printing out the latest operand inputed.

"^" : Performing power operation on two operands (base number and exponent).
Negative number is not allowed for the exponent and will give error message.

"^=" : Performing power operation on two operands, pushing the result to
stack, and printing out the latest operand inputed.

"%" : Performing modulo operation on two operands.

"%=" : Performing modulo operation on two operands, pushing the result to
stack, and printing out the latest operand inputed.

==============================================================================

(C) Other functions

"#" : Opening/closing operator for comment for which enclosing input will not
be operated.

"d" : Display the whole stack.

"=" : Peek on the stack and display the top value of the stack.
 
"." : Detection of decimal values which will throw error when decimal values
are involved.

To use the calculator, enter operands first, then the operator, and finally
the "=" sign.

Example:

10 2 + = -> Return 12

3 3 * 4 4 * + = -> Return 25

Result generated will normally be a 32-bit int, thus values beyond the range
would be limited between -2147483648 (-2^31) and 2147483647 (2^31-1), which
are values of the minimum and maximum limits of int data type of Java.
