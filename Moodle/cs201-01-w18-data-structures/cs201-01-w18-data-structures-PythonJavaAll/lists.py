'''
   lists.py
   Jeff Ondich, 2013-01-05
   Anna Rafferty, 2016-08-24 (conversion to python3)

   Demonstrates a few operations with lists.
   
   Intended as the Python half of parallel examples in Python and
   Java. See Lists.java.
'''

# Initialize
animal_list = ['goat', 'moose', 'emu', 'kudu']

# Add an item
animal_list.append('coatimundi')

# Insert an item in third position
animal_list.insert(2, 'gnat')

# Iterate over the items in the list using a for loop
print('Animals iterated using a for/in loop')
for animal in animal_list:
    print(animal)

print()
print('Animals iterated using an index')
for k in range(len(animal_list)):
    print(animal_list[k])