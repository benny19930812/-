package _14_listAP.service;

import java.util.Collection;
import java.util.List;

import _14_init.ProductBean;

public interface ProductServiceInterface {

	int getTotalPages();

	List<ProductBean> getPageBooks();

	void getRecordCounts();

	List<String> getCategory();

	String getCategoryTag();

	int updateProduct(ProductBean bean);

	int deleteProduct(int no);

	int saveBook(ProductBean bean);

	void setSelected(String selected);

	int getPageNo();

	void setPageNo(int pageNo);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);

	ProductBean getProduc(int bookId);

	Collection<ProductBean> getSerachAP(String serachInput);

}