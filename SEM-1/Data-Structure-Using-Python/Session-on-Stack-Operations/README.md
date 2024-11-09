# Stack Operation Explanation

## Problem Statement

To implement basic stack operations (Push, Pop, Peek, and IsEmpty) using Python, which will allow us to manage data in a Last-In-First-Out (LIFO) order.

A stack is a linear data structure that operates in a LIFO (Last-In-First-Out) manner. This means the last element added to the stack is the first one to be removed. Stacks have several key operations:

1. **Push**: Adds an element to the top of the stack.
2. **Pop**: Removes the top element from the stack.
3. **Peek/Top**: Returns the top element without removing it.
4. **IsEmpty**: Checks if the stack is empty.

**Stack Use Cases**:
Stacks are used in various applications, such as:

1. Reversing strings
2. Managing function calls (call stack)
3. Validating expressions (like parentheses balancing)
4. Implementing undo/redo functionality

### **Program Walkthrough** with Example Operations

I'll simulate the behavior of the program as if it were being run interactively.

---

### **Step 1: Start the Program**

When the program starts, the function `stack_operations()` is called, initializing an empty list `stk` to represent the stack:

```python
stk = []
```

The program then enters an infinite loop, awaiting the user's input to choose one of the stack operations.

---

### **Step 2: Display Menu to User**

The program displays the following menu to the user:

```plaintext
Select the Operation:

1. Push
2. Display
3. Peek
4. Pop
5. isEmpty
6. Quit
```

---

### **Step 3: User Chooses to Push Elements**

#### **User Input**: `1` (Push operation)

The program then asks for the **element** to push onto the stack:

```python
element = int(input("Enter the element to push onto the stack: "))
```

Let’s assume the user enters `10`.

- The program then appends `10` to the stack using `stk.append(10)`.
- The stack after this push operation becomes: `[10]`.

The program prints the following:

```plaintext
10 pushed onto the stack.
Stack after push:  [10]
```

Now, the stack contains one element: `[10]`.

---

### **Step 4: Push More Elements**

#### **User Input**: `1` (Push operation again)

The program asks for another **element** to push onto the stack. Let’s assume the user enters `20`.

- The program appends `20` to the stack using `stk.append(20)`.
- The stack after this push operation becomes: `[10, 20]`.

The program prints:

```plaintext
20 pushed onto the stack.
Stack after push:  [10, 20]
```

Now, the stack contains two elements: `[10, 20]`.

#### **User Input**: `1` (Push operation again)

The program asks for another **element** to push. Let’s assume the user enters `30`.

- The program appends `30` to the stack using `stk.append(30)`.
- The stack after this push operation becomes: `[10, 20, 30]`.

The program prints:

```plaintext
30 pushed onto the stack.
Stack after push:  [10, 20, 30]
```

Now, the stack contains three elements: `[10, 20, 30]`.

#### **User Input**: `1` (Push operation again)

The program asks for another **element** to push. Let’s assume the user enters `40`.

- The program appends `40` to the stack using `stk.append(40)`.
- The stack after this push operation becomes: `[10, 20, 30, 40]`.

The program prints:

```plaintext
40 pushed onto the stack.
Stack after push:  [10, 20, 30, 40]
```

Now, the stack contains four elements: `[10, 20, 30, 40]`.

---

### **Step 5: Display the Current Stack**

#### **User Input**: `2` (Display operation)

The program simply prints the current stack:

```plaintext
Current Stack: [10, 20, 30, 40]
```

---

### **Step 6: Peek the Top Element**

#### **User Input**: `3` (Peek operation)

The program checks if the stack is empty. Since the stack is not empty, it displays the top element, which is the last element pushed (40):

```plaintext
Top element is: 40
```

---

### **Step 7: Pop the Top Element**

#### **User Input**: `4` (Pop operation)

The program checks if the stack is empty. Since the stack is not empty, it removes and returns the top element (40) using `stk.pop()`.

- The program prints that `40` is popped from the stack and shows the updated stack:

```plaintext
40 popped from the stack.
Stack after pop:  [10, 20, 30]
```

Now, the stack contains three elements: `[10, 20, 30]`.

---

### **Step 8: Peek Again After Pop**

#### **User Input**: `3` (Peek operation)

The program again checks if the stack is empty. Since it is not empty, it displays the new top element, which is `30`:

```plaintext
Top element is: 30
```

---

### **Step 9: Pop Another Element**

#### **User Input**: `4` (Pop operation)

The program checks if the stack is empty. Since the stack is not empty, it removes and returns the top element (30).

- The program prints that `30` is popped from the stack and shows the updated stack:

```plaintext
30 popped from the stack.
Stack after pop:  [10, 20]
```

Now, the stack contains two elements: `[10, 20]`.

---

### **Step 10: Check if Stack is Empty**

#### **User Input**: `5` (isEmpty operation)

The program checks if the stack is empty. Since the stack contains two elements, it prints that the stack is not empty:

```plaintext
Stack is not empty
Current Stack: [10, 20]
```

---

### **Step 11: Pop Remaining Elements**

#### **User Input**: `4` (Pop operation)

The program checks if the stack is empty. Since the stack is not empty, it removes and returns the top element (20).

- The program prints that `20` is popped from the stack and shows the updated stack:

```plaintext
20 popped from the stack.
Stack after pop:  [10]
```

Now, the stack contains one element: `[10]`.

#### **User Input**: `4` (Pop operation again)

The program checks if the stack is empty. Since the stack is not empty, it removes and returns the top element (10).

- The program prints that `10` is popped from the stack and shows the updated stack:

```plaintext
10 popped from the stack.
Stack after pop:  []
```

Now, the stack is empty: `[]`.

---

### **Step 12: Check if Stack is Empty Again**

#### **User Input**: `5` (isEmpty operation)

The program checks if the stack is empty. Since the stack is now empty, it prints:

```plaintext
Stack is empty
```

---

### **Step 13: Quit the Program**

#### **User Input**: `6` (Quit operation)

The program exits the loop and prints:

```plaintext
Quitting the program.
```

The program ends.

---
