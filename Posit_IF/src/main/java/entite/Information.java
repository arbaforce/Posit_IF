
package entite;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Information implements Serializable {

    @Column(unique = true)
    private String mail;
    
    private String address;
    
    private String phone;
    
    private String password;

    public Information() {
    }
    
    public Information(String mail, String address, String phone, String password) {
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Information{" + "mail=" + mail + ", address=" + address + ", phone=" + phone + ", password=" + password + '}';
    }
 
}
