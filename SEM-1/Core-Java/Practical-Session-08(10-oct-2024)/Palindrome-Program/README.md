## Problem Statement

**Write a java program to create a user defined package and test whether the given package is palindrome or not.**

---

### The process of executing the Palindrome Program, which is using the custom-created package.

Need to compile the Java files while ensuring the output goes to the `Class-Files` directory.

```bash
javac -d "Class-Files" palindrome/PalindromeChecker.java
```

```bash
javac -d "Class-Files" TestPalindrome.java
```

### Run the Program to get the Output.

Execute the `TestPalindrome` class from the `Class-Files` directory:

```bash
java -cp "Class-Files" TestPalindrome
```
