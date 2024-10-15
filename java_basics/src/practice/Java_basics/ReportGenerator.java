package practice.Java_basics;

public class ReportGenerator {
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 1; i <= 1000; i++) {
            report.append("Section ").append(i).append(": This is the content of section ").append(i).append("\n");
        }
        return report.toString();
    }

    public static void main(String[] args) {
        ReportGenerator generator = new ReportGenerator();
        String report = generator.generateReport();
        System.out.println(report);
    }
}
