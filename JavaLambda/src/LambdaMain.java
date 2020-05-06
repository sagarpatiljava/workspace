import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;



public class LambdaMain {

	List <String> nameList = Arrays.asList("sagar","jayshree","shrey","sarang","patil");
	
	List<Person> personList = new ArrayList<Person>();
	
	LambdaMain(){
		personList.add(new Person("sagar","pune",917428));
		personList.add(new Person("Ashutosh","Urai",915442));
		personList.add(new Person("Vivek","Japan",814837));
		personList.add(new Person("Chandra","Japan",81702));
		personList.add(new Person("Sarang","India",758800));
	}
	
	
	
	
	private void greet() {
		System.out.println("Hello world");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//RandomeFunction randomFunction = () -> {System.out.println("This is random function"); };
	RandomeFunction addNumber = (int a,int b) -> a+b;
	
	System.out.println(addNumber.myFunction(1, 2));
     LambdaMain lambdaMain = new LambdaMain();
     
     System.out.println("Before sort :");
     lambdaMain.personList.stream().forEach(x->System.out.println(x.toString()));
	
	//Sorting list by name 
	Collections.sort(lambdaMain.personList,new Comparator<Person>() {

		@Override
		public int compare(Person o1, Person o2) {
			// TODO Auto-generated method stub
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
		
	});
	
	System.out.println("After sort :");
	  lambdaMain.personList.stream().forEach(x->System.out.println(x.toString()));
		
	System.out.println("Pring persons with namestarts with s");
	lambdaMain.printConditional(lambdaMain.personList,  p-> p.getName().startsWith("s"),p-> System.out.println(p.toString()));
	
	}

	public void printConditional(List<Person> persons,Predicate<Person> cond,Consumer<Person> consume) {
		
		for(Person per :persons) {
			
			if(cond.test(per)) {
				consume.accept(per);
			}
		}
	}
}
