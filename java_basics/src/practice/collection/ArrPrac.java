package practice.collection;

class Patient{
	public String name;

	@Override
	public String toString() {
		return "Patient [name=" + name + "]";
	}
}

class Report extends Patient{
	public String file;
}
public class ArrPrac {
public static void main(String[] args) {
	Patient[] p = new Patient[2];
	Patient patient = new Patient();
	patient.name = "Rathna";
	Report report = new Report();
	report.file = ".pdf";
	
	p[0] = patient;
	p[1] = report;
	
	p[0] = null;
	
	for(Patient ps : p) {
		System.out.println(ps);
	}
	
	final int[] arr = new int[1];
	
	arr[0] = 12;
	
	arr[0] = 14;
	
	for (int i : arr) {
		System.out.println(i);
	}
}
}
