package xyz.mint123.lemon.showcase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.mint123.lemon.showcase.entity.EmployeeEntity;
import xyz.mint123.lemon.showcase.repository.EmployeeRepertory;

/**
 * 样例代码 控制器
 * @author YP
 * @version 2018年1月15日
 */

@RestController
public class EmployeeController {
	
	private final EmployeeRepertory  repertory;
	
	@Autowired
	public EmployeeController(EmployeeRepertory repertory) {
		super();
		this.repertory = repertory;
	}


	/**
	 * 添加一个 员工
	 * @return
	 */
	@PostMapping("/emp/add")
	public EmployeeEntity add(@RequestParam String name){
		EmployeeEntity entity = new EmployeeEntity();
		entity.setName(name);
		if(repertory.save(entity)){
			System.out.printf("员工: %s 添加成功 ",entity);
		};
		return entity;
	}
	
	
}