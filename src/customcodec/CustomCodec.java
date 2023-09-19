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

			//encode
			if ("e".equalsIgnoreCase(userInput)) {
				while(true) {
					System.out.println("What is your Encoding type? Pick a letter from A-Z or 0-9 or (-/ ");
					String encodingInput = scanner.nextLine().toUpperCase();
					codec.logicController.codecType(encodingInput.charAt(0));
					if(codec.logicController.checkRefTable(encodingInput.charAt(0))) {
						System.out.println("Enter the text:");
						String text = scanner.nextLine().toUpperCase().strip();
						String encodedText = codec.encode(text);
						System.out.println("Encoded text: " + encodingInput + encodedText);
						break;
					}
					else {
						System.out.println("Invalid Encoding type. Please try again.");
					}
				}
			}
			//decode
			else if ("d".equalsIgnoreCase(userInput)) {
				System.out.println("Enter the text:");
				String text = scanner.nextLine().toUpperCase().strip();
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
}
