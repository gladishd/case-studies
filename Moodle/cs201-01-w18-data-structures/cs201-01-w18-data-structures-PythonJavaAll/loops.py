'''
   loops.py
   Jeff Ondich, 2013-01-05
   Anna Rafferty, 2016-08-24 (conversion to python3)

   Demonstrates a few simple loops.
   
   Intended as the Python half of parallel examples in Python and
   Java. See Loops.java.
'''

print('Counting up with a while loop')
k = 0
while k < 5:
    print(k)
    k += 1

print('Counting up with a for/in loop')
for k in range(5):
    print(k)

print('Counting down with a for/in loop')
for k in range(5, 0, -1):
    print(k)