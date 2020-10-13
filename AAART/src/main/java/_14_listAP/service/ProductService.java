package _14_listAP.service;

import java.util.Collection;
import java.util.List;

import _14_init.ProductBean;
import _14_listAP.ProductDAO;
import _14_listAP.ProductDaoImpl_Jdbc;

public class ProductService implements ProductServiceInterface {
	ProductDAO dao;
	
	public ProductService() {
		this.dao = new ProductDaoImpl_Jdbc();
	}
	
	@Override
	public int getTotalPages() {
		return dao.getTotalPages();
	}
	
	@Override
	public List<ProductBean> getPageBooks(){
		return dao.getPageBooks();
	}

	@Override
	public void getRecordCounts(){
		dao.getRecordCounts();
	}
	
	@Override
	public List<String> getCategory(){
		return dao.getCategory();
	}

	@Override
	public String getCategoryTag() {
		return dao.getCategoryTag();
	}
	
	@Override
	public int updateProduct(ProductBean bean) {
		return dao.updateProduct(bean);
	}
	
	@Override
	public int deleteProduct(int no) {
		return dao.deleteProduct(no);
	}
	
	@Override
	public int saveBook(ProductBean bean) {
		return dao.saveBook(bean);
	}
	
	@Override
	public void setSelected(String selected) {
		dao.setSelected(selected);
	}
	
	@Override
	public int getPageNo() {
		return dao.getPageNo();
	}
	
	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}
	
	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}
	
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
		
	}
	
	@Override
	public ProductBean getProduc(int bookId) {
		return getProduc(bookId);
	}

	@Override
	public Collection<ProductBean> getSerachAP(String serachInput) {
		return dao.getPageBooks();
	}
	
	
}
