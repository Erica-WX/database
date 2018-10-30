package com.database.demo;

import com.database.demo.entity.DataType;
import com.database.demo.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
@SpringBootApplication
public class DemoApplication {

	private static ExamineService examineService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String phoneNumber = "18251838318";

		//examineService.setMessageRecord(phoneNumber,101);

		//examineService.setCallRecord(phoneNumber,6,20);

		//examineService.setDataRecord(phoneNumber, 10,100, DataType.DOMESTIC_DATA);

		//examineService.setDataRecord(phoneNumber, 5,1000, DataType.LOCAL_DATA);

		/*--------上面是插入数据行为，下面是查询操作--------*/

		/**
		 * 订购套餐
		 */
		//examineService.setNewCombo(phoneNumber,5,3);

		/**
		 * 退订套餐（次月生效）
		 */
		//examineService.cancelOneCombo(phoneNumber,3,true);

		/**
		 * 退订套餐（当月生效）
		 */
		examineService.cancelOneCombo(phoneNumber,3,false);

		/**
		 * 查询通话资费
		 */
		//examineService.getExpenseOnCallCombo(phoneNumber);

		/**
		 * 查询流量资费
		 */
		//examineService.getExpenseOnDataCombo(phoneNumber);

		/**
		 * 月账单查询
		 */
		//examineService.getMonthBill(phoneNumber);


		/**
		 * 用户套餐查询
		 */
		examineService.getAllCombos(phoneNumber);


	}

	@Autowired
	public DemoApplication(ExamineService service){
		this.examineService = service;
	}
}
