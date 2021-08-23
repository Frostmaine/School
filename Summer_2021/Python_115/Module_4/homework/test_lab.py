import lab

good = 4388576018410707
bad = 4246345689049834

def test_isValid():
    assert lab.isValid(bad) == False
    assert lab.isValid(good) == True

def test_sumOfDoubleEvenPlace():
    assert lab.sumOfDoubleEvenPlace(good) == 37
    assert lab.sumOfDoubleEvenPlace(bad) == 63


