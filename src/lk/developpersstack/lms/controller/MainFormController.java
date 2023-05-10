package lk.developpersstack.lms.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.developpersstack.lms.bo.BoFactory;
import lk.developpersstack.lms.bo.custom.StudentBo;
import lk.developpersstack.lms.dto.StudentDto;

public class MainFormController {

    public TextField txtName;
    public TextField txtContact;

    private final StudentBo studentBo= BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        StudentDto dto=new StudentDto();
        dto.setName(txtName.getText());
        dto.setContact(txtContact.getText());

        try {
            studentBo.saveStudent(dto);
            new Alert(Alert.AlertType.INFORMATION,"Student saved!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Try Again").show();
        }

    }
}
