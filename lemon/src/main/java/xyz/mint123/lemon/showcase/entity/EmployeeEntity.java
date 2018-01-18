package xyz.mint123.lemon.showcase.entity;

import java.io.Serializable;
/**
 * 样例实体  
 * @author YP
 * @version 2018年1月15日
 */
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = -4103150514747058350L;
	
	/**
	 * 编号 
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	public EmployeeEntity() {
		
	}
	
	

	public EmployeeEntity(Long id) {
		super();
		this.id = id;
	}
	
	public EmployeeEntity(String name) {
		super();
		this.name = name;
	}
	
	public EmployeeEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", name=" + name + "]";
	}

	
	
	
	
}
