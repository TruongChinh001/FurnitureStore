package com.FurnitureStore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(name = "main_image", nullable = false, length = 255)
	private String mainImage;
	
	@Column(nullable = false)
	private Float price;
	
	@Column
	private Float discount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "day_of_manufacture")
	private Date dayOfManufacture;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@Column(nullable = false)
	private Integer inventory;
	
	@Column(name = "in_stock")
	private Boolean inStock;
	
	@Column(name = "short_desc", length = 512, columnDefinition="TEXT")
    @Type(type="text")
    private String shortDesc;

    @Column(name = "long_desc", length = 65535, columnDefinition="TEXT")
    @Type(type = "text")
    private String longDesc;
	
	@Column(name = "average_rating")
	private Float averageRaing;
	
	@Column(name = "review_count")
	private Integer reviewCount;
	
	@Column
	private Float length;
	
	@Column
	private Float width;
	
	@Column
	private Float height;
	
	@Column
	private Float weight;
	
	@Transient
    public Float getRealPrice() {
        return this.price - (this.price * this.discount / 100);
    }
	
	@ManyToOne()
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne()
	@JoinColumn(name = "unit_product_id")
	private UnitProduct unitProduct;
	
	@ManyToOne()
	@JoinColumn(name = "material_id")
	private Material material;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;
	
}
