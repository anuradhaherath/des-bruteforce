package anuradha.ac.security;

public class Cryptography {

	public static int plaintext [] = {0,1,0,1,1,0,1,0};
	public static int key [] = {1,0,1,1};
	public static int noofrounds = 4;
	
	private static int ciphertext [] = new int[8];
	private static int decryptedtext [] = new int[8];
	
	public static void main(String[] args) {
		
		/** DES *********************************************************************************/
		
		//Printing the plaintext
		System.out.println("********Performing DES Encyption************");
		printPlaintext();
		
		// Initializing the Encryption Object and calling initiate method
		DESAlgorithm encryption = new DESAlgorithm(plaintext,key,noofrounds);		
		ciphertext =  (int[]) encryption.initiate();
		//Printing the plaintext
		printCiphertext();
		
		// Initializing the Decryption Object and calling initiate method
		DESAlgorithm decryption = new DESAlgorithm(ciphertext,key,noofrounds);
		decryptedtext = decryption.initiate();
		//Printing the decyptedtext
		printDecryptedtext();
		
		/** Brute Force Attack ******************************************************************/
		
		System.out.println("\n********Performing Brute Force Attack*******");
		
		// Initializing the BruteForce Object and calling attack method
		BruteForceAttack bruteforce = new BruteForceAttack(plaintext,ciphertext,noofrounds);
		bruteforce.attack();
	}
	
	/** Print Methods ******************************************************************/	
	//Printing the plaintext
		public static void printPlaintext(){
			
			System.out.print("Plaintext is : ");
			for(int count=0; count<plaintext.length;count++){
				 
				System.out.print(plaintext [count]);		
			}
			System.out.println();		
		}
		
		//Printing the cipher text
		public static void printCiphertext(){
			
			System.out.print("Ciphertext is : ");
			for(int count=0; count<ciphertext.length;count++){
				 
				System.out.print(ciphertext [count]);		
			}
			System.out.println();		
		}
		
		//Printing the decrypted text
		public static void printDecryptedtext(){
			
			System.out.print("Decrypted text is : ");
			for(int count=0; count<decryptedtext.length;count++){
				 
				System.out.print(decryptedtext [count]);		
			}
			System.out.println();		
		}	



}
