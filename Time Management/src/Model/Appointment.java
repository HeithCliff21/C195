/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class Appointment {
    
    public int appointmentId;
    public int customerId;
    public int userId;
    public String type;
    public String title;
    public String description;
    public String location;
    public String contact;
    public String url;
    public String start;
    public String end;
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    public Appointment(int appointmentId, int customerId, int userId, String type, String title, String description, String location, String contact, String url, String start, String end) {
        this.setId(appointmentId);
        this.setCustId(customerId);
        this.setUserId(userId);
        this.setTitle(title);
        this.setType(type);
        this.setDescription(description);
        this.setLocation(location);
        this.setContact(contact);
        this.setUrl(url);
        this.setStart(start);
        this.setEnd(end);
    }
    public int getAppointmentId(){
        return this.appointmentId;
    }    
    
    public int getCustomerId(){
        return this.customerId;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getContact(){
        return this.contact;
    }
    
    public String getUrl(){
        return this.url;
    }

    public String getStart(){
        return this.start;
    }
    
    public String getEnd(){
        return this.end;
    }
    
    public int getUserId(){
        return this.userId;
    }
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type= type;
    }
     
    public void setUserId(int userId){
        this.userId= userId;
    }
    
    public void setId(int appointmentID){
        this.appointmentId= appointmentID;
    }
    
    public void setCustId(int customerID){
        this.customerId = customerID;
    }
    
    public void setTitle(String title){
        this.title= title;
    }
    
    public void setDescription(String description){
        this.description= description;
    }
    
    public void setLocation(String location){
        this.location= location;
    }
    
    public void setContact(String contact){
        this.contact= contact;
    }
    
    public void setUrl(String Url){
        this.url = url;
    }
    
    public void setStart(String start){
        this.start= start;
    }
    
    public void setEnd(String end){
        this.end= end;
    }
    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static ObservableList<Appointment> getAllAppointments(){
        allAppointments.clear();
        try {
            Statement statement = DataBase.conn.createStatement();
            String query = "SELECT * FROM appointment;";
            ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
                 Appointment newAppointment = new Appointment(
                 rs.getInt("appointmentId"),
                 rs.getInt("customerId"),
                 rs.getInt("userId"),
                 rs.getString("type"),
                 rs.getString("title"),
                 rs.getString("description"),
                 rs.getString("location"),
                 rs.getString("contact"),
                 rs.getString("url"),
                 rs.getString("start"),
                 rs.getString("end"));
                 
                 
                 System.out.println("title: " + newAppointment.getTitle());
                 System.out.println("description: " + newAppointment.getDescription());
                 System.out.println("location: " + newAppointment.getLocation());
                 System.out.println("contact: " + newAppointment.getContact());
                 System.out.println("url: " + newAppointment.getUrl());
                 System.out.println("start: " + newAppointment.getStart());
                 System.out.println("end: " + newAppointment.getEnd());
                 
             
                allAppointments.add(newAppointment);
             }
             statement.close();
             return allAppointments;
             
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
    }
}
   
        
//        public String getDateTimeFormat() {
//        
//            
//            Timestamp ts = Timestamp.valueOf(this.aptStart.get());
//        ZonedDateTime zdt;
//        ZoneId zid;
//        LocalTime lt;
//        if(this.aptLocation.get().equals("New York")) {
//            zid = ZoneId.of("America/New_York");
//            zdt = ts.toLocalDateTime().atZone(zid);
//            lt = zdt.toLocalTime().minusHours(4);
//        } else if(this.aptLocation.get().equals("Phoenix")) {
//            zid = ZoneId.of("America/Phoenix");
//            zdt = ts.toLocalDateTime().atZone(zid);
//            lt = zdt.toLocalTime().minusHours(7);
//        } else {
//            zid = ZoneId.of("Europe/London");
//            zdt = ts.toLocalDateTime().atZone(zid);
//            lt = zdt.toLocalTime().plusHours(1);
//}
//     
//    }
    public static String getTime(String time){
       LocalDateTime ldt =  LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.s"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return ldt.format(formatter);
    }
    
    
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static String getDateTime(String date, String time, String location) {
        String dateInString = date + " " + time;
        //String dateInString = "22-1-2015 10:15:55 AM";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZoneId ZoneLoc = ZoneId.of(location);
        
        ZonedDateTime Appointment = ldt.atZone(ZoneLoc);
        
        LocalDateTime utc = LocalDateTime.ofInstant(Appointment.toInstant(), ZoneId.of("UTC"));
        
        String AptUtc = utc.toString();
        return AptUtc;      
}
    
     public static boolean addApt(int customerId, String title, String description, String location, String date, String contact, String type, String url, String start, String end) {
                try {
                    String startDateTime = getDateTime(date, start, location);
                    String endDateTime = getDateTime(date, end, location);
                    Statement statement = DataBase.conn.createStatement();
                    User user = User.getCurrentUser();
                    String username = user.getUsername();
                    int userId = user.getUserId();
                    int appointmentId = allAppointments.size() +1;
                    String query = "INSERT INTO appointment SET appointmentId=" + appointmentId + ", customerId=" + customerId + ", userId=" + userId + ", title='" + title + "', description='" +
                    description + "', location='" + location + "', contact='" + contact + "', type='" + type + "', url='" + url + "', start='" + startDateTime + "', end='" + endDateTime + 
                            "', createDate=CURRENT_TIMESTAMP, createdBy='" + username + "', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='" + username + "'";
            int update = statement.executeUpdate(query);
            if(update == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
}
//      public static boolean updateApt(int customerId, String title, String description, String location, String date, String contact, String type, String url, String start, String end) {
//        try {
//                    String startDateTime = getDateTime(date, start, location);
//                    String endDateTime = getDateTime(date, end, location);
//                    Statement statement = DataBase.conn.createStatement();
//                    User user = User.getCurrentUser();
//                    String username = user.getUsername();
//                    int userId = user.getUserId();
//                    String query = "UPDATE appointment SET title='" + title + "', description='" + description + "', contact='" +
//                        contact + "', location='" + location + "', start='" + tsStart + "', end='" + tsEnd + "' WHERE " +
//                        "appointmentId='" + id + "'";
//                    int update = statement.executeUpdate(query);
//                    if(update == 1) {
//                        return true;
//                    }
//        } catch (SQLException e) {
//            System.out.println("SQLException: " + e.getMessage());
//        }
//        return false;
//}
}