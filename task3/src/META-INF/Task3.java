import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Ошибка");
            return;
        }

        try {
            String valuesContent = new String(Files.readAllBytes(Paths.get(args[0])));
            JSONObject values = new JSONObject(valuesContent);
            System.out.println("Values загружен");

            String testsContent = new String(Files.readAllBytes(Paths.get(args[1])));
            JSONObject testsRoot = new JSONObject(testsContent);
            System.out.println("Tests загружен");

            if (testsRoot.has("tests")) {
                JSONArray testsArray = testsRoot.getJSONArray("tests");
                fillValues(testsArray, values);
            } else {
                System.out.println("Ошибка");
                return;
            }

            System.out.println("Загрузка report: " + args[2]);
            try (FileWriter writer = new FileWriter(args[2])) {
                writer.write(testsRoot.toString(4));
            }
            System.out.println("Report загружен");

        } catch (IOException e) {
            System.out.println("Ошибка" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка JSON" + e.getMessage());
        }
        try {
            String testsPath = args[1];
            System.out.println("Путь к tests.json: " + testsPath);
            String testsContent = new String(Files.readAllBytes(Paths.get(testsPath)));
            System.out.println("Содержание" + testsContent);
            JSONObject testsRoot = new JSONObject(testsContent);
            System.out.println("Успешно");
        } catch (IOException e) {
            System.out.println("Ошибка tests.json: " + e.getMessage());
        }

    }

    private static void fillValues(JSONArray testsArray, JSONObject values) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject test = testsArray.getJSONObject(i);

            if (test.has("id")) {
                int id = test.getInt("id");
                if (values.has(String.valueOf(id))) {
                    test.put("value", values.get(String.valueOf(id)));
                }
            }

            if (test.has("values")) {
                JSONArray nestedValues = test.getJSONArray("values");
                fillValues(nestedValues, values);
            }
        }
    }
}

