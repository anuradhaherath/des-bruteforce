package anuradha.ac.security;

import java.util.Arrays;

public class DESAlgorithm {

	private int inputtext [] = new int[8];
	private int outputtext [] = new int[8];
	private int key [] = new int[4];		
	private int noofrounds = 0;	
	private int lefthalf [] = new int[4];
	private int righthalf [] = new int[4];

	public DESAlgorithm(int [] inputtext, int [] key, int noofrounds){

		//Initialization of values
		this.inputtext = inputtext;
		this.key = key;
		this.noofrounds = noofrounds;
	}

	//Performing the encryption process
	public int[] initiate(){

		//Separate the array in to 2 halves
		lefthalf  = Arrays.copyOfRange(inputtext,0,inputtext.length/2);
		righthalf  = Arrays.copyOfRange(inputtext,inputtext.length/2,inputtext.length);				

		//Performing the encryption function for the specified no of rounds
		for(int count=0; count<noofrounds; count++){		

			loop();
		}

		//Interchanging the lefthalf and righthalf		
		int [] temp = lefthalf;
		lefthalf = righthalf;
		righthalf = temp;

		//Obtaining the outputtext
		System.arraycopy(lefthalf, 0, outputtext, 0, 4);
		System.arraycopy(righthalf, 0, outputtext, 4, 4);

		return outputtext;	
	}

	//Performing the DES function for the specified no of rounds
	public void loop(){

		//Performing the round function
		int [] rounded = roundFunction(righthalf,key);		

		//Performing the xor operation
		int [] output = xorFunction(lefthalf,rounded);	

		//Initializing values for next round
		lefthalf = righthalf;
		righthalf = output;

	}

	//Round function involves in XOR Operation of key with right half of input text
	public int[] roundFunction(int righthalf [], int key []){

		int result [] = new int[righthalf.length];

		for (int count=0; count<righthalf.length; count++){

			result [count] = righthalf[count] ^ key[count];;
		}
		return (result);
	}

	//XOR Operation for the rounded string and the left half 
	public int[] xorFunction(int lefthalf [], int rounded []){

		int result [] = new int[lefthalf.length];

		for (int count=0; count<lefthalf.length; count++){

			result [count] = lefthalf[count] ^ rounded[count];
		}
		return (result);		
	}

}
