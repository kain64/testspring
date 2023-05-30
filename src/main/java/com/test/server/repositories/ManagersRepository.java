package com.test.server.repositories;

import com.test.server.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Managers mongo Repository
 */
public interface ManagersRepository extends MongoRepository<Manager, String> {
}
