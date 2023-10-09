package ui.testers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ui.Column;
import ui.TableUI;

class Person {

  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}

class PersonTable extends TableUI<Person> {

  class PersonNameColumn implements Column<Person> {

    public String getColumnName() {
      return "Name";
    }

    public String getValue(Person person) {
      return person.getName();
    }
  }

  class PersonAgeColumn implements Column<Person> {

    public String getColumnName() {
      return "Age";
    }

    public String getValue(Person person) {
      return String.valueOf(person.getAge());
    }
  }

  @Override
  public List<Column<Person>> getColumns() {
    List<Column<Person>> personColumns = Arrays.asList(
      new PersonNameColumn(),
      new PersonAgeColumn()
    );
    return personColumns;
  }

  public PersonTable(List<Person> data) {
    super(data);
  }
}

public class TableUITester {

  public static void main(String[] args) {
    List<Person> people = Arrays.asList(
      new Person("Alice", 30),
      new Person("Bob", 40),
      new Person("Charlie", 50)
    );

    PersonTable personTable = new PersonTable(people);
    personTable.render();
    System.out.println(personTable.selectItem(new Scanner(System.in)).getName());
  }
}