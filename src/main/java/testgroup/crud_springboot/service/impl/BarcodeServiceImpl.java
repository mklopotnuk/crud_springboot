package testgroup.crud_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.crud_springboot.dao.BarcodeDAO;
import testgroup.crud_springboot.model.Barcode;
import testgroup.crud_springboot.service.BarcodeService;

import java.util.List;

@Service
public class BarcodeServiceImpl implements BarcodeService {
     private BarcodeDAO barcodeDAO;

    @Autowired
    public BarcodeServiceImpl(BarcodeDAO barcodeDAO) {
        this.barcodeDAO = barcodeDAO;
    }

    @Override
    public List<Barcode> allBarcodes() {
        return barcodeDAO.allBarcodes();
    }

    @Override
    public void add(Barcode barcode) {
        barcodeDAO.add(barcode);
    }

    @Override
    public void delete(Barcode barcode) {
        barcodeDAO.delete(barcode.getId());
    }

    @Override
    public void edit(Barcode barcode) {
        barcodeDAO.edit(barcode);
    }

    @Override
    public Barcode getById(Long id) {
        return barcodeDAO.getById(id);
    }
}
