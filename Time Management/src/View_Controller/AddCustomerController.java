/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.City;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Heith
 */
public class AddCustomerController implements Initializable{
    
    @FXML
    private TextField AddCustomerName;
    
    @FXML
    private TextField AddCustomerPhone;
    
    @FXML
    private TextField AddCustomerAddress;
    
    @FXML
    private TextField AddCustomerAddress2;
    
    @FXML
    private TextField AddCustomerZipCode;
    
    @FXML
    private TextField AddCustomerCountry;
    
    @FXML
    private ComboBox<City> AddCustomerCity;
    //private ComboBox<String> AddCustomerCity = new ComboBox<>();
    
    @FXML
    private Button AddCustomerSave;
    
    @FXML
    private Button AddCustomerCancel;
      
    @FXML
    public void SetCountry(ActionEvent event) {
        CountrySet();
}
     public void CountrySet(){
         City city = AddCustomerCity.getSelectionModel().getSelectedItem();
        int cityId = city.getId();
        City.setSelectedId(cityId);
    
        if(cityId == 3) {
            AddCustomerCountry.setText("England");
        } else {
            AddCustomerCountry.setText("United States");       
        }
     }
    @FXML
    void AddCustomerCancel(ActionEvent event){
        
    }
    
    @FXML
    void AddCustomerSave(ActionEvent event) throws IOException {
        // Retrieves Values from text boxes
        String Name = AddCustomerName.getText();
        String Phone = AddCustomerPhone.getText();
        String Address = AddCustomerAddress.getText();
        String Address2 = AddCustomerAddress2.getText();
        String Zip = AddCustomerZipCode.getText();
        int cityId = City.getSelectedId();
        
        Customer.saveCustomer(Name, Address, Address2, cityId, Zip, Phone);
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
        /*
        try {
            exceptionMessage = Customer.isClientValid(clientName,clientPhone,clientAddress,clientCity,clientCountry,clientZip, exceptionMessage);
            if (exceptionMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part");
                alert.setHeaderText("Error");
                alert.setContentText(exceptionMessage);
                alert.showAndWait();
                exceptionMessage = "";
            } else {
                    Customer.setName(partName);
                    iPart.setPrice(Double.parseDouble(partPrice));
                    iPart.setInStock(Integer.parseInt(partInv));
                    iPart.setMin(Integer.parseInt(partMin));
                    iPart.setMax(Integer.parseInt(partMax));
                    iPart.setMachineID(Integer.parseInt(partDyn));
                    Inventory.addPart(iPart);
                    
                    // Increment our part id
                    Inventory.setPartID(Inventory.getPartID() + 1);
        
        if(!checkName(clientName)|!checkPhone(clientPhone)|!checkAddress(clientAddress)|!checkCity(clientCity)|!checkCountry(clientCountry)|!checkZip(clientZip)){
            return false;
        }else{
            return Customer.saveClient(clientName, clientAddress, clientCity, clientZip, clientPhone);
        }
    }
    
    /**
     * Initializes the controller class.
     */
        
   @Override
    public void initialize(URL url, ResourceBundle rb) {   
        AddCustomerCity.getItems().addAll(City.allCities);
 
    } 
}
