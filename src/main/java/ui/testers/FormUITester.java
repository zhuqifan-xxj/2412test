package ui.testers;

import java.io.PrintStream;
import java.util.Scanner;

import ui.*;

import java.util.LinkedHashMap;

public class FormUITester {
    public static void main(String[] args) {
        SampleFormUI sampleForm = new SampleFormUI();
        LinkedHashMap<String, Object> formData = sampleForm.fill(new Scanner(System.in));

        if (formData != null) {
            System.out.println("Form Submitted Successfully.");
            System.out.println("Form Data: " + formData);
        }
    }
}

class SampleFormUI extends FormUI {
    public SampleFormUI(PrintStream out) {
        super(out);
    }

    public SampleFormUI() {
        super();
    }

    @Override
    public LinkedHashMap<String, FormField> getFields() {
        LinkedHashMap<String, FormField> fields = new LinkedHashMap<>();
        fields.put("name", new FormField("Name", true, FormDataType.TEXT));
        fields.put("age", new FormField("Age", true, FormDataType.NUMBER));
        fields.put("email", new FormField("Email", false, FormDataType.TEXT, new EmailValidator()));
        fields.put("subscribe", new FormField("Subscribe to newsletter?", true, FormDataType.BOOLEAN));
        return fields;
    }


}
