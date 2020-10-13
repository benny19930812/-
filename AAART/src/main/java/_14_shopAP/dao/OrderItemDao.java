package _14_shopAP.dao;

import java.sql.Connection;

import _14_shopAP.orderbean.OrderItemBeanAP;

public interface OrderItemDao {

	int updateProductStock(OrderItemBeanAP oibAP);

	void setConnection(Connection conn);

}