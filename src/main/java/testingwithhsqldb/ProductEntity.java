/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

/**
 *
 * @author Matthias
 */
public class ProductEntity {
    // TODO : ajouter les autres propriétés
	private int productId;
	private String name;
	private int Price;

	public ProductEntity(int productId, String name, int price) {
		this.productId = productId;
		this.name = name;
		this.Price = price;
	}

	/**
	 * Get the value of customerId
	 *
	 * @return the value of customerId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the value of addressLine1
	 *
	 * @return the value of addressLine1
	 */
	public int getPrice() {
		return Price;
	}
}
