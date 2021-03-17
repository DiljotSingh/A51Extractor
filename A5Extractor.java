//Author: Diljot Singh

//HOW TO USE: 
//		      1. Paste in the given 64 bit key (such as 1011111110101010100111000000001011001100011100001110000011110111)
//			  2. Program will analyze the key, break it up into 19-22-23 bits and give info such as the majority bit, etc
// 			  3. Program will print the keystream bit for the current sequence (given by x[18] XOR y[21] XOR z[22])
//			  4. Program will give the new sequence (after all appropriate registers were shifted)
//            5. Feel free to copy the new sequence, run the program again, and paste in the new sequence as needed


import java.util.*;

public class A5Extractor {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Scanner to get user inputs

		// Arrays to store each bit
		int[] xRegister = new int[19]; // first 19 bits get stored in x register
		int[] yRegister = new int[22]; // next 22 bits get stored in y register
		int[] zRegister = new int[23]; // final 23 bits get stored in z register

		// Ask for user input and store it in 'sequence;
		System.out.print("Please paste in your 64 bit sequence: ");
		String sequence = scan.nextLine();
		System.out.println("You entered: " + sequence);

		// Break up sequence into 19, 22, and 23 bits, allocating each to array
		System.out.print("First 19 bits are: ");
		for (int i = 0; i < 19; i++) {
			xRegister[i] = Integer.parseInt((sequence.substring(i, i + 1)));
			System.out.print(xRegister[i]);
		}

		// Break up sequence into 19, 22, and 23 bits, allocating each to array
		System.out.println();
		System.out.print("Next 22 bits are: ");
		for (int i = 0; i < 22; i++) {
			yRegister[i] = Integer.parseInt((sequence.substring(i + 19, i + 20)));
			System.out.print(yRegister[i]);
		}

		// Break up sequence into 19, 22, and 23 bits, allocating each to array
		System.out.println();
		System.out.print("Last 23 bits are: ");
		for (int i = 0; i < 23; i++) {
			zRegister[i] = Integer.parseInt((sequence.substring(i + 19 + 22, i + 20 + 22)));
			System.out.print(zRegister[i]);
		}
		System.out.println();

		// Get the bits from registers x[8], y[10], z[10] to determine majority
		int x8 = xRegister[8];
		int y10 = yRegister[10];
		int z10 = zRegister[10];

		// Print each register
		System.out.println("x[8]: " + xRegister[8]);
		System.out.println("y[10]: " + yRegister[10]);
		System.out.println("z[10]: " + zRegister[10]);

		// Determine whether 0 or 1 was the majority
		int sum = x8 + y10 + z10; // add the three bits up

		int majorityBit = -1; // Used to store the bit that was the majority

		// If the sum was 1 or less, that means 0 was the majority (2 or more zero's
		// present)
		if (sum <= 1) {
			majorityBit = 0;
		}
		// Otherwise the majority was 1 (2 or more one's)
		else if (sum > 1) {
			majorityBit = 1;
		}
		System.out.println("Majority bit was: " + majorityBit);

		// If x[8] matches the majority bit, shift xRegister down by 1
		if (xRegister[8] == majorityBit) {
			System.out.println("Shifting x-register: "); // alert user they are shifting this register

			// first compute the new first bit (t = x[13] XOR x[16] XOR x[17] XOR x[18])
			int newBit = xRegister[13] ^ xRegister[16] ^ xRegister[17] ^ xRegister[18];
			System.out.println("		New x[0] bit is: " + newBit);
			// Shift xRegister down by 1
			for (int i = 18; i > 0; i--) {
				xRegister[i] = xRegister[i - 1];
			}
			// set x[0] to newBit
			xRegister[0] = newBit;
		}

		// If y[10] matches the majority bit, shift yRegister down by 1
		if (yRegister[10] == majorityBit) {
			System.out.println("Shifting y-register: "); // alert user they are shifting this register

			// first compute the new first bit (t = y[20] XOR y[21])
			int newBit = yRegister[20] ^ yRegister[21];
			System.out.println("		New y[0] bit is: " + newBit);

			// Shift yRegister down by 1
			for (int i = 21; i > 0; i--) {
				yRegister[i] = yRegister[i - 1];
			}
			// set y[0] to newBit
			yRegister[0] = newBit;
		}

		// If z[10] matches the majority bit, shift zRegister down by 1
		if (zRegister[10] == majorityBit) {
			System.out.println("Shifting z-register: "); // alert user they are shifting this register

			// first compute the new first bit (t = zRegister[7] ^ zRegister[20] ^
			// zRegister[21] ^ zRegister[22])
			int newBit = zRegister[7] ^ zRegister[20] ^ zRegister[21] ^ zRegister[22];
			System.out.println("		New z[0] bit is: " + newBit);

			// Shift zRegister down by 1
			for (int i = 22; i > 0; i--) {
				zRegister[i] = zRegister[i - 1];
			}
			// set z[0] to newBit
			zRegister[0] = newBit;
		}

		// Compute the keystream bit (x[18] XOR y[21] XOR z[22])
		int keyStreamBit = xRegister[18] ^ yRegister[21] ^ zRegister[22];
		System.out.println("Keystream bit: " + keyStreamBit);

		
		String newSequence = ""; //The new sequence for the user's luxury

		// Add the new x register bits
		for (int i = 0; i < 19; i++) {
			newSequence = newSequence + xRegister[i];
		}

		// Add the new y register bits
		for (int i = 0; i < 22; i++) {
			newSequence = newSequence + yRegister[i];
		}

		// Add the new z register bits
		for (int i = 0; i < 23; i++) {
			newSequence = newSequence + zRegister[i];
		}
		
		// Print the new sequence (combining bits from all registers so the user can run
		// the program again if needed)
		System.out.println("The new sequence is:" + newSequence);

	}

}
