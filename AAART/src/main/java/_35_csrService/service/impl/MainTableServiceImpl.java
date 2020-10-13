package _35_csrService.service.impl;

import java.util.List;

import _35_csrService.dao.impl.MainTableDaoImpl;
import _35_csrService.model.MainTableBean;

public class MainTableServiceImpl {
	
	private MainTableDaoImpl dao;
	
	//重要，該建構子代表建構服務層物件的同時，也new了一個dao，沒new會一直叫不到dao物件哦
	//這個dao是服務層物件的屬性，俄羅斯娃娃的概念
	public MainTableServiceImpl() {
		this.dao = new MainTableDaoImpl();
	}
	
	public int getActCategory() {
		return dao.getActCategory();
	}
	
	public void setActCategory(int actCategory) {
		dao.setActCategory(actCategory);
	}
	
	//資料存取的底層方法寫在dao物件內，這邊沒有特別service要做
	//那就直接return dao.方法
	public List<MainTableBean> selectDBtoMTbyCat() {
		return dao.selectDBtoMTbyCat();
	}
	
	public List<MainTableBean> selectDBtoMT() {
		return dao.selectDBtoMT();
	}
	
	
}
