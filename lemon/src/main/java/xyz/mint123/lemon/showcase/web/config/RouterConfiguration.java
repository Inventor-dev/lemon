package xyz.mint123.lemon.showcase.web.config;

import reactor.core.publisher.Flux;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import xyz.mint123.lemon.showcase.entity.EmployeeEntity;
import xyz.mint123.lemon.showcase.repository.EmployeeRepertory;

/**
 * 路由配置 demo
 * @author lemon
 * @version 2018年1月15日
 */
@Configuration
public class RouterConfiguration {
	
	/**
	 * spring 5.0  
	 * 请求  serverRequest
	 * 响应 serverResponse
	 * 好处{支持自定义 } 
	 * Flux   是 0-N 个对象集合
	 * Mono   是 0-1 个对象集合
	 * Flux , Mono 异步非阻塞     都是 Publisher  发布
	 * 
	 */
	@Bean
	public RouterFunction<ServerResponse> findAll(@Autowired EmployeeRepertory repertory ){
		return RouterFunctions.route(RequestPredicates.GET("/emp/find/all"),
				request ->{
					Flux<EmployeeEntity> empFlux = Flux.fromIterable(repertory.findAll());
					return ServerResponse.ok().body(empFlux,EmployeeEntity.class);
				} );
	}
	
	
	
	
}
