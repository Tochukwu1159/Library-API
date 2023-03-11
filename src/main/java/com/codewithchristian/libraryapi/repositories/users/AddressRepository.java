package com.codewithchristian.libraryapi.repositories.users;


import com.codewithchristian.libraryapi.models.users.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
