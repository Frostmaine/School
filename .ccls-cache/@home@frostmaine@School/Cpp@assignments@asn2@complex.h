/*
  Matthew Yackiel
  2-14-2022
  Assignment 2
 */
/*
  complex() :
  pre-condition: none
  post-condition: a complex number object initialized as 1 + i

  complex(double a, double b) :
  pre-condition: 2 double values passed as parameters
  post-condition: a complex number intitialized as a + bi

  operator + :
  pre-condition: 2 initialized complex number objects
  post-condition: performs complex number addition on the complex number objects
    returns a complex number containing the sum

  operator - :
  pre-condition: 2 initialized complex number objects
  post-condition;  performs complex number subtraction on the complex number
  objects returns a complex number containing the difference

  operator * (complex, complex) :
  pre-condition: 2 initialized complex number objects
  post-condition: performs complex number multiplication on the complex number
  objects returns a complex number containing the product

  operator * (complex, double) & opertor * (double, complex) :
  pre-condition: an initialized complex number object and a double value
  post-condition: both methods perform scalar multiplication on the complex
  number returns a complex number containing the product between the double
  value and the complex value

  operator / :
  pre-condition: 2 initialized complex numbers, the second one being non-zero
  !(0 + 0i) post-condition: performs complex division on both complex numbers
    returns a complex number containing the quotient

  operator ! :
  pre-condition: an initialized complex number
  post-condition: performs complex negation
    returns a complex number containing the negated value

  operator == :
  pre-condition: 2 initialized complex number objects
  post-condition: compares the 2 complex numbers for equality, true if both have
  the same value, false otherwise

  operator << :
  pre-condition: an osteram, and an initialized complex number
  post-conditiion: returns an ostream object that has been passed a complex
  number as output

  operator >> :
  pre-condition: a input stream, and acomplex number object
  post-condition: takes input from the input stream in the form of 2 doulbe
  values using these values to construct a complex number object

  magnitude() :
  pre-condition: an initialized complex number
  post-condition: returns the magnitude of the complex number
 */

#ifndef _COMPLEX_
#define _COMPLEX_

#include <istream>
#include <ostream>
#include <string>

namespace complex_number {

class complex {

private:
  double real, imag;

public:
  inline complex() {
    real = 1;
    imag = 1;
  }
  inline complex(double const real, double const imag) {
    this->real = real;
    this->imag = imag;
  }
  inline complex(const complex *cp) {
      this->real = cp->real;
      this->imag = cp->imag;
  }
  complex& operator!() const;
  double magnitude(void) const;
  friend complex &operator+(const complex *, const complex *);
  friend complex &operator-(const complex *, const complex *);
  friend complex &operator*(const complex *, const complex *);
  friend complex &operator*(const double *, const complex *);
  friend complex &operator*(const complex *, const double);
  friend complex &operator/(const complex *, const complex *);
  void operator=(const complex *);
  bool operator==(const complex);
  friend std::ostream &operator<<(std::ostream &, complex &);
  friend std::istream &operator>>(std::istream &, complex &);
};
} // namespace complex_number

#endif
