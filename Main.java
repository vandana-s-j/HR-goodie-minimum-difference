import java.util.*;
import java.io.*;
class Goodie {
  String name;
  int price;
//Creating a constructor inorder to initialize the state of an object
  public Goodie(String name, int price) {
    this.name = name;
    this.price = price;
  }
// toString method to return the input string in the format name:price
  public String toString() { 
      return this.name + ": " + this.price;
  }
}
//Main class
public class Main {
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("sample_input.txt");       
    Scanner sc=new Scanner(fis);
    int no_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();
 // Creating an ArrayList  of Element class and named it as goodies_items
    ArrayList<Goodie> goodies_items = new ArrayList<Goodie>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Goodie(current[0], Integer.parseInt(current[1])));
    }
    sc.close();
    // sorting 
    Collections.sort(goodies_items, new Comparator<Goodie>(){
    //compare function to return the difference between the price of any two goodies
      public int compare(Goodie a, Goodie b) { 
        return a.price - b.price; 
      } 
    });
// finding minimum difference and assigning it to a variable minimum_diff
    int mindifference = goodies_items.get(goodies_items.size()-1).price;
    int minimum_index = 0;
    for(int i=0;i<goodies_items.size()- no_of_employees+1;i++) {
// finding actual difference 
      int diff = goodies_items.get(no_of_employees+i-1).price-goodies_items.get(i).price;
//comparing both minimum difference and difference
      if(diff<=mindifference) {
// if the actual difference is less than or equal to minimum difference then set minimum difference as actual difference
        mindifference = diff;
        minimum_index = i;
      }
    }
    
    
// process of writing to a output file 
    FileWriter fw = new FileWriter("sample_output.txt");
    fw.write("The goodies selected for distribution are:\n");
    for(int i=minimum_index;i<minimum_index + no_of_employees; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + mindifference);
// closing the output file 
   fw.close();
  }
}
