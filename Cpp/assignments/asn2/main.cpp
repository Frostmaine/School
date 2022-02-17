/*
  Matthew Yackiel
  02-14-2022
  Asn 2
*/
#include "complex.h"
#include <iomanip>
#include <iostream>
#include <sstream>

using namespace std;

void repl(void);

/*
  Runs a repl loop that expects input in the format Operation operand
  <optional>operand
 */
int main(int argc, char **argv) {

  repl();
  return EXIT_SUCCESS;
}

// trim an trailing i's or j's off of string
void trim_last(string &str) {
  while (str.back() == 'i' || str.back() == 'j') {
    str.pop_back();
  }
}

bool single_arg(char cmd) { return cmd == '!' || cmd == 'm'; }

bool double_arg(char cmd) {
  return cmd == '+' || cmd == '-' || cmd == '*' || cmd == '/' || cmd == '=';
}

void repl() {
  cout << "Please input a command followed by a space seperated list of " << 
          "opperands (cmd_char op1 op2 op3 op4): " << endl;
  cout << "Press h for the list of commands" << endl;
  string cmd, token;
  complex_number::complex first;
  complex_number::complex second;

  // take input line by line
  while (getline(cin, cmd)) {
    stringstream line(cmd);

    // read token by token
    line >> token;
    char c = token.front();

    // parse arguments
    if (single_arg(c)) {
      line >> first;
    } else if (double_arg(c)) {
      line >> first;
      line >> second;
    }

    switch (c) {
    case '+': {
      complex_number::complex sum = first + second;
      cout << first << " + " << second << " = " << sum << endl;
      break;
    }
    case '-': {
      complex_number::complex difference = first - second;
      cout << first << " - " << second << " = " << difference << endl;
      break;
    }
    case '*': {
      complex_number::complex product = first * second;
      cout << first << " - " << second << " = " << product << endl;
      break;
    }
    case '/': {
      if (second == complex_number::complex(0, 0))
        cout << "No dividing by zero! Try again" << endl;
      else {
        complex_number::complex quotient = first / second;
        cout << first << " - " << second << " = " << quotient << endl;
      }
      break;
    }
    case '=': {
	cout << first << " == " << second << "?  " << (first == second) << endl;
      break;
    case 'm':
	cout << "The magnitude of " << first << " is " << first.magnitude() << endl;
      break;
    }
    case '!': {
      complex_number::complex negate = !first;
      cout << "The negation of " << first << " is " << negate << endl;
    }
    case 'h': {
      cout << "List of commands and their number of operands:\n" << endl
           << "+ a1 a2 b1 b2 : addition\n- a1 a2 b1 b2 : subtraction\n* a1 a2 "
              "b1 b2 : multiplication\n"
           << "/ a1 a2 b1 b2 : division (b values cannot both be zero\n== a1 "
              "a2 b1 b2 : equality\n)"
           << "! a1 a2 : negation\nm a1 a2 : magnitude" << endl;
           break;
    }
    default: {
      return;
    }
    } // end switch
    cout << "Please input another command or press enter to exit" << endl;
  }
}
