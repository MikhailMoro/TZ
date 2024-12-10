package task3;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Ошибка! Укажите пути к файлам: values.json, tests.json, report.json");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            String valuesContent = new String(Files.readAllBytes(Paths.get(valuesPath)));
            JSONObject values = new JSONObject(valuesContent);

            String testsContent = new String(Files.readAllBytes(Paths.get(testsPath)));
            JSONObject testsRoot = new JSONObject(testsContent);

            if (testsRoot.has("tests")) {
                JSONArray testsArray = testsRoot.getJSONArray("tests");
                fillValues(testsArray, values);
            }

            try (FileWriter writer = new FileWriter(reportPath)) {
                writer.write(testsRoot.toString(4));
            }

            System.out.println("Отчёт успешно сохранён в " + reportPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(JSONArray testsArray, JSONObject values) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject test = testsArray.getJSONObject(i);

            if (test.has("id")) {
                int id = test.getInt("id");
                if (values.has(String.valueOf(id))) {
                    test.put("value", values.getString(String.valueOf(id)));
                }
            }

            if (test.has("values")) {
                JSONArray nestedValues = test.getJSONArray("values");
                fillValues(nestedValues, values);
            }
        }
    }
}
