package cn.ekgc.vms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>车辆管理启动器</b>
 */
@MapperScan("cn.ekgc.vms.dao")
@SpringBootApplication
public class VmsStarter {
	public static void main(String[] args) {
		SpringApplication.run(VmsStarter.class,args);
	}
}
