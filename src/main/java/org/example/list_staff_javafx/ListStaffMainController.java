package org.example.list_staff_javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ListStaffMainController {
    Persons_collect persons_collect = new Persons_collect();
    public TextField IDTextField;
    public TextField NameTextField;
    public TextField AgeTextField;
    public TextField IdNumberTextField;
    public TextArea displayArea;

    public void ClearFields() {
        IDTextField.clear();
        NameTextField.clear();
        AgeTextField.clear();
        IdNumberTextField.clear();
    }

    public void AddPerson(ActionEvent actionEvent) {
        String name = NameTextField.getText();
        int age = Integer.parseInt(AgeTextField.getText());
        String idNumber = IdNumberTextField.getText();
        persons_collect.addPerson(new Persons(name, age, idNumber));
        displayArea.setText(persons_collect.PrintAllPersons());
        ClearFields();
    }

    public void UpdatePerson(ActionEvent actionEvent) {
        String name = NameTextField.getText();
        int age = Integer.parseInt(AgeTextField.getText());
        String idNumber = IdNumberTextField.getText();
        persons_collect.updatePerson(new Persons(name, age, idNumber));
        displayArea.setText(persons_collect.PrintAllPersons());
        ClearFields();
    }

    public void DeletePerson(ActionEvent actionEvent) {
        String idNumber = IdNumberTextField.getText();
        persons_collect.removePerson(idNumber);
        displayArea.setText(persons_collect.PrintAllPersons());
        ClearFields();
    }

    public void Print(ActionEvent actionEvent) {
        displayArea.setText(persons_collect.PrintAllPersons());
    }

    public void FindByIdNumber(ActionEvent actionEvent) {
        String idNumber = IdNumberTextField.getText();
        Persons person = persons_collect.SearchPersonsByIdNumber(idNumber);
        if (person != null) {
            displayArea.setText(person.toString());
        } else {
            displayArea.setText("Person not found");
        }
    }

    public void FindByName(ActionEvent actionEvent) {
        String name = NameTextField.getText();
        displayArea.setText(persons_collect.FindPersonsByName(name));
    }
}
