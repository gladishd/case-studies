'''
   linereader.py
   Jeff Ondich, 2013-01-04
   Anna Rafferty, 2016-08-24 (conversion to python3)
   Layla Oesper, 2017-08-30

   Reads lines from a file, prints them in upper-case, and prints
   a count of the lines at the end.
   
   Intended as the Python half of parallel examples in Python and
   Java. See LineReader.java.
   
   Try a few things:
 
   1. Create a small text file called somefile.txt, put it in the same
      directory as linereader.py, and then run linereader.py.
      ("python3 linereader.py")
  
   2. What happens when you change the inputFilePath variable to 'nonexistent.txt'?
   
'''
import sys

inputFilePath = 'somefile.txt'
try:
    inputFile = open(inputFilePath)
except Exception as e:
    print('Cannot open', inputFilePath, file=sys.stderr)
    exit()

numberOfLines = 0
for line in inputFile:
    print(line.upper(), end='')
    numberOfLines += 1
print('\nNumber of lines in file:', numberOfLines)