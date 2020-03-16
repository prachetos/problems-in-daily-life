This problem manifests itself in several forms
1. Given an orchard of oranges (m x n 2D matrix), one or more oranges go bad on day 1. 
On day 2 , the infected oranges will infect neighbours (diagonal/top/left/right/bottom)
Find out the time required to infect all oranges.
Link: https://leetcode.com/problems/rotting-oranges/

Input - 
1 0 1 
0 0 0 
1 1 1
(2-> bad orange, 1- good orange, 0- blank)

Output - number

Instead of orange, it can be anything else, for example fire spreading to adjacent plots,
or servers providing updates to other servers etc. 

2. Given a field of cell phone towers, find the max distance of any user from the cell tower.
Field is modelled as a 2D matrix, and cell towers are marked as 1 and users as 0.


Optimisation problems on this:
1. Given a field of cell towers, position cell towers so that 
distance is minimum 
 - Brute force -> generate all possible combinations, and compute max in each case. 
   Choose min scnario. 

 - Can we use DP ?


Uses:
Search, BFS 

Commonly asked by e-commerce companies

I will try to solve this question in 3 languages, just to 
see the syntactical changes in each of them.