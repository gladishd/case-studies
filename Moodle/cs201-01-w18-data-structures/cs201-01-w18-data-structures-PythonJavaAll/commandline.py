'''
   commandline.py
   Jeff Ondich, 2013-01-04
   Anna Rafferty, 2016-08-24 (conversion to python3)
   Layla Oepser, 2017-08-30


   Identical to linereader.py, but takes the input file path from
   the command line rather than from a hard-coded string. Look at
   the comments for the java version for more about command line
   arguments.
   
   To run, type the following at the command line:
   "python3 linereader.py somefile.txt"
   
   Intended as the Python half of parallel examples in Python and
   Java. See CommandLine.java.
'''

# START CHANGES
# This is the part that's different from linereader.py.
import sys

if len(sys.argv) != 2:
    print('Usage: python %s inputFilePath' % sys.argv[0], file=sys.stderr)
    exit()
    
inputFilePath = sys.argv[1]
#END CHANGES

try:
    inputFile = open(inputFilePath)
except Exception as e:
    print('Cannot open', inputFilePath, sys.stderr)
    exit()

numberOfLines = 0
for line in inputFile:
    print(line.upper(), end='')
    numberOfLines += 1
print('\nNumber of lines in file:', numberOfLines)