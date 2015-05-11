package models;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Date;
import java.util.Objects;

/**
 * Created by annikamagnusson on 20/04/15.
 *
 */
@Table(keyspace = "scinote", name = "user")
public class User {
    @PartitionKey
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Date date;

    public User(){

    }

    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof User){
            User that = (User) other;
            return Objects.equals(this.firstName, that.firstName) &&
                    Objects.equals(this.lastName, that.lastName) &&
                    Objects.equals(this.email, that.email) &&
                    Objects.equals(this.password, that.password);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
