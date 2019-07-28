package com.shridhar.billing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shridhar.billing.dto.ProductDTO;

public class ProductDAO {
	public boolean bulkAdd(ArrayList<ProductDTO> products) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = CommonDAO.getPostgresConnection();
		boolean isSuccess = false;
		con.setAutoCommit(false);
		pstmt = con.prepareStatement("insert into product(id, name, price, quantity) values(?, ?, ?, ?)");
		try {
		for(ProductDTO product : products) {
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getQuantity());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		con.commit();
		isSuccess=true;
		}
		catch (SQLException e) {
			// TODO: handle exception
			con.rollback();
			isSuccess = false;
		}
		return isSuccess;
	}
	public boolean isAdded(ProductDTO productDTO) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = CommonDAO.getPostgresConnection();
		pstmt = con.prepareStatement("insert into product(id,name,price) values(?,?,?)");
		pstmt.setInt(1, productDTO.getId());
		pstmt.setString(2, productDTO.getName());
		pstmt.setInt(3, productDTO.getPrice());
		int recordCount = pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return recordCount>0;
	}
	
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = CommonDAO.getPostgresConnection();
		pstmt = con.prepareStatement("delete from product where id=?");
		pstmt.setInt(1, id);
		int recordCount = pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return recordCount>0;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ProductDTO p = new ProductDTO();
		p.setId(2);
		p.setName("lg");
		p.setPrice(90000);
		ProductDAO pr = new ProductDAO();
		String msg = pr.isAdded(p)?"Record Added":"Not Added";
		System.out.println(msg);
		msg = pr.delete(1)?"Record Deleted":"Not Deleted";
		System.out.println(msg);

	}

}
