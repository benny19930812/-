package _14_listAP;

import java.util.List;

import _14_init.ProductBean;

public interface ProductDAO {

	int getTotalPages();

	List<ProductBean> getPageBooks();

	long getRecordCounts();

	List<String> getCategory();

	String getCategoryTag();

	// 修改一筆商品資料，不改圖片
	int updateProduct(ProductBean bean);

	int deleteProduct(int no);

	int saveBook(ProductBean bean);

	void setSelected(String selected);

	int getPageNo();

	void setPageNo(int pageNo);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);

	ProductBean getProduc(int bookId);

	List<ProductBean> getSerachAP(String serachInput);

}