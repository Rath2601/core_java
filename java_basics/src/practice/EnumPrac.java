package practice;

public class EnumPrac {
	public static void main(String[] args) {
		for (Category category : Category.values()) {
            System.out.println("Category: " + category + ", Value: " + category.getValue());
        }
	}
}

enum Category {
	POLICE("Kaval"), THIEF("thirudan"), DOCTOR("maruthuvar"), VILLAGER("oorkaran");

	private String value;

	Category(String string) {
		this.value = string;
	}

	public String getValue() {
		return value;
	}
}