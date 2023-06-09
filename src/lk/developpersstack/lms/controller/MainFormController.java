package lk.developpersstack.lms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.developpersstack.lms.bo.BoFactory;
import lk.developpersstack.lms.bo.custom.StudentBo;
import lk.developpersstack.lms.dto.StudentDto;
import lk.developpersstack.lms.view.tm.StudentTm;
import java.sql.SQLException;
import java.util.Optional;

public class MainFormController {

    public TextField txtName;
    public TextField txtContact;

    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;

    public TableView<StudentTm> tblStudent;
    public TableColumn colSeeMore;
    public TableColumn colDelete;
    private final StudentBo studentBo= BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);
    public Button btnStudentSave;

    private StudentTm selectedStudentTm=null;


    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSeeMore.setCellValueFactory(new PropertyValueFactory<>("seeMoreBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllStudents();

        //----------------Listener---------------------
        tblStudent.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
             if (newValue!=null){
                 selectedStudentTm=newValue;
                 txtName.setText(newValue.getName());
                 txtContact.setText(newValue.getContact());
                 btnStudentSave.setText("Update Student");
             }
        });
        //----------------Listener---------------------
    }


    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        for (StudentDto dto:studentBo.findAllStudent()) {
            Button deleteBtn=new Button("Delete");
            deleteBtn.setStyle("-fx-background-color: #8e44ad");
            Button seeMoreBtn=new Button("See More");
            seeMoreBtn.setStyle("-fx-background-color: #d35400");
            StudentTm tm=new StudentTm(
                    dto.getId(),dto.getName(),dto.getContact(),seeMoreBtn,deleteBtn
            );
            obList.add(tm);
            deleteBtn.setOnAction(e->{
                Alert alert=new Alert(
                        Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO
                );
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)){
                    try{
                        studentBo.deleteStudentById(tm.getId());
                        new Alert(Alert.AlertType.INFORMATION,"Student Deleted!").show();
                        loadAllStudents();
                    }catch (Exception error){
                        new Alert(Alert.AlertType.ERROR,error.toString()).show();
                        System.out.println(error);
                    }
                }
            });
        }
        tblStudent.setItems(obList);
        tblStudent.refresh();
    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        StudentDto dto=new StudentDto();
        dto.setName(txtName.getText());
        dto.setContact(txtContact.getText());

        if (btnStudentSave.getText().equals("Update Student")){
            if (selectedStudentTm==null){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
                return;
            }
            try {
                dto.setId(selectedStudentTm.getId());
                studentBo.updateStudent(dto);
                new Alert(Alert.AlertType.INFORMATION,"Student updated!").show();
                selectedStudentTm=null;
                btnStudentSave.setText("Save Student");
                loadAllStudents();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }else {
            try {
                studentBo.saveStudent(dto);
                new Alert(Alert.AlertType.INFORMATION,"Student saved!").show();
                loadAllStudents();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }
    }

    public void newStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnStudentSave.setText("Save Student");
        selectedStudentTm=null;
        txtContact.clear();
        txtName.clear();
    }
}
