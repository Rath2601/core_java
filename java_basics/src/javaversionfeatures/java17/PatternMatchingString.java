package javaversionfeatures.java17;

public class PatternMatchingString {
	public static void logMessage(Object message) {
		String logEntry = switch (message) {
		case String s -> "String message: " + s;
		case Integer i -> "Integer message: " + i;
		case Double d -> "Double message: " + d;
		default -> "Unknown message type";
		};
		System.out.println(logEntry);
	}

	public static void main(String[] args) {
		logMessage("suthan is a developer");
		logMessage(12);
		logMessage(new ArrayIndexOutOfBoundsException());
	}
}
