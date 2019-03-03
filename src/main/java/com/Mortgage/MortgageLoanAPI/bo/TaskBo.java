package com.Mortgage.MortgageLoanAPI.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mortgage.MortgageLoanAPI.dao.IEmployeeDao;
import com.Mortgage.MortgageLoanAPI.dao.ITaskDao;
import com.Mortgage.MortgageLoanAPI.models.Employee;
import com.Mortgage.MortgageLoanAPI.models.Task;
import com.Mortgage.MortgageLoanAPI.utils.Constants;
import com.Mortgage.MortgageLoanAPI.utils.SendEmail;

@Service
public class TaskBo {
	
	@Autowired
	private ITaskDao taskDao;
	
	@Autowired
	private IEmployeeDao employeeDao;

	public Map<String, Object> saveUpdate(Task task) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		String cr = null;
		try {
			
			if (task.getAssign_to() == null) {
				rsMap.put("missing", "Assign to should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (task.getTask_date() == null) {
				rsMap.put("missing", "Task date should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (task.getCreated_by() == null) {
				rsMap.put("missing", "Created by should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if (task.getId() == null) {
				task.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
			} else {
				Task e = taskDao.findById(task.getId()).orElse(null);
				task.setCreated_on(e.getCreated_on());
			}
			
			if (task.getId() == null) {
				task.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
				cr = "create";
			} else {
				Task e =  taskDao.findById(task.getId()).orElse(null);
				task.setCreated_on(e.getCreated_on());
			}
			
			task.setRead(false);
			task.setDelete(false);
			task.setModifed_on((new java.sql.Timestamp(new java.util.Date().getTime())));
			t = taskDao.save(task);
			Employee em = employeeDao.findById(task.getAssign_to()).orElse(null);
			
			if(cr != null && em != null) {
				SendEmail e = new SendEmail();
				e.email(Constants.EMAIL_SUBJECT_TASK, em.getEmail(), "Your task is scheduled on : " + task.getTask_date());
			}
			
			rsMap.put("task", t);
			rsMap.put("message", Constants.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;

	}

	public List<Task> findAll() {
		List<Task> list = new ArrayList<>();

		try {
			list = (List<Task>) taskDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Task> getTaskByEmpId(Long id) {
		List<Task> list = new ArrayList<>();

		try {
			list = (List<Task>) taskDao.getTaskByEmpId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Task findById(Long id) {
		Task t = new Task();
		try {
			t = taskDao.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	public Map<String, Object> remove(Long id) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Task ts = taskDao.findById(id).orElse(null);
			if (ts != null) {
				ts.setDelete(true);
				t = taskDao.save(ts);
				rsMap.put("message", t);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public Map<String, Object> changeRead(Long id) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Task ts = taskDao.findById(id).orElse(null);
			if (ts != null) {
				ts.setRead(true);
				t = taskDao.save(ts);
				rsMap.put("message", t);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

}
