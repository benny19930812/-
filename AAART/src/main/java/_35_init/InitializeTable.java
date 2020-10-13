package _35_init;

import java.util.List;

import _35_geoSearch.model.PositionBean;
import _35_init.util.CreateTableForDB;
import _35_init.util.DataForDB;
import _35_geoSearch.model.MainTableBean;

public class InitializeTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateTableForDB init = new CreateTableForDB();
		DataForDB dataForDB = new DataForDB();
		
//		創建所需表格
		init.dropTableMT();
		init.createTableMT();
		init.dropTablePT();
		init.createTablePT();
		init.dropTableMB();
		init.createTableMB();
		init.dropTableBURI();
		init.createTableBURI();
		init.dropTableBUR();
		init.createTableBUR();
		
//		MainTable表格插入資料
		List<MainTableBean> listMT1 = dataForDB.readJsonToMT(); //讀取網路上的json到MainTableBean
		dataForDB.mtWriteDB(listMT1); //寫入資料到資料庫內的MainTable表格
		dataForDB.stringWriteCSV(); //輸出為轉換經緯度而產出的csv檔
//		Position表格插入資料
		List<PositionBean> listPT = dataForDB.readCSVtoPT(); //讀取內政部回傳的csv檔到PositionBean
		List<MainTableBean> listMT2 = dataForDB.readUIDtoMT(); //只讀取主表格內的UID欄位
		dataForDB.ptmtWriteDB(listPT,listMT2); //寫入資料到資料庫內的Position表格
	}

}
