package com.painter.demo;

import com.painter.demo.Services.Drawer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Drawer test=new Drawer();
		test.test();
		String path_json="/Users/naguimostafa/Downloads/demo/src/test.json";
		String path_xml="/Users/naguimostafa/Downloads/demo/src/test.xml";
		test.test();
	}



}
