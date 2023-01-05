package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ReadingReportService {

    final Path SOURCE = new File("src/main/resources/report.txt").toPath();

    public static void main(String[] args) {
        var reportService = new ReadingReportService();
        try (var service = new KafkaService<>(ReadingReportService.class.getSimpleName(),
                "USER_GENERATE_READING_REPORT",
                reportService::parse,
                User.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, User> record) throws IOException {
        System.out.println("------------------------------------------");
        System.out.println("Processing report for " + record.value());

        User user = record.value();
        File target = new File(user.getReportPath());
        IO.coyTo(SOURCE, target);
        IO.append(target, "Created for " + user.getId());
    }
}
