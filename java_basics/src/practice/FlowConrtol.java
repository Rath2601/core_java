package practice;

public class FlowConrtol {
	public static void main(String[] args) {
		Detail d = new Detail("maeve", 19, "female");

		FlowConrtol f = new FlowConrtol();
		System.out.println(f.checkColor("green"));
		System.out.println(f.checkEligible(d));
	}

	private Boolean checkEligible(Detail d) {
		if (d.getAge() < 25 && d.getAge() > 18) {
			if (d.getGender() != "female") {
				return false;
			} else {
				if (d.getName().length() < 9) {
					return true;
				} else {
					return false;
				}
			}
		} 
		else {
			return false;
		}
	}

	private String checkColor(String string) {
		switch (string) {
		case "red": {
			return "red is matching";
		}
		case "yellow": {
			return "yellow is matching";
		}
		case "green": {
			return "green is matching";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
	}
}

class Detail {
	private String name;
	private int age;
	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public Detail(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
