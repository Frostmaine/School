// implementation for longest()
#include "longest.h"

std::string longest(std::string a, std::string b, std::string c)
{
	if (a.length() >= b.length() && a.length() >= c.length())
		return a;
	if (b.length() >= c.length() && b.length() >= a.length())
		return b;
	return c;
}
