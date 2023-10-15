package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Checkout;
import com.alquiler.reservas.entity.User;

@Repository
public interface CheckoutRepository extends CrudRepository<Checkout, Long>{
	
	
	public Checkout findByApartadoAndUser(Apartado apartado, User user);
	
	
	
}
