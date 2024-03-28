package dao.daoImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import dataBase.DataBase;
import model.Department;
import model.Hospital;
import myException.MyException;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(id)) {
                    return hospital.getDepartments();
                }
            }
            throw new MyException("больница с таким ID не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getDepartmentName().equalsIgnoreCase(name)) {
                        return department;
                    }
                }
            }
            throw new MyException("больница с таким ID не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    @Override
    public String add(Long hospitalId, Department department) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(hospitalId)) {
                    hospital.getDepartments().add(department);
                    return "успешно добавлено";
                }
            }
            throw new MyException("больница с таким ID не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                Iterator<Department> iterator = hospital.getDepartments().iterator();
                while (iterator.hasNext()) {
                    Department department = iterator.next();
                    if (department.getId() == id) {
                        iterator.remove();
                        System.out.println("Успешно удалено");
                        return;
                    }
                }
            }
            throw new MyException("Отделение с таким ID не найдено");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                List<Department> departments = hospital.getDepartments();
                ListIterator<Department> iterator = departments.listIterator();
                while (iterator.hasNext()) {
                    Department department1 = iterator.next();
                    if (department1.getId().equals(id)) {
                        iterator.set(department);
                        department.setId(department1.getId());
                        return "Успешно обновлено";
                    }
                }
            }
            throw new MyException("Отделение с таким ID не найдено");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
