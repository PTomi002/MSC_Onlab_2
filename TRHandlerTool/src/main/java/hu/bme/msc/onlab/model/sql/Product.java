package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 2166738612959293558L;

	private String productId;

	private String version;

	public Product() {
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
