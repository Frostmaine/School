/*
  bag():
  pre-condition: none
  post_condition: creates an empty bag with a maximum capacity of 20 items

  insert():
  pre-condition: an initialized bag object
  post-condition: the item being inserted is added to the bag, count is
  incremented

  remove():
  pre-condition: an initialized bag object that has atleast 1 item in it
  post-condition: removes the last occurence of the target item


 */

#include <iostream>
#include <stddef.h>

#ifndef _BAG_
#define _BAG_

class bag {

public:
  static const size_t CAPACITY = 20;
  bag();
  void insert(int const item);
  void remove(int const target);
  int peek(int const index);
  size_t size() const;
  size_t occurences(int const target) const;
  friend std::ostream &operator<<(std::ostream &out, bag &src);

private:
  size_t count = 0;
  int contents[CAPACITY];
};
#endif
