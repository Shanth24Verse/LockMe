package projectOOPS;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class Products implements Serializable{
	
  		String itemname;                                        	//Initializing the Variables	   
 		int itemid;
		
	  	Products( String itemname, int itemid){               	    //Initializing Constructor
	      this.itemname = itemname;
	      this.itemid = itemid;	     
	   }
	   public String toString(){
	      return itemname+" "+itemid;
	   }
	}
 

public class LockMe {                                              

	public static void main(String[] args) throws Exception {
		
		
		System.out.println("-------------Application Developer : Sk-------------------");          // Developer Details
		System.out.println("----------------------DataLog-----------------------------\n");         // APP Details
		//System.out.println("---------------------");
		
		int choice = -1;
	      Scanner s = new Scanner(System.in);                      //Initializing Scanner class for Integer     
	      Scanner s1 = new Scanner(System.in);                     //Initializing Scanner class for String
	      
	      File file = new File("Log.txt");                         // Creating File object
	      File f;
	      
	      ArrayList<Products> al = new ArrayList<Products>();      // Calling List into Product class
	      
	      ObjectOutputStream oos = null;                           
	      ObjectInputStream ois = null;
	      
	      ListIterator li = null;
	      
	      if(file.isFile()){
	          ois = new ObjectInputStream(new FileInputStream(file));
	          al = (ArrayList<Products>)ois.readObject();
	          ois.close();
	       }
	      
	      do{                                                      
				System.out.println("Choose your option");
				System.out.println("1 - Display the Current File Name");
				System.out.println("2 - Display the user interface");
				System.out.println("3 - Exit");
	          choice = s.nextInt();

	          switch(choice){
	             case 1:
	                 if(file.isFile()){
	                     ois = new ObjectInputStream(new FileInputStream(file));
	                     al = (ArrayList<Products>)ois.readObject();
	                     ois.close();

	                     Collections.sort(al, new Comparator<Products>(){        //Sorting the data
	                         public int compare(Products d1, Products d2){
	                            return d1.itemname.compareTo(d2.itemname);
	                         }  
	                      });    

	                      oos = new ObjectOutputStream(new FileOutputStream(file));
	                      oos.writeObject(al);
	                      oos.close();
	                      
	                      Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
	      				for (Path name: dirs) {
	      				    System.err.println(name);
	      				}
	                     
	                     System.out.println("-------------------------------------");
	                     li = al.listIterator();
	                     while(li.hasNext())
	                        System.out.println(li.next());
	                     

	                     
	                     System.out.println("-------------------------------------");
	                  }else{
	                     System.out.println("\nFile not Exists....!\n");
	                     
	                  }
	                break;
	                
	             case 2:
	            	 
	            	System.out.println("You are in Case 2");
	 				System.out.println("Choose the operation to do...");
	 				System.out.println("11 - Add File");
	 				System.out.println("12 - Delete File");
	 				System.out.println("13 - Search File");
	 				System.out.println("14 - Back to Main Menu");
	 				
	 				int suboption=s.nextInt();
	 				
	 				switch(suboption)
					{
	 				
	 				
	 				case 11:
	            	 
	            	 System.out.println("Enter how many Items you want to Add: ");
	                 int n = s.nextInt();
	                 for(int i=0;i<n;i++){
	                	
	                    System.out.print("Enter Item Name: ");
						String itemname = s1.nextLine().toLowerCase();
						
						f = new File(itemname + ".txt"); 
						f.createNewFile();
												
						System.out.print("Enter Item ID: ");
	                    int itemid = s.nextInt();
	                    System.out.println(itemname+ " :Created");
	                    System.out.println();
	                    al.add(new Products(itemname,itemid)); 
               
	                 }
	                 
	                 oos = new ObjectOutputStream(new FileOutputStream(file));
	                 oos.writeObject(al);
	                 oos.close();
	            	break;
	            	 
	             case 12:
	            	 if(file.isFile()){
	                     ois = new ObjectInputStream(new FileInputStream(file));
	                     al = (ArrayList<Products>)ois.readObject();
	                     ois.close();

	                     boolean found = false;
	                     System.out.print("Enter Item name to Delete : ");
	                     String itemname = s1.nextLine();
	                     System.out.println("-------------------------------------");
	                     li = al.listIterator();
	                     while(li.hasNext()){
	                    	 Products d = (Products)li.next();	                      
	                        	if(d.itemname.equals(itemname)){
	                           li.remove();
	                           found = true;
	                        }
	                     }
	                     try {
	     					Files.deleteIfExists(Paths.get(itemname+".txt"));
	     					}
	     					catch(IOException e){
	     						System.out.println("Invalid");
	     					}
	                     if(found){
	                        oos = new ObjectOutputStream(new FileOutputStream(file));
	                        oos.writeObject(al);
	                        oos.close();
	                        System.out.println("Record Deleted Successfully....!");
	                     }
	                     else{
	                        System.out.println("Record Not Found...!");                      
	                     }
	                     System.out.println("-------------------------------------");
	                  }else{
	                     System.out.println("File not Exists....!");
	                  }
	                break;
	                
	             case 13: if(file.isFile()){
	                  ois = new ObjectInputStream(new FileInputStream(file));
	                  al = (ArrayList<Products>)ois.readObject();
	                  ois.close();

	                  boolean found = false;
	                  System.out.println("Enter Item name to Search : ");
	                  String itemname = s1.nextLine();
	                  System.out.println("-------------------------------------");
	                  li = al.listIterator();
	                  while(li.hasNext()){
	                	  Products d = (Products)li.next();
	                	  if(d.itemname.equals(itemname)){
	                        System.out.println(d);
	                        found = true;
	                     }
	                  }
	                  if(!found)
	                     System.out.println("Record Not Found...!");
	                  System.out.println("-------------------------------------");
	               }else{
	                  System.out.println("File not Exists....!");
	               }
	            	break;
	             case 14:
						// Main menu
					}
					break;
	 			case 3: 
					System.out.println("Thank you for using the Application");
					return;
	          }
	       }while(true);

	}

}
