package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 2166738612959293558L;

	@Id
	@Column(name = "PRODUCT_ID", nullable = false, length = 15)
	private String productId;

	@Column(name = "VERSION", nullable = false, length = 15)
	private String version;

	public Product() {
		// Needed for Hibernate instantiation.
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", version=" + version + "]";
	}

}
