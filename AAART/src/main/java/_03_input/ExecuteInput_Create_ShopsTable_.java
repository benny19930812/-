package _03_input;

import java.util.ArrayList;

public class ExecuteInput_Create_ShopsTable_ {

	public static void main(String[] args) {
		
		CulturalAndCreativeShops_Input input = new CulturalAndCreativeShops_Input();
		
		//建立DB表格
		input.createTableForDB();
		
		//讀取Json轉換成list
		ArrayList<CultureAndCreativeShopsTable> list = input.readJsonToTable();
		
		//將list讀進DB
		input.creatShopsInfoTable(list);
		
	}

}
