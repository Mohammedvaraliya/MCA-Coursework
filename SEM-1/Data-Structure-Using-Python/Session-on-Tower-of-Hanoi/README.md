# Tower of Hanoi Explanation

## Problem Statement

The **Tower of Hanoi** is a mathematical puzzle where we have three towers (pegs) and a certain number of disks of different sizes. The goal is to move all disks from the source tower to the destination tower with the help of an auxiliary tower, following these rules:

1. Only one disk can be moved at a time.
2. A larger disk cannot be placed on top of a smaller disk.
3. Only the top disk of a tower can be moved at any time.

For $n$ disks, the solution requires moving the disks in a specific sequence. This puzzle is commonly solved using **recursion** due to its repetitive nature.

### **Approach 1 (`tower_of_hanoi_approach1`)**

- In the Tower of Hanoi problem, we have 3 pegs: source, destination, and auxiliary.
- **The `temp` variable** is used to calculate the auxiliary peg.  
  The calculation `temp = 6 - source - destination` works because the sum of the three peg numbers (1 + 2 + 3) is always 6. So, if we know the `source` and `destination` pegs, we can calculate the remaining peg as the auxiliary peg.

For example, if:

- `source = 1` and `destination = 3`, then `temp = 6 - 1 - 3 = 2`.
- `source = 2` and `destination = 1`, then `temp = 6 - 2 - 1 = 3`.

#### **Walkthrough of `tower_of_hanoi_approach1(1, 3, 3)`**:

Here, We are trying to move 3 disks from peg 1 (source) to peg 3 (destination) using peg 2 as the auxiliary.

1. **Initial Call**: `tower_of_hanoi_approach1(1, 3, 3)`

   - `source = 1`, `destination = 3`, `numOfDisk = 3`
   - `temp = 6 - 1 - 3 = 2`
   - We first need to move `n-1 = 2` disks from source (peg 1) to auxiliary (peg 2), using peg 3 as temporary storage.

2. **First Recursive Call**: `tower_of_hanoi_approach1(1, 2, 2)`

   - `source = 1`, `destination = 2`, `numOfDisk = 2`
   - `temp = 6 - 1 - 2 = 3`
   - Now, move `n-1 = 1` disk from peg 1 to peg 3 (using peg 2 as auxiliary).

3. **Second Recursive Call**: `tower_of_hanoi_approach1(1, 3, 1)`

   - `source = 1`, `destination = 3`, `numOfDisk = 1`
   - **Base case**: Move disk 1 from peg 1 to peg 3.
   - Output: `Move Disk from 1 to 3` (this corresponds to the first move in the output)
   - Return: `1`

4. **Back to First Recursive Call**:

   - We now move the 2nd disk from peg 1 to peg 2.
   - Output: `Move Disk from 1 to 2` (this corresponds to the second move in the output)

5. **Third Recursive Call**: `tower_of_hanoi_approach1(3, 2, 1)`

   - `source = 3`, `destination = 2`, `numOfDisk = 1`
   - **Base case**: Move disk 1 from peg 3 to peg 2.
   - Output: `Move Disk from 3 to 2` (this corresponds to the third move in the output)
   - Return: `1`

6. **Back to Initial Call**:

   - Now, move the largest disk (disk 3) from peg 1 to peg 3.
   - Output: `Move Disk from 1 to 3` (this corresponds to the fourth move in the output)

7. **Fourth Recursive Call**: `tower_of_hanoi_approach1(2, 3, 2)`

   - `source = 2`, `destination = 3`, `numOfDisk = 2`
   - `temp = 6 - 2 - 3 = 1`
   - Move `n-1 = 1` disk from peg 2 to peg 1 (using peg 3 as auxiliary).

8. **Fifth Recursive Call**: `tower_of_hanoi_approach1(2, 1, 1)`

   - `source = 2`, `destination = 1`, `numOfDisk = 1`
   - **Base case**: Move disk 1 from peg 2 to peg 1.
   - Output: `Move Disk from 2 to 1` (this corresponds to the fifth move in the output)
   - Return: `1`

9. **Back to Fourth Recursive Call**:

   - Now, move disk 2 from peg 2 to peg 3.
   - Output: `Move Disk from 2 to 3` (this corresponds to the sixth move in the output)

10. **Sixth Recursive Call**: `tower_of_hanoi_approach1(1, 3, 1)`
    - `source = 1`, `destination = 3`, `numOfDisk = 1`
    - **Base case**: Move disk 1 from peg 1 to peg 3.
    - Output: `Move Disk from 1 to 3` (this corresponds to the seventh move in the output)
    - Return: `1`

---

### **Approach 2 (`tower_of_hanoi_approach2`)**

### **Walkthrough of `tower_of_hanoi_approach2(3, 'Source (1)', 'Auxiliary (2)', 'Destination (3)')`**:

Here, We are trying to move 3 disks from `Source (1)` to `Destination (3)` using `Auxiliary (2)` as the temporary peg.

1. **Initial Call**: `tower_of_hanoi_approach2(3, 'Source (1)', 'Auxiliary (2)', 'Destination (3)')`

   - Move `n-1 = 2` disks from source (`1`) to auxiliary (`2`), using destination (`3`) as temporary storage.

2. **First Recursive Call**: `tower_of_hanoi_approach2(2, 'Source (1)', 'Destination (3)', 'Auxiliary (2)')`

   - Move `n-1 = 1` disk from source (`1`) to destination (`3`), using auxiliary (`2`) as temporary storage.

3. **Second Recursive Call**: `tower_of_hanoi_approach2(1, 'Source (1)', 'Auxiliary (2)', 'Destination (3)')`

   - **Base case**: Move disk 1 from source (`1`) to destination (`3`).
   - Output: `Move disk 1 from Source (1) to Destination (3)` (this corresponds to the first move in the output)
   - Return: `1`

4. **Back to First Recursive Call**:

   - Now move disk 2 from source (`1`) to auxiliary (`2`).
   - Output: `Move disk 2 from Source (1) to Auxiliary (2)` (this corresponds to the second move in the output)

5. **Third Recursive Call**: `tower_of_hanoi_approach2(1, 'Destination (3)', 'Source (1)', 'Auxiliary (2)')`

   - **Base case**: Move disk 1 from destination (`3`) to auxiliary (`2`).
   - Output: `Move disk 1 from Destination (3) to Auxiliary (2)` (this corresponds to the third move in the output)
   - Return: `1`

6. **Back to Initial Call**:

   - Now move disk 3 from source (`1`) to destination (`3`).
   - Output: `Move disk 3 from Source (1) to Destination (3)` (this corresponds to the fourth move in the output)

7. **Fourth Recursive Call**: `tower_of

\_hanoi_approach2(2, 'Auxiliary (2)', 'Source (1)', 'Destination (3)')`

- Move `n-1 = 1` disk from auxiliary (`2`) to source (`1`), using destination (`3`) as temporary storage.

8. **Fifth Recursive Call**: `tower_of_hanoi_approach2(1, 'Auxiliary (2)', 'Destination (3)', 'Source (1)')`

   - **Base case**: Move disk 1 from auxiliary (`2`) to source (`1`).
   - Output: `Move disk 1 from Auxiliary (2) to Source (1)` (this corresponds to the fifth move in the output)
   - Return: `1`

9. **Back to Fourth Recursive Call**:

   - Now move disk 2 from auxiliary (`2`) to destination (`3`).
   - Output: `Move disk 2 from Auxiliary (2) to Destination (3)` (this corresponds to the sixth move in the output)

10. **Sixth Recursive Call**: `tower_of_hanoi_approach2(1, 'Source (1)', 'Auxiliary (2)', 'Destination (3)')`
    - **Base case**: Move disk 1 from source (`1`) to destination (`3`).
    - Output: `Move disk 1 from Source (1) to Destination (3)` (this corresponds to the seventh move in the output)
    - Return: `1`

---

### **Differences Between the Two Approaches**

1. **Auxiliary Peg Calculation**:

   - **Approach 1** uses the formula `temp = 6 - source - destination` to calculate the auxiliary peg dynamically.
   - **Approach 2** passes the auxiliary peg explicitly as a parameter.

2. **Recursive Call Order**:

   - **Approach 1** has the recursive calls in the order of:
     1. Move `n-1` disks from source to auxiliary.
     2. Move the largest disk from source to destination.
     3. Move `n-1` disks from auxiliary to destination.
   - **Approach 2** has the recursive calls in a similar order but swaps the roles of the source and auxiliary in different stages.

---

### **Efficiency**

Both approaches have:

- **Time Complexity**: $O(2^n - 1)$, because each recursive call breaks the problem into subproblems, and there are $2^n - 1$ moves for `n` disks.
- **Space Complexity**: $O(n)$, since the recursion depth is proportional to the number of disks.

Both approaches are equally efficient in terms of time and space complexity, but **Approach 2** is clearer and more intuitive.
