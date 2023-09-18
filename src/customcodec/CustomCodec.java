package customcodec;
import java.util.*;
public class CustomCodec {
	
	// use methods inside CodecLogic method
	public CodecLogic logicController;
	
	// constructor for CodecLogic
	public CustomCodec() {
        this.logicController = new CodecLogic();
    }
	
	public static void main(String args[]) {
		CustomCodec codec = new CustomCodec();
	
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter 'e' to encode text, 'd' to decode text, or 'q' to exit:");
			String userInput = scanner.nextLine().toLowerCase();
			

			//check if quit. put here. else it will take the other commands first
			if ("q".equalsIgnoreCase(userInput)) {
				System.out.println("Exiting...");
				break;
			}
			System.out.println("Enter the text:");
			String text = scanner.nextLine().toUpperCase();

			//encode
			if ("e".equalsIgnoreCase(userInput)) {
				System.out.println("What is your Encoding type? Pick a letter from A-Z or 0-9 or (-/ ");
				String encodingInput = scanner.nextLine().toUpperCase();
				codec.typeOfEncoding(encodingInput.charAt(0));
				String encodedText = codec.encode(text);
				System.out.println("Encoded text: " + encodingInput + encodedText);
			}
			//decode
			else if ("d".equalsIgnoreCase(userInput)) {
				String decodedText = codec.decode(text.toUpperCase());
				System.out.println("Decoded text: " + decodedText);
			}
			//error handling
			else {
				System.out.println("Unknown command. Please enter 'e' to encode text, 'd' to decode text, or 'q' to exit:");
			}
			
		}
		scanner.close();
	}
	
	public String encode (String plainText) {
		return logicController.encodeText(plainText);
	}
	public String decode (String encodedText) {
		return logicController.decodeText(encodedText);
	}
	public Character typeOfEncoding(Character encodeType) {
		return logicController.encodeType(encodeType);
	}
}
