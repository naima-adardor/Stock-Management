module ecommerce {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	exports ecommerce;
	opens ecommerce to javafx.graphics, javafx.fxml, javafx.base;
	
	
}
