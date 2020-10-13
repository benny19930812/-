package _04_int;

import java.util.ArrayList;

public class CreateTable {

	public static void main(String[] args) {
				
		JDBCDAO jdbcdao = new JDBCDAO();
		

//		1.表格
		jdbcdao.dropTableOL(); //刪ORDERLIST表格
		jdbcdao.dropTableON(); //刪ORDERNUM表格
		jdbcdao.createTableOL(); //創ORDERLIST表格
		jdbcdao.createTableOU(); //創ORDERNUM表格
		System.out.println("ORDERLIST表格,ORDERNUM表格已建立");


	}

}
