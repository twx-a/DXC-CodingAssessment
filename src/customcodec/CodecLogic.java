package customcodec;

import java.util.*;

public class CodecLogic {
	//initialize instance
	private LinkedList<Character> refTable;
	private int shiftingValue;
	//constructor
	public CodecLogic(){
		this.refTable = new LinkedList<Character>();
		initializeRefTable();
		this.shiftingValue = 0;
	}
	
	public String encodeText(String inputText) {
		//convert to char array
		List<Character> inputCharacters = new ArrayList<>(strToChar(inputText));
		
		List<Integer> indexOfInput = new ArrayList<>();
		for (Character c : inputCharacters) {
			//for each character in inputCharacters list get the index
			int getIndex = this.refTable.indexOf(c);
			indexOfInput.add(getIndex);
		}
		encodedRefTable();
		List<Character> outputCharacters = new ArrayList<>();
		
		for (int i = 0; i<indexOfInput.size(); i++) {
			int originalArray = indexOfInput.get(i);
			Character c = this.refTable.get(originalArray);
			outputCharacters.add(c);
		}

		String encodedText = "";
		for (Character c : outputCharacters) {
			encodedText += c;
		}
		//System.out.println(indexOfInput);							//check for index of characters
		//System.out.println("encodeText method has been called");  //test connection to see if method is called
		//System.out.println(inputCharacters);                      //test if its being converted properly
		//System.out.println(refTable);                             //check refTable being printed properly
		
		return encodedText;
	}
	
	public String decodeText(String inputText) {
		String decodedText = inputText;
		System.out.println("decodeText method has been called");
		return decodedText;
	}
	
	public Character encodeType(Character decodingType) {
			this.shiftingValue = this.refTable.indexOf(decodingType);
		return decodingType;
	}
	
	private List<Character> strToChar(String initialString) {
		List<Character> userCharacters = new ArrayList<>();
		//convert string to character using for each
		for (char c : initialString.toCharArray()) {
			userCharacters.add(c);
		}
		return userCharacters;
	}
	private void initializeRefTable() {
		//add a-z
		for (char c = 'A'; c <= 'Z'; c++ ) {
			this.refTable.add(c);
		}
		//add 0-9, we use char here because its a char data type
		for (char c = '0'; c <= '9'; c++ ) {
			this.refTable.add(c);
		}
		//try symbols, works as intended. Nice!
		for (char c = '('; c <= '/'; c++ ) {
			this.refTable.add(c);
		}
	}
	
	private void encodedRefTable() {
		List<Character> holdingShiftCharacters = new LinkedList<>();
		int startPoint = this.refTable.size()-this.shiftingValue;
		int endPoint = this.refTable.size()-1;
		// loop from back because list dynamically reassigns indexes. This loop takes the bottom of the list and adds it to a temp list
		for (int i = endPoint; i>=startPoint; i--) {
			holdingShiftCharacters.add(this.refTable.get(i));
		}
		// System.out.println("this contain new items to add: " + holdingShiftCharacters);
		// System.out.println("Current refTable " + this.refTable);
		// This loop removes the bottom of the list and adds it to the top
		int indexPosition = 0;
		for (int i = endPoint; i>=startPoint; i-- ) {
			this.refTable.remove(i);
			if(indexPosition == this.shiftingValue) {
				break;
			}
			else {
				this.refTable.add(0, holdingShiftCharacters.get(indexPosition));
				indexPosition +=1;
			}
		}
		// System.out.println("this the new table " + this.refTable);
		//	System.out.println("shiftingValue: " + this.shiftingValue);
		//	System.out.println(this.refTable.subList(startPoint, endPoint));
		//	System.out.println("starting position " + startPoint);
		//	System.out.println("ending position " + endPoint);
		
	}
}

