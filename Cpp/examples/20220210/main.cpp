#include "bag.h"
#include <iostream>

using namespace std;

void repl(void);

int main() {

  repl();
  return EXIT_SUCCESS;
}

void repl(void) {
  bag items;
  while (true) {
    cout << "[irpsoP?q] ";
    char cmd;
    cin >> cmd;
    switch (cmd) {
    case 'i': {
      int value;
      cin >> value;
      items.insert(value);
      continue;
    }
    case 'r': {
      int value;
      cin >> value;
      items.remove(value);
      continue;
    }
    case 'p': {
      int index;
      cin >> index;
      cout << items.peek(index) << endl;
      continue;
    }
    case 's': {
      cout << items.size() << endl;
      continue;
    }
    case 'o': {
      int target;
      cin >> target;
      cout << items.occurences(target) << endl;
      continue;
    }
    case 'q': {
      return;
    }
    case 'P': {
      cout << items;
      continue;
    }
    }
  }
}
