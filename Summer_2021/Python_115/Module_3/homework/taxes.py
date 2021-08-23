'''
  Matthew Yackiel
  Python 115
  Module 3 Lab
  6-13-2021
''' 

status = -1

while status not in range(4):
    status = int(input("Enter your filing status: Single(0), Married Filing Jointly(1), Married Filing Seperate(2), Head of Household(3): "))

income = -1

while income < 0:
    income = float(input("Enter your taxable income: "))

taxes = 0.0

if status == 0:
    # Filing Single    while income > 0:
    if income > 372950:
        taxes += (income - 372951) * .35
        income = 372951
    if income > 171550:
        taxes += (income - 171551) * .33
        income = 171551
    if income > 82250:
        taxes += (income - 82251) * .28
        income = 82251
    if income > 33950:
        taxes += (income - 33951) * .25
        income = 33951
    if income > 8350:
        taxes += (income - 8351) * .15
        income = 8351
    taxes += income * .10

if status == 1:
    # married filing jointly
    if income > 372950:
        taxes += (income - 372951) * .35
        income = 372951
    if income > 208850:
        taxes += (income - 208851) * .33
        income = 208851
    if income > 137050:
        taxes += (income - 137051) * .28
        income = 137051
    if income > 67900:
        taxes += (income - 67901) * .25
        income = 67901
    if income > 16700:
        taxes += (income - 16701) * .15
        income = 16701
    taxes += income * .10

if status == 2:
    # Married Filing Seperately
    if income > 186475:
        taxes += (income - 186476) * .35
        income = 186476
    if income > 104425:
        taxes += (income - 104426) * .33
        income = 104426
    if income > 68525:
        taxes += (income - 68526) * .28
        income = 68526
    if income > 33950:
        taxes += (income - 33951) * .25
        income = 33951
    if income > 8350:
        taxes += (income - 8351) * .15
        income = 8351
    taxes += income * .10

if status == 3:
    #  Head of Household
    if income > 372950:
        taxes += (income - 372951) * .35
        income = 372951
    if income > 190200:
        taxes += (income - 190201) * .33
        income = 190201
    if income > 117450:
        taxes += (income - 117451) * .28
        income = 117451
    if income > 45500:
        taxes += (income - 45501) * .25
        income = 45501
    if income > 8350:
        taxes += (income - 8351) * .15
        income = 8351
    taxes += income * .10

print("You payed", taxes, "in taxes.")
