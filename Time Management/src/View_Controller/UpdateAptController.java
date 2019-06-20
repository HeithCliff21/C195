/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import static Model.Appointment.getDateTime;
import Model.City;
import Model.Customer;
import Model.CustomerTable;
import Model.DataBase;
import static View_Controller.MainController.appointmentToAppointmentId;
import static View_Controller.MainController.appointmentToCustId;
import static View_Controller.MainController.appointmentToType;
import static View_Controller.MainController.appointmentToLocation;
import static View_Controller.MainController.appointmentToStart;
import static View_Controller.MainController.appointmentToEnd;
import static View_Controller.MainController.appointmentToContact;
import static View_Controller.MainController.appointmentToUrl;
import static View_Controller.MainController.appointmentToTitle;
import static View_Controller.MainController.appointmentToDescription;
//import static View_Controller.MainController.appointmentToCustName;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
interface something{
            String getTime(String Location, String Time);
            
        } 
/**
 * FXML Controller class
 *
 * @author Heith
 */
public class UpdateAptController implements Initializable {
    @FXML
    private TextField updateaptClientName;
    @FXML
    private ComboBox updateaptType;
    @FXML
    private DatePicker updateaptDate;
    @FXML
    private ComboBox updateaptStartTime;
    @FXML
    private ComboBox updateaptEndTime;
    @FXML
    private ComboBox<City> updateaptLocation;
    @FXML
    private TextField updateaptUrl;
    @FXML
    private TextField updateaptTitle;
    @FXML
    private TextArea updateaptDescription;
    @FXML
    private TextField updateaptContact;
    @FXML
    private Button updateAptSave;
    @FXML
    private Button updateAptCancel;
    
    //Using the C Global Consulting Type of Consulting as references for a global consulting agency
    private final ObservableList<String> AppointmentType = FXCollections.observableArrayList("LeaderShip Development" , "Conflict Management", "Organization Change");
    
    private final ObservableList<String> AptStartTime = FXCollections.observableArrayList("8:00 AM","8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM");
    
    private final ObservableList<String> AptEndTime = FXCollections.observableArrayList("8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM","5:00 PM");
      
    public String setAptType(){
        String Type = (String) updateaptType.getSelectionModel().getSelectedItem();
        return Type;
    }
    
    public int LocationSet(){  
        String location = appointmentToLocation();
        if(location != "America/Phoniex") {
            int cityId = 1;
            return cityId;
        } else if (location != "America/New_York") {
            int cityId = 2;
            return cityId;
        }else{
            int cityId = 3;
            return cityId;
        }
     }
    
    //Set Location City Object to String to use for correct DateTime Format
    public String SetLocationName(){
        City city = updateaptLocation.getSelectionModel().getSelectedItem();
        int cityId = city.getId();
        City.setSelectedId(cityId);
        
        if(cityId == 1) {
            String Location = "America/Phoniex";
            return Location;
        }else if(cityId == 2){
            String Location = "America/New_York";
            return Location;           
        }else{
            String Location = "Europe/London";
            return Location;
        }  
    }
    public String setAptStart() throws ParseException{
        String Start = (String) updateaptStartTime.getSelectionModel().getSelectedItem();
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        
    	String time24;
        time24 = date24Format.format(date12Format.parse(Start));
    	
        return time24;
    }
    
    
    public String setAptEnd() throws ParseException{
        String End = (String) updateaptEndTime.getSelectionModel().getSelectedItem();
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        
    	String time24;
        time24 = date24Format.format(date12Format.parse(End));
    	
        return time24;
    }
    public String setAptDate(){
        String Date = updateaptDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Date;
    }
    
    
    
    
     @FXML
    void updateAptSave(ActionEvent event) throws IOException, ParseException, SQLException {
        
        int aptId = appointmentToAppointmentId();
        int custId = appointmentToCustId();
        String title = updateaptTitle.getText();
        String description = updateaptDescription.getText();
        String location = SetLocationName();
        String contact = updateaptContact.getText();
        String type = setAptType();
        String url = updateaptUrl.getText();
        String start = setAptStart();
        String end = setAptEnd();
        String date = setAptDate();
        
        String startDateTime = Appointment.getDateTime(date, start, location);
        String endDateTime = Appointment.getDateTime(date, end, location);
        
        // Line to check if Appointment Time are already taken for user
        if (Appointment.appointmentAvialableUser(startDateTime, endDateTime) == false){
           
           // Alert alert  // Appointment isn't avialbe due to User has another appointment at that time 
           }
        if (Appointment.appointmentAvialableCust(startDateTime, endDateTime, custId) == false){
           // Appointment isn't avialbe due to customer has another appointment at that time 
        }
       
        
        
        
        System.out.println("date: " + setAptDate());
        System.out.println("start: " + setAptStart());
        System.out.println("end: " + setAptEnd());
        System.out.println("location: " + SetLocationName());
        
        Appointment.updateApt(aptId, title, description, location, contact, type, url, startDateTime, endDateTime);
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void updateAptCancel(ActionEvent event) throws IOException {
        
    }
    
     // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {
 
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
 
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
 
                        // Disable Monday, Tueday, Wednesday.
                        if (item.getDayOfWeek() == DayOfWeek.SATURDAY //
                            || item.getDayOfWeek() == DayOfWeek.SUNDAY
                            || item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #939696;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
    
    @FXML
     public void SetDate() throws ParseException {
        DatePicker newaptDate = new DatePicker();
        
        String dateL = Appointment.getlocationDate(appointmentToLocation(), appointmentToStart());
        
        //LocalDate localDate = LocalDate.parse(dateL); 
        
        //newaptDate.setValue(localDate);
        newaptDate.setShowWeekNumbers(false);
        
        
        //updateaptDate.getSelectionModel().select(dateL);
        //getDayCellFactory();
     
        // Converter
//        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
//        DateTimeFormatter dateFormatter =
//                      DateTimeFormatter.ofPattern("YYYY-MM-DD");
//
//            @Override
//            public String toString(LocalDate object) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }         
//        };
     }
    
//    public static LocalDate LOCAL_DATE (String dateString){
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//    LocalDate localDate = LocalDate.parse(dateString, formatter);
//    return localDate;
//}
   
     public void startTime() throws ParseException{
         String time = Appointment.getlocationTime(appointmentToLocation(), appointmentToStart());
                 updateaptStartTime.getSelectionModel().select(time);
     }
     
     public void endTime() throws ParseException{
         String time = Appointment.getlocationTime(appointmentToLocation(), appointmentToEnd());
         updateaptEndTime.getSelectionModel().select(time);
     }
     

     public String customerName(int custID){
         //Customer.getAllCustomers();
        try {
        Statement statement = DataBase.conn.createStatement();
        String query = "SELECT * FROM customer WHERE customerId = '" + custID + "';";
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        String custname = rs.getString("customerName");
        
        statement.close();
        return custname;
     }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
}
//        int custId = appointmentToCustId();
//        String custName = Customer.getCustName(custId);
//        String custname = Customer.getCustName(custId);
        
        // String name = CustomerTable.getCustomerName(appointmentToCustId());
        // updateaptClientName.setText(name);
//        return custname;
     }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //customerName();
        //updateaptType.getSelectionModel().select(appointmentToType());
        //updateaptLocation.getSelectionModel().select(appointmentToLocation());
        updateaptType.setItems(AppointmentType);
        updateaptLocation.getItems().addAll(City.allCities);
        updateaptStartTime.setItems(AptStartTime);
        updateaptEndTime.setItems(AptEndTime);
        try {
            startTime();
        } catch (ParseException ex) {
            Logger.getLogger(UpdateAptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            endTime();
        } catch (ParseException ex) {
            Logger.getLogger(UpdateAptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateaptContact.setText(appointmentToContact());
        updateaptUrl.setText(appointmentToUrl());
        updateaptTitle.setText(appointmentToTitle());
        updateaptDescription.setText(appointmentToDescription());
        updateaptClientName.setText(customerName(appointmentToCustId()));
        updateaptType.getSelectionModel().select(appointmentToType());
        updateaptLocation.getSelectionModel().select(LocationSet());
   
        try {
            // Disable Monday, Tueday, Wednesday.
            SetDate();
        } catch (ParseException ex) {
            Logger.getLogger(UpdateAptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        updateaptDate.setDayCellFactory(dayCellFactory);
      
}
}


