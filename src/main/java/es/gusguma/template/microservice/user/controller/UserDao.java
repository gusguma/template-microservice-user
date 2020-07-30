package es.gusguma.template.microservice.user.controller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
}
