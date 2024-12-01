import java.io.*;

class Person implements Serializable {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class _13_SerializationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Alice", 30);

        // Serialization: Save object to a file
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assets\\person.ser"));
        out.writeObject(person);
        out.close();

        // De-serialization: Retrieve object from the file
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets\\person.ser"));
        Person deserializedPerson = (Person) in.readObject();
        in.close();

        System.out.println("Name: " + deserializedPerson.name + ", Age: " + deserializedPerson.age);
    }
}