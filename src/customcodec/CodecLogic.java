package customcodec;

import java.util.*;

public class CodecLogic {
	//initialize instance
	private List<Character> refTable;
	private List<Character> holdingTable;
	private int shiftingValue;
	//constructor
	public CodecLogic(){
		this.refTable = new LinkedList<Character>();
		initializeRefTable();
		this.holdingTable = new LinkedList<Character>(this.refTable);
		this.shiftingValue = 0;
	}
	
	public String encodeText(String inputText) {
		//convert to char array
		List<Character> inputCharacters = new ArrayList<>(strToChar(inputText));
		List<Integer> indexOfInput = new ArrayList<>();
		List<Character> outputCharacters = new ArrayList<>();
		//get index of each character
		for (Character c : inputCharacters) {
			//for each character in inputCharacters list get the index
			int getIndex = this.refTable.indexOf(c);
			indexOfInput.add(getIndex);
		}
		encodedRefTable();		
		for (int i = 0; i<indexOfInput.size(); i++) {
			int element = indexOfInput.get(i);
			Character c = this.holdingTable.get(element);
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
		this.holdingTable = new LinkedList<>(this.refTable);
		return encodedText;
	}
	
	public String decodeText(String inputText) {
		// convert to char array
		List<Character> inputCharacters = new ArrayList<>(strToChar(inputText));
		List<Character> outputCharacters = new ArrayList<>();
		List<Integer> indexOfInput = new ArrayList<>();
		// first i take the first value of input char, that will give me how many to shift the table by
		char originalShift = inputCharacters.get(0);
		// get the encoded table
		codecType(originalShift);
		//remove this to return normal string
		inputCharacters.remove(0);
		encodedRefTable();
		for (Character c : inputCharacters) {
			//for each character in inputCharacters list get the index using TRANSFORMED TABLE
			int getIndex = this.holdingTable.indexOf(c);
			indexOfInput.add(getIndex);
		}
		
		for (int i=0; i<indexOfInput.size(); i++) {
			int element  = indexOfInput.get(i);
			Character c = this.refTable.get(element);
			outputCharacters.add(c);
		}
		String decodedText = "";
		for (Character c : outputCharacters) {
			decodedText += c;
		}
		//System.out.println(this.holdingTable);
		//System.out.println(this.refTable);
		//System.out.println("decodeText method has been called");
		//reset table
		this.holdingTable = new LinkedList<>(this.refTable);
		return decodedText;
	}
	
	public Character codecType(Character codecType) {
			this.shiftingValue = this.refTable.indexOf(codecType);
		return codecType;
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
		//start at this index to move
		int startPoint = this.refTable.size()-this.shiftingValue;
		//end at this index to move
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
			this.holdingTable.remove(i);
			if(indexPosition == this.shiftingValue) {
				break;
			}
			else {
				this.holdingTable.add(0, holdingShiftCharacters.get(indexPosition));
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

