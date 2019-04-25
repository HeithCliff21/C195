/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.City;
import Model.Customer;
import static View_Controller.MainController.customerToCustAddress;
import static View_Controller.MainController.customerToCustAddress2;
import static View_Controller.MainController.customerToCustCityId;
import static View_Controller.MainController.customerToCustName;
import static View_Controller.MainController.customerToCustPhone;
import static View_Controller.MainController.customerToCustZip;
import static View_Controller.MainController.customerToCustomerId;
import static View_Controller.MainController.customertoCustAddressId;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Heith
 */
public class DeleteCustomerController implements Initializable {
    
    @FXML
    private TextField DeleteCustomerName;
    
    @FXML
    private TextField DeleteCustomerPhone;
    
    @FXML
    private TextField DeleteCustomerAddress;
    
    @FXML
    private TextField DeleteCustomerAddress2;
    
    @FXML
    private TextField DeleteCustomerZipCode;
    
    @FXML
    private TextField DeleteCustomerCountry;
    
    @FXML
    private ComboBox<City> DeleteCustomerCity;
    
    @FXML
    private Button UpdateCustomerSave;
    
    @FXML
    private Button UpdateCustomerCancel;
      
    @FXML
     public void SetCountry(ActionEvent event) {
        CountrySet();
}
     public void CountrySet(){
         City city = DeleteCustomerCity.getSelectionModel().getSelectedItem();
        int cityId = city.getId();
        City.setSelectedId(cityId);
    
        if(cityId == 3) {
            DeleteCustomerCountry.setText("England");
        } else {
            DeleteCustomerCountry.setText("United States");       
        }
     }
     
     @FXML
    void DeleteCustomer(ActionEvent event) throws IOException {
        // Retrieves Values from text boxes
        int addressId = customertoCustAddressId();
        int customerId = customerToCustomerId();
        
        Customer.deleteCustomer(customerId, addressId);
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void DeleteCustomerCancel(ActionEvent event){
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DeleteCustomerCity.getItems().addAll(City.allCities);
        DeleteCustomerName.setText(customerToCustName());
        DeleteCustomerPhone.setText(customerToCustPhone());
        DeleteCustomerAddress.setText(customerToCustAddress());
        DeleteCustomerAddress2.setText(customerToCustAddress2());
        DeleteCustomerZipCode.setText(customerToCustZip());
        try {
            City city = City.lookupCity(customerToCustCityId());
            DeleteCustomerCity.getSelectionModel().select(city);
            CountrySet();
        } catch (Exception ex) {
            Logger.getLogger(DeleteCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
