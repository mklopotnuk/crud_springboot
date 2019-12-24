package testgroup.crud_springboot.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testgroup.crud_springboot.dao.BarcodeDAO;
import testgroup.crud_springboot.model.Barcode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BarcodeDAOImpl implements BarcodeDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Barcode> allBarcodes() {
        return null;
    }

    @Override
    public Long add(Barcode barcode) {
        entityManager.persist(barcode);
        return barcode.getId();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void edit(Barcode barcode) {

    }

    @Override
    public Barcode getById(Long id) {
        return  entityManager.find(Barcode.class,id);
    }
}