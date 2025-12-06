
package com.project.qrform.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserForm {
  @Id
   @GeneratedValue
  private Long id;

  @NotBlank private String name;
  @Email @NotBlank private String email;
  @NotBlank private String  phone;
  @NotBlank
    private String program;
    
    private Double latitude;  
    private Double longitude; 

  // getters + setters
  public Long getId(){ return id; }
  public void setId(Long id){ this.id=id; }

  public String getName(){ return name; }
  public void setName(String name){ this.name=name; }
  public String getEmail(){ return email; }
  public void setEmail(String email){ this.email=email; }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
 

}
