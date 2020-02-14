package testgroup.crud_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import testgroup.crud_springboot.security.config.BarcodeProperties;
import testgroup.crud_springboot.dao.UserDAO;
import testgroup.crud_springboot.model.Barcode;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.service.UserService;


import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BarcodeProperties BarcodeProperties;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder, BarcodeProperties appProperties) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.BarcodeProperties = appProperties;
    }

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public Long add(User user) {
        Barcode barcode = new Barcode();
        RestTemplate restTemplate = new RestTemplate();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Long userId = userDAO.add(user);
        String formattedId = String.format("S%06d", userId);
        String url = BarcodeProperties.getUrlBarcodeGenerator() + formattedId + "." + BarcodeProperties.getBarcodeFileFormat() + "?resolution=" + BarcodeProperties.getBarcodeResolution();
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        barcode.setBarcodeId(formattedId);
        String string = Base64.getEncoder().encodeToString(imageBytes);
        barcode.setBarcodeImage(string);
        user.setBarcode(barcode);
        return userId;
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user.getId());
    }

    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User findByUserName(String name) {
        return userDAO.findByUserName(name);
    }

    @Override
    public boolean isUserExist(String name) {
        return userDAO.findByUserName(name) == null;
    }
}
