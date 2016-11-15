
import java.util.Arrays;

public class BruteForceAttack {

	private int plaintext [] = new int[8];
	private int ciphertext [] = new int[8];
	private int noofrounds = 0;
	private int noattempts = 0;
	private int randomkey [] = {0,0,0,0};
	private int randomtext [] = new int[8];

	public BruteForceAttack(int plaintext[],int ciphertext[], int noofrounds){
		
		this.plaintext = plaintext;
		this.ciphertext = ciphertext;
		this.noofrounds = noofrounds;
	}
	
	public void attack(){
		
		//Generating the random key
		generateKey(randomkey);		
		
		//Initializing the Encryption Object and calling initiate method
		DESAlgorithm ob = new DESAlgorithm(plaintext,randomkey,noofrounds);		
		randomtext = ob.initiate();
		
		//Checking the equality of plaintext and randomtext
		if(Arrays.equals(ciphertext, randomtext)){
			
			System.out.print("Attempt "+(noattempts+1)+": Identified Key : ");
			for(int count=0; count<randomkey.length; count++){
				
				System.out.print(randomkey [count]);		
			}

		}
		else{
			
			if(noattempts < 16){
				System.out.println("Attempt "+(noattempts+1)+": Key identification failed!");
				noattempts++;	
				attack();		
			}
			else{
				System.out.println("Error: No Key found");
				System.exit(0);
			}
			
		}
	}
	
	//Generating the random key
	public void generateKey(int randomkey[]){
		
		//Converting the integer array into a continuous string
		String input0 = Arrays.toString(randomkey).replaceAll("\\[|\\]|,|\\s", "");	
		String input1 = "0001";

		//Add binary 0001 to increment randomkey
		String str = addBinaryStrings(input0,input1);
		
		//Passing back the binary string into the integer array
		for(int count=0; count<4; count++){						
			randomkey [count] = Integer.parseInt(String.valueOf(str.charAt(count)));
		}
		
	}
	
	//Adding two binary strings
	public String addBinaryStrings(String num1, String num2){
		
		int index = num1.length()-1;	
		int flag = 0;
		
		//Creating a string builder for holding the string
		StringBuilder sb = new StringBuilder();
		
		while(index >= 0){
			
			int a =0;
			int b =0;
			
			a = num1.charAt(index)=='0'?0:1;
			b = num2.charAt(index)=='0'?0:1;
			index--;
			
			int sum = a+b+flag;
			
			if(sum >= 2){
	            sb.append(String.valueOf(sum-2));
	            flag = 1;
	        }else{
	            flag = 0;
	            sb.append(String.valueOf(sum));
	        }		
		}
	    /*if(flag == 1){
	        sb.append("1");
	    }*/	 
	    String reversed = sb.reverse().toString();
	    return reversed;
		
	}
}
