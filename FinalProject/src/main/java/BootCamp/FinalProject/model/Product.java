package BootCamp.FinalProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * This class represents all the product’s information.
 *
 */
public class Product {

	private String sku;
	private String description;
	private String category;
	private double price;
	private int amount;

	public Product(String sku, String description, String category, double price) {
		this.sku = sku;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	@JsonProperty("SKU")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");
		sb.append("  SKU: ").append(sku).append("\n");
		sb.append("  description: ").append(description).append("\n");
		sb.append("  price: ").append(price).append("\n");
		sb.append("  amount: ").append(amount).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
