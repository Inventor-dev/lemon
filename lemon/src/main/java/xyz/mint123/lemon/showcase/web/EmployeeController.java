package xyz.mint123.lemon.showcase.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.mint123.lemon.core.util.CacheUtil;
import xyz.mint123.lemon.core.util.RedisUtils;
import xyz.mint123.lemon.showcase.entity.EmployeeEntity;
import xyz.mint123.lemon.showcase.repository.EmployeeRepertory;

/**
 * 样例代码 控制器
 * @author lemon
 * @version 2018年1月15日
 */

@RestController
public class EmployeeController {
	
	private final EmployeeRepertory  repertory;

    @Autowired
	private RedisUtils redisUtils;

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

	@GetMapping("/test")
	public Collection<String> test(){
		EmployeeEntity entity = new EmployeeEntity();
		entity.setName("测试");
        CacheUtil.getInstance().getCache().put("test",entity);
        redisUtils.put("test",entity,-1);
		return CacheUtil.getInstance().getCacheNames();
	}
	
}
