package es.gusguma.template.microservice.user.controller;

import es.gusguma.template.microservice.user.exceptions.BadRequestException;
import es.gusguma.template.microservice.user.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserBusinessController {

    private UserDao userDao;

    @Autowired
    public UserBusinessController(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserBasicDto create(UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.getNick(), userCreationDto.getEmail(),
                new Address(userCreationDto.getCountry(), userCreationDto.getCity(), userCreationDto.getStreet()));
        this.userDao.save(user);
        return new UserBasicDto(user);
    }

    public UserBasicDto readNick(String id) {
        return new UserBasicDto(this.findUserByIdAssured(id));
    }

    private User findUserByIdAssured(String id) {
        return this.userDao.findById(id).orElseThrow(() -> new NotFoundException("User id: " + id));
    }

    public void updateNick(String id, String nick) {
        User user = this.findUserByIdAssured(id);
        user.setNick(nick);
        this.userDao.save(user);
    }

    public void patch(String id, UserPatchDto userPatchDto) {
        User user = this.findUserByIdAssured(id);
        switch (userPatchDto.getPath()) {
            case "email":
                user.setEmail(userPatchDto.getNewValue());
                break;
            case "country":
                user.getAddress().setCountry(userPatchDto.getNewValue());
                break;
            case "city":
                user.getAddress().setCity(userPatchDto.getNewValue());
                break;
            default:
                throw new BadRequestException("UserPatchDto is invalid");
        }
        this.userDao.save(user);
    }

}
