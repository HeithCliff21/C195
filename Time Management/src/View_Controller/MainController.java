/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Address;
import Model.Appointment;
import Model.City;
import Model.Country;
import Model.Customer;
import Model.CustomerTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Heith
 */
public class MainController implements Initializable {
    
    @FXML
    private TableView<CustomerTable> MainCustomersTable;
    @FXML
    private TableColumn<CustomerTable, Integer> MainCustomerID;
    @FXML
    private TableColumn<CustomerTable, String> MainCustomerName;
    @FXML
    private TableColumn<CustomerTable, String> MainCustomerPhone;
    @FXML
    private TableColumn<CustomerTable, String> MainCustomerAddress;
    @FXML 
    private TableColumn<CustomerTable, String> MainCustomerCity;
    
    
    @FXML
    private TableView<Appointment> AllAptTable;
    @FXML
    private TableColumn<Appointment, Integer> AllAptID;
    @FXML
    private TableColumn<Appointment, Integer> AllClientID;
    @FXML
    private TableColumn<Appointment, String> AllLocation;
    @FXML
    private TableColumn<Appointment, String> AllStartTime;
    @FXML 
    private TableColumn<Appointment, String> AllEndTime;
    
    private static Customer updateCustomer;
    private static int updateCustomerId;
    private static String updateCustomerName;
    private static String updateCustomerPhone;
    private static String updateCustomerAddress;
    private static String updateCustomerAddress2;
    private static int updateCustomerCityId;
    private static String updateCustomerZip;
    private static int updateCustomerAddressId;
    
   
    private static Appointment updateAppointmnet;
    private static int updateAptId;
    private static int updateAptCustId;
    private static String updateAptType;
    private static String updateAptLocation;
    private static String updateAptStart;
    private static String updateAptEnd;
    private static String updateAptContact;
    private static String updateAptUrl;
    private static String updateAptTitle;
    private static String updateAptDescription;
    
       
    public static int customerToCustomerId() {
        return updateCustomerId;
    }   
    public static String customerToCustName() {
        return updateCustomerName;
    }
     public static String customerToCustPhone() {
        return updateCustomerPhone;
    }
      public static String customerToCustAddress() {
        return updateCustomerAddress;
    }
       public static String customerToCustAddress2() {
        return updateCustomerAddress2;
    }
        public static int customerToCustCityId() {
        return updateCustomerCityId;
    }
        public static String customerToCustZip() {
        return updateCustomerZip;
    }
         public static int customertoCustAddressId(){
             return updateCustomerAddressId;
         }
      
    public static int appointmentToAppointmentId() {
        return updateAptId;
    }   
    public static int appointmentToCustId() {
        return updateAptCustId;
    } 
    public static String appointmentToType() {
        return updateAptType;
    }
     public static String appointmentToLocation() {
        return updateAptLocation;
    }
       public static String appointmentToStart() {
        return updateAptStart;
    }
        public static String appointmentToEnd() {
        return updateAptEnd;
    }
        public static String appointmentToContact() {
        return updateAptContact;
    }
        public static String appointmentToUrl(){
        return updateAptUrl;
    }
        public static String appointmentToTitle(){
        return updateAptTitle;
    }
        public static String appointmentToDescription(){
        return updateAptDescription;
    }
         
    
              
  /*  @FXML
    private TableView<Customer> MainCustomerTable;
    @FXML
    private TableColumn<Customer, Integer> MainCustomerID;
    @FXML
    private TableColumn<Customer, String> MainCustomerName;
    @FXML
    private TableColumn<Customer, String> MainCustomerAddress;
    @FXML
    private TableColumn<Customer, String> MainCustomerPhone;
    @FXML
    private TableView<Appointment> MainProductTable;
    @FXML
    private TableColumn<Product, Integer> MainProductID;
    @FXML
    private TableColumn<Product, String> MainProductName;
    @FXML
    private TableColumn<Product, Integer> MainProductInvLevel;
    @FXML
    private TableColumn<Product, Double> MainProductPricePer;
*/
    
    
    
    @FXML
    void AddCustomer(ActionEvent event) throws IOException {

        Parent addCustomer = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene scene = new Scene(addCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    
    @FXML
    void UpdateCustomer(ActionEvent event) throws IOException {
        CustomerTable customer = MainCustomersTable.getSelectionModel().getSelectedItem();
        updateCustomerId = customer.getCustomerId();
        updateCustomerName = customer.getCustomerName();
        updateCustomerPhone = customer.getPhone();
        updateCustomerAddress = customer.getAddress();
        updateCustomerAddress2 = customer.getAddress2();
        updateCustomerCityId = customer.getCityId();
        updateCustomerZip = customer.getPostalCode();
        updateCustomerAddressId = customer.getAddId();
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("UpdateCustomer.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
    }
    
    
    
    @FXML
    void DeleteCustomer(ActionEvent event) throws IOException {
        CustomerTable customer = MainCustomersTable.getSelectionModel().getSelectedItem();
        updateCustomerId = customer.getCustomerId();
        updateCustomerName = customer.getCustomerName();
        updateCustomerPhone = customer.getPhone();
        updateCustomerAddress = customer.getAddress();
        updateCustomerAddress2 = customer.getAddress2();
        updateCustomerCityId = customer.getCityId();
        updateCustomerZip = customer.getPostalCode();
        updateCustomerAddressId = customer.getAddId();
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("DeleteCustomer.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
       @FXML
    void AddApt(ActionEvent event) throws IOException {
        CustomerTable customer = MainCustomersTable.getSelectionModel().getSelectedItem();
        updateCustomerId = customer.getCustomerId();
        updateCustomerName = customer.getCustomerName();
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("AddApt.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
       @FXML
    void UpdateApt(ActionEvent event) throws IOException {
        Appointment appointment = AllAptTable.getSelectionModel().getSelectedItem();
        updateAptId = appointment.getAppointmentId();
        updateAptCustId = appointment.getCustomerId();
        updateAptTitle = appointment.getTitle();
        updateAptDescription = appointment.getDescription();
        updateAptLocation = appointment.getLocation();
        updateAptContact = appointment.getContact();
        updateAptUrl = appointment.getUrl();
        updateAptStart = appointment.getStart();
        updateAptEnd = appointment.getEnd();
        updateAptType = appointment.getType();
        
//        updateAptDate = appointment.getDate();
        
        
        
        
        
        
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("UpdateApt.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
       @FXML
    void DeleteApt(ActionEvent event) throws IOException {
    }
    
       @FXML
    void MainExit(ActionEvent event) throws IOException {
    }

    
 /*   
     public void updateCustomersTable() {
        MainCustomerTable.setItems(Inventory.getParts());
    }

    public void updateAppointmentsTable() {
        try {
            ObservableList<Product> products = Inventory.getProducts();
            MainProductTable.setItems(Inventory.getProducts());
        }
        catch (Exception e) {
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePartTable();
        updateProductTable();
        MainPartID.setCellValueFactory(new PropertyValueFactory("partID"));
        MainPartName.setCellValueFactory(new PropertyValueFactory("name"));
    /**
     * Initializes the controller class.
     */
    public void updateCustomerTable() {
        MainCustomersTable.setItems(CustomerTable.getCustomersTable());   
    }

    public void updateAppointmentTable() {
        AllAptTable.setItems(Appointment.getAllAppointments());  
        
      }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Address.getAllAddress();
        
        City.getAllCities();
        Country.getAllCountries();
        Customer.getAllCustomers();
        updateCustomerTable();
        updateAppointmentTable();
        
        MainCustomerID.setCellValueFactory(new PropertyValueFactory("customerId"));
        MainCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        MainCustomerPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        MainCustomerAddress.setCellValueFactory(new PropertyValueFactory("address"));
        MainCustomerCity.setCellValueFactory(new PropertyValueFactory("city"));
        
        AllAptID.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        AllClientID.setCellValueFactory(new PropertyValueFactory("customerId"));
        AllLocation.setCellValueFactory(new PropertyValueFactory("location"));
        AllStartTime.setCellValueFactory(new PropertyValueFactory("start"));
        AllEndTime.setCellValueFactory(new PropertyValueFactory("end"));
          
    }    
    
}
