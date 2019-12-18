package testgroup.crud_springboot.model;

import javax.persistence.*;

@Entity
@Table(name="barcodes")
public class Barcode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String barcodeId;

    @Column(length = 1024)//Change to Blob!
    private String barcodeImage;

    @OneToOne(mappedBy = "barcode")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public String getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(String barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
