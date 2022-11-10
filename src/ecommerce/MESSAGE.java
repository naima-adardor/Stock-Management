package ecommerce;

public class MESSAGE {
private int id;
private String msg;
public MESSAGE(int id, String msg) {
	super();
	this.id = id;
	this.msg = msg;
}

public MESSAGE(String msg) {
	super();
	this.msg = msg;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}

}
