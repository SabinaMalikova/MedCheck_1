package dao.daoImpl;

import dao.DoctorDao;
import dao.GenericDao;
import dataBase.DataBase;
import model.Department;
import model.Doctor;
import model.Hospital;
import myException.MyException;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoctorDaoImpl implements GenericDao<Doctor>, DoctorDao {

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                for (Doctor doctor : hospital.getDoctors()) {
                    if (doctor.getId().equals(id)) {
                        return doctor;
                    }
                }
            }
            throw new MyException("доктор с таким ID  не найден");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : DataBase.hospitals) {


        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(id)) {
                    return hospital.getDoctors();
                }
            }
            throw new MyException("больница с таким ID не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getId().equals(id)) {
                        return department.getDoctors();
                    }
                }
            }
            throw new MyException("отделение с таким ID  не найдено");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(hospitalId)) {
                    for (Doctor doctor1 : hospital.getDoctors()) {
                        if (doctor1.getFirstName().equalsIgnoreCase(doctor.getFirstName()) && doctor1.getLastName().equalsIgnoreCase(doctor.getLastName())) {
                            throw new MyException("доктор с таким ФиО уже существует ");
                        }
                    }
                    hospital.getDoctors().add(doctor);
                    return "успешно добавлено";
                }
            }
            throw new MyException("больница с таким ID не найдана");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                Iterator<Doctor> iterator = hospital.getDoctors().iterator();
                while (iterator.hasNext()) {
                    Doctor doctor = iterator.next();
                    if (doctor.getId().equals(id)) {
                        iterator.remove();
                        System.out.println("успешно удалено");
                        return;
                    }
                }
            }
            throw new MyException("отделение с таким ID  не найдено");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                List<Doctor> doctors = hospital.getDoctors();
                ListIterator<Doctor> iterator = doctors.listIterator();
                while (iterator.hasNext()) {
                    Doctor doctor1 = iterator.next();
                    if (doctor1.getId().equals(id)) {
                        iterator.set(doctor);
                        doctor.setId(doctor1.getId());
                        return "Успешно удалено";
                    }
                }
            }
            throw new MyException("отделение с таким ID  не найдено");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
