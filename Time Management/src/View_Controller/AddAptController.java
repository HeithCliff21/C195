/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import Model.City;
import Model.Customer;
import static View_Controller.MainController.customerToCustName;
import static View_Controller.MainController.customerToCustomerId;
import static View_Controller.MainController.customertoCustAddressId;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import static java.util.Optional.empty;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Heith
 */
public class AddAptController implements Initializable {
    
    @FXML
    private TextField newaptClientName;
    @FXML
    private ComboBox newaptAppointmentType;
    @FXML
    private DatePicker newaptDate;
    @FXML
    private ComboBox newaptStartTime;
    @FXML
    private ComboBox newaptEndTime;
    @FXML
    private ComboBox<City> newaptLocation;
    @FXML
    private TextField newaptUrl;
    @FXML
    private TextField newaptTitle;
    @FXML
    private TextArea newaptDescription;
    @FXML
    private TextField newaptContact;
    @FXML
    private Button newAptSave;
    @FXML
    private Button newAptCancel;
    
    //Using the C Global Consulting Type of Consulting as references for a global consulting agency
    private final ObservableList<String> AppointmentType = FXCollections.observableArrayList("LeaderShip Development" , "Conflict Management", "Organization Change");
    
    private final ObservableList<String> AptStartTime = FXCollections.observableArrayList("8:00 AM","8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM");
    
    private final ObservableList<String> AptEndTime = FXCollections.observableArrayList("8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM","5:00 PM");
    
    public String setAptType(){
        String Type = (String) newaptAppointmentType.getSelectionModel().getSelectedItem();
        return Type;
    }
    //Set Location City Object to String to use for correct DateTime Format
    public String SetLocationName(){
        City city = newaptLocation.getSelectionModel().getSelectedItem();
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
        String Start = (String) newaptStartTime.getSelectionModel().getSelectedItem();
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        
    	String time24;
        time24 = date24Format.format(date12Format.parse(Start));
    	
        return time24;
    }
    
    
    public String setAptEnd() throws ParseException{
        String End = (String) newaptEndTime.getSelectionModel().getSelectedItem();
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        
    	String time24;
        time24 = date24Format.format(date12Format.parse(End));
    	
        return time24;
    }
    public String setAptDate(){
        String Date = newaptDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Date;
    }
    
    
     @FXML
    void newAptSave(ActionEvent event) throws IOException, ParseException {
        
        int customerId = customerToCustomerId();
        String title = newaptTitle.getText();
        String description = newaptDescription.getText();
        String location = SetLocationName();
        String contact = newaptContact.getText();
        String type = setAptType();
        String url = newaptUrl.getText();
        String start = setAptStart();
        String end = setAptEnd();
        String date = setAptDate();
        
        System.out.println("date: " + setAptDate());
        System.out.println("start: " + setAptStart());
        System.out.println("end: " + setAptEnd());
        System.out.println("location: " + SetLocationName());
        
        Appointment.addApt(customerId, title, description, location, date, contact, type, url, start, end);
        
        Parent UpdateCustomer = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(UpdateCustomer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void newAptCancel(ActionEvent event) throws IOException {
        
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
     public void SetDate() {
        DatePicker newaptDate = new DatePicker();
        
        newaptDate.setValue(LocalDate.now());
        newaptDate.setShowWeekNumbers(false);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newaptAppointmentType.setItems(AppointmentType);
        newaptLocation.getItems().addAll(City.allCities);
        newaptStartTime.setItems(AptStartTime);
        newaptEndTime.setItems(AptEndTime);
        newaptClientName.setText(customerToCustName());
        // Disable Monday, Tueday, Wednesday.
        SetDate();
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        newaptDate.setDayCellFactory(dayCellFactory);
}
}