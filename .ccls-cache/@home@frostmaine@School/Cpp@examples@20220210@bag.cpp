#include "bag.h"
#include <ostream>

bag::bag() { count = 0; }

void bag::insert(int const item) {
  if (count >= 20)
    return;
  contents[count++] = item;
}

void bag::remove(int const target) {
  for (int i = count; i > 0; i--) {
    if (contents[i] == target && i) {
      for (int j = i; j < CAPACITY - 1; j++) { // shifting loop
        contents[j] = contents[j + 1];
      }
      count--;
      return;
    } else if (i == CAPACITY - 1) {
      contents[i] = 0;
      count--;
    }
  }
}

int bag::peek(int const index) { return index > count ? -1 : contents[index]; }

size_t bag::size() const { return count; }

size_t bag::occurences(int const target) const {
  size_t sum = 0;
  for (int i = 0; i < count; i++)
    if (contents[i] == target)
      sum++;
  return sum;
}

std::ostream &operator<<(std::ostream &out, bag &src) {
  std::string str = "[";
  for (int i = 0; i < src.size(); i++)
    str += " " + std::to_string(src.peek(i));
  str += " ]\n";
  out << str;
  return out;
}
