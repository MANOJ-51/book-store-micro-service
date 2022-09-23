package com.bridgelabz.orders.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * Purpose:Creating model for order
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "order_details")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private LocalDate orderDate;
	private Long userId;
	private boolean isCancel;
	private double price;
	private Long bookId;
	private Long cartId;
	private int quantity;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<AddressModel> address;

	public OrderModel() {
		super();
	}
}
