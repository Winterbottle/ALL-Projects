// Another example on text file example
import java.io.FileNotFoundException;
import java.util.Random;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

class Person
{
  private String name;
  private char gender;
  private int age;

  public Person (String name,  int age, char gender)
  {
    if (age < 10 || age >= 130)
      throw new IllegalArgumentException ("Invalid age");

    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName ()
  {
    return name;
  }

  public char gender ()
  {
    return gender;
  }

  public int getAge ()
  {
    return age;
  }

  public void setInfo (String name, char gender, int age)
  {
    if (age < 10 || age >= 130)
      throw new IllegalArgumentException ("Invalid age");

    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  @Override
  public String toString ()
  {
    return String.format ("%s%n" + "%d	%c", name, age, gender);
  }
}

class CreateTextFile_2
{
  private static Formatter output;

  private static Person [ ] pArray = {new Person ("Heng 1", 28, 'M'),
          new Person ("Heng 2", 22, 'F'),
          new Person ("Heng 3", 36, 'M')};

  private static void openFile (String fileName)
  {
    try
    {
      output = new Formatter (fileName);
    }
    catch (SecurityException e)
    {
      System.err.println ("Write permission denied");
      System.exit (1);
    }
    catch (FileNotFoundException e)
    {
      System.err.println ("Error in opening the file");
      System.exit (1);
    }

    System.out.printf ("File name %s successfully opened for creation%n", fileName);
  }

  private static void createFile (String fileName)
  {
    try
    {
      for (Person p : pArray)
      {
        output.format ("%s%n", p);
        System.out.printf ("==> %s written to file%n", p.getName ());
      }
    }
    catch (FormatterClosedException e)
    {
      System.err.println ("Error in writing to file");
      System.exit (1);
    }

    System.out.println ("All information ready to write to a data file");
  }

  private static void closeFile (String fileName)
  {
    if (output != null)
    {
      output.close ();
      System.out.printf ("File name %s successfully created%n", fileName);
    }
  }

  public static void main (String [ ] args)
  {
    openFile ("infile.txt");
    createFile ("infile.txt");
    closeFile ("infile.txt");
  }
}// Another example on text file example
import java.io.FileNotFoundException;
        import java.util.Random;
        import java.lang.SecurityException;
        import java.util.Formatter;
        import java.util.FormatterClosedException;

class Person
{
  private String name;
  private char gender;
  private int age;

  public Person (String name,  int age, char gender)
  {
    if (age < 10 || age >= 130)
      throw new IllegalArgumentException ("Invalid age");

    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName ()
  {
    return name;
  }

  public char gender ()
  {
    return gender;
  }

  public int getAge ()
  {
    return age;
  }

  public void setInfo (String name, char gender, int age)
  {
    if (age < 10 || age >= 130)
      throw new IllegalArgumentException ("Invalid age");

    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  @Override
  public String toString ()
  {
    return String.format ("%s%n" + "%d	%c", name, age, gender);
  }
}

class CreateTextFile_2
{
  private static Formatter output;

  private static Person [ ] pArray = {new Person ("Heng 1", 28, 'M'),
          new Person ("Heng 2", 22, 'F'),
          new Person ("Heng 3", 36, 'M')};

  private static void openFile (String fileName)
  {
    try
    {
      output = new Formatter (fileName);
    }
    catch (SecurityException e)
    {
      System.err.println ("Write permission denied");
      System.exit (1);
    }
    catch (FileNotFoundException e)
    {
      System.err.println ("Error in opening the file");
      System.exit (1);
    }

    System.out.printf ("File name %s successfully opened for creation%n", fileName);
  }

  private static void createFile (String fileName)
  {
    try
    {
      for (Person p : pArray)
      {
        output.format ("%s%n", p);
        System.out.printf ("==> %s written to file%n", p.getName ());
      }
    }
    catch (FormatterClosedException e)
    {
      System.err.println ("Error in writing to file");
      System.exit (1);
    }

    System.out.println ("All information ready to write to a data file");
  }

  private static void closeFile (String fileName)
  {
    if (output != null)
    {
      output.close ();
      System.out.printf ("File name %s successfully created%n", fileName);
    }
  }

  public static void main (String [ ] args)
  {
    openFile ("infile.txt");
    createFile ("infile.txt");
    closeFile ("infile.txt");
  }
}