package demos;

/** A class to store information about a Student
*  Used in module 4 of the UC San Diego MOOC Object Oriented Programming in Java
* @author Hryhorii-Shtanko
*/
public class Student extends Person
{
	public Student(String name)  {
		super(name);
	}

	@Override
	public boolean isAsleep( int hr ) // override 
	{ 
		return 2 < hr && 8 > hr; 
	}
	
	public static void main(String[] args)
	{
		Person p;
		p = new Student("Gregory");
		p.status(1);
	}
}
