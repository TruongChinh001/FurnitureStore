package com.FurnitureStore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_date")
	private Date createDate = new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "update_time")
    private Date updateTime;
	
	@Column(name = "address_line_1", nullable = false, length = 255)
    private String addressLine1;

    @Column(length = 45)
    private String provinceCity;

    @Column(length = 45)
    private String district;

    @Column(length = 45)
    private String wardVillage;

    @Column(length = 45)
    private String hamlet;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
	

}
