package br.com.alura.ecommerce;

public class User {

    private final String id;
    private final String reportPath;

    public User(String id, String reportPath) {
        this.id = id;
        this.reportPath = reportPath;
    }

    public String getId() {
        return id;
    }

    public String getReportPath() {
        return "target/" + id + "-report.txt";
    }
}
