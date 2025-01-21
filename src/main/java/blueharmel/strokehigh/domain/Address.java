package blueharmel.strokehigh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    @Column(length = 30, nullable = false)
    private String city;
    @Column(length = 30, nullable = false)
    private String street;

    protected Address(){

    }

    public Address(String city, String street){
        this.city = city;
        this.street = street;
    }
}
