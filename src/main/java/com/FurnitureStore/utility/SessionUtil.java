package com.FurnitureStore.utility;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SessionUtil {

	@Autowired
	HttpSession session;
	
	/**
	 * Đọc giá trị attribute trong session
	 * 
	 * @param name tên attribute
	 * @return giá trị đọc được hoặc null nếu không tồn tại
	 */
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}
	
	/**
	 * Đọc giá trị attribute trong session
	 * 
	 * @param name tên attribute
	 * @param defaultValue giá trị mặc định nếu không tồn tại
	 * @return giá trị đọc được
	 */
	public <T> T get(String name, Object defaultValue) {
		Object value = session.getAttribute(name);
		if(value == null) {
			return (T) defaultValue;
		}
		return (T) value;
	}
	
	/**
	 * Thay đổi hoặc tạo mới attribute trong session
	 * 
	 * @param name  tên attribute
	 * @param value giá trị attribute
	 */
	public void set(String name, Object value){
		session.setAttribute(name, value);
	}
	
	/**
	 * Xoá attribute trong session
	 * 
	 * @param name tên attribute cần xoá
	 */
	public void remove(String name) {
		session.removeAttribute(name);
	}
	
}
