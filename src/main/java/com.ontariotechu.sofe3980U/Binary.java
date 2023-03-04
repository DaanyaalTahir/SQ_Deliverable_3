package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	/**
	* This function takes two binary numbers and pads zeros to a number to make them both the same length
	*
	* @param num1 The first binary object
	* @param num2 The second binary object
	* @return An array containing both binary objects of same length
	*/
	private static String[] matchTwoBinaryLengths(Binary num1, Binary num2){
		// Initialize variables
		int paddedZeros = 0;
		String num1Str = num1.getValue(), num2Str = num2.getValue();

		// Find which binary object is longer in length than the other
		if( num1Str.length() > num2Str.length() ){
			paddedZeros = num1Str.length() - num2Str.length(); // Determine num of zeros to padd
			// Pad the zeros to the number to make both the same length
			num2Str = new String(new char[paddedZeros]).replace("\0", "0") + num2Str;
		}
		else if( num1Str.length() < num2Str.length()){
			paddedZeros = num2Str.length() - num1Str.length();
			num1Str = new String(new char[paddedZeros]).replace("\0", "0") + num1Str;
		}

		return new String[] {num1Str, num2Str};
	}

	/**
	* This function takes two binary numbers and performs bitwise "AND" on them
	*
	* @param num1 The first binary object
	* @param num2 The second binary object
	* @return Result of AND'ing both binary objects
	*/
	public static Binary bitwiseAnd(Binary num1, Binary num2)
	{
		// Initialize variables
		String[] matchedBinaryStrings = matchTwoBinaryLengths(num1, num2); 
		String num1Str = matchedBinaryStrings[0], num2Str = matchedBinaryStrings[1], result="";

		// Loop through the binary numbers
		for(int i=0; i<num1Str.length(); i++){
			// Perform "AND" on each bit of both binary numbers by checking if each bit is the same
			if(num1Str.charAt(i) == num2Str.charAt(i)){
				result += num1Str.charAt(i);
			}
			else{
				result += "0";
			}
		}

		return new Binary(result);
	}

	/**
	* This function takes two binary numbers and performs bitwise "OR" on them
	*
	* @param num1 The first binary object 
	* @param num2 The second binary object
	* @return Result of OR'ing both binary objects
	*/
	public static Binary bitwiseOr(Binary num1, Binary num2)
	{
		// Initialize variables
		String[] matchedBinaryStrings = matchTwoBinaryLengths(num1, num2);
		String num1Str = matchedBinaryStrings[0], num2Str = matchedBinaryStrings[1], result="";

		// Loop through each bit of both binary numbers
		for(int i=0; i<num1Str.length(); i++){
			// Perform "OR" on each bit by checking if at least one of them is a '1'
			if(num1Str.charAt(i) == '1' || num2Str.charAt(i) == '1' ){
				result += "1";
			}
			else{
				result += "0";
			}
		}

		return new Binary(result);
	}

	/**
	* This function takes two binary numbers and performs bitwise AND on them
	*
	* @param num1 The first binary object to multiply
	* @param num2 The second binary object to multiply 
	* @return Result of multiplying both binary objects
	*/
	public static Binary multiply(Binary num1, Binary num2) {
		// Initialize variables
		int ind2 = num2.number.length() - 1;
		String num3 = "0";
		Binary result = new Binary(num3);
		Binary num1Cpy = new Binary(num1.getValue()); // copy num1 to prevent changing the real obj

		// Loop through the length of second num
		while (ind2 >= 0) {
			// Add extra zero at end depending on bit position
			int bit = (num2.number.charAt(ind2) == '1') ? 1 : 0;

			if (bit == 1) {
				result = add(result, num1Cpy); // Add partial products together
			}
			num1Cpy.number += "0"; 
			ind2--; // Decrement variable
		}
		return result; // return the result
	}

}	