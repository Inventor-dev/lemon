package xyz.mint123.lemon.showcase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import xyz.mint123.lemon.showcase.entity.EmployeeEntity;

/**
 * 样例代码   数据仓储
 * {@link EmployeeEntity} {@link Repository}
 * @author YP 
 * @version 2018年1月15日
 */
@Repository
public class EmployeeRepertory {
	/**
	 * 员工集合
	 */
	private final ConcurrentMap<Long,EmployeeEntity> repertory = new ConcurrentHashMap<Long, EmployeeEntity>();
    /**
     * ID 生成器
     */
	private final static AtomicLong ID_GEN = new AtomicLong();

	/**
	 * 员工保存
	 * @param entity {@link EmployeeEntity}
	 * @return
	 */
	public boolean save(EmployeeEntity entity) {
		if(null == entity.getId()){
			entity.setId(ID_GEN.incrementAndGet());
		}
		return repertory.put(entity.getId(), entity)==null;
	}

	/**
	 * 返回所有员工数据
	 * @return
	 */
	public Collection<EmployeeEntity> findAll() {
		return repertory.values();
	}

	
	
	


}
