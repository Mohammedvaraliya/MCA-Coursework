def push_ele(stk):
    element = int(input("Enter the element to push onto the stack: "))
    stk.append(element)
    print(f"{element} pushed onto the stack.")
    print("Stack after push: ", stk)

def display(stk):
    print("Current Stack:", stk)

def peek(stk):
    if not stk:
        print("Stack is empty! Cannot peek.")
    else:
        top_element = stk[-1]
        print(f"Top element is: {top_element}")

def pop_ele(stk):
    if not stk:
        print("Stack is empty! Cannot pop.")
    else:
        removed_element = stk.pop()
        print(f"{removed_element} popped from the stack.")
        print("Stack after pop: ", stk)

def is_empty(stk):
    if len(stk) == 0:
      print("Stack is empty")
    else:
      print("Stack is not empty")
      print("Current Stack:", stk)

def stack_operations():
    stk = []
    while True:
        asked_string = '''
        Select the Operation:

        1. Push
        2. Display
        3. Peek
        4. Pop
        5. isEmpty
        6. Quit
        '''
        print(asked_string)

        choice = int(input("Enter your choice (1, 2, 3, 4, 5, 6): "))

        if choice == 1:
            push_ele(stk)
        elif choice == 2:
            display(stk)
        elif choice == 3:
            peek(stk)
        elif choice == 4:
            pop_ele(stk)
        elif choice == 5:
            is_empty(stk)
        elif choice == 6:
            print("Quitting the program.")
            break
        else:
            print("Invalid choice, please select (1, 2, 3, 4, 5, 6)")


if __name__ == "__main__":

    stack_operations()