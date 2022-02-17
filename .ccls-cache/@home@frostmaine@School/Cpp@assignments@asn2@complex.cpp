/*
  Matthew Yackiel
  02-15-2022
  Asn 2
*/
#include "complex.h"
#include <cmath>
#include <iomanip>
#include <string>


namespace complex_number { // I get a link error if I dont put friend functions implementation in the namespace

complex& operator+(const complex *a, const complex *b) {
    double real = a->real + b->real;
    double imag = b->real + b->imag;
    return *(new complex(real, imag));
}

complex& operator-(const complex *a, const complex *b) {
    double real = a->real - b->real;
    double imag = a->imag - b->imag;
    return *(new complex(real, imag));
}

complex& operator*(const complex *a, const complex *b) {
    double real = a->real * b->real - a->imag * b->imag;
    double imag = a->imag * b->real + a->real * b->imag;
    return *(new complex(real, imag));
}

complex& operator*(double const a, const complex *b) {
    double real = b->real * a;
    double imag = b->imag * a;
    return *(new complex(real, imag));
}

complex& operator*(const complex *a, double const b) {
    double real = a->real * b;
    double imag = a->imag * b;
    return *(new complex(real, imag));
}

complex& operator/(complex const *a, complex const *b) {
    double real_temp = a->real * b->real + a->imag * b->imag;
    real_temp /= pow(b->real, 2) - pow(b->imag, 2);
    double imag_temp = a->imag * b->real - a->real * b->imag;
    imag_temp /= pow(b->real, 2) - pow(b->imag, 2);
    return *(new complex(real_temp, imag_temp));
}

bool complex::operator==(complex const *b) const {
    return fabs(this->real - b->real) <= 0.01f
	&& fabs(this->imag - b->imag) <= 0.01f;
}
    
complex& complex::operator!() const {
    return *(new complex (this->real * -1, this->imag * -1));
}

double complex::magnitude() const {
  return sqrt(pow(this->real, 2) + pow(this->imag, 2));
}


std::ostream &operator<<(std::ostream& out, const complex* a) {
    std::string temp = (a->imag < 0) ? " - " : " + ";
    out << "(" << a->real << temp << ((a->imag < 0) ? (-1 * a->imag) : (a->imag)) << "i)"
	<< std::setprecision(2);
    return out;
}

std::istream &operator>>(std::istream& in, complex* a) {
    double real, imag;
    in >> real >> imag;
    a->real = real;
    a->imag = imag;
    return in;
}
    
void complex::&operator=(const complex *other) {
    this = new complex(other);
}
}

