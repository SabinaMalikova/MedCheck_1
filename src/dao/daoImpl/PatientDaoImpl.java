package dao.daoImpl;

import dao.GenericDao;
import dao.PatientDao;
import dataBase.DataBase;
import model.Hospital;
import model.Patient;
import myException.MyException;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(id)) {
                    hospital.getPatients().addAll(patients);
                    return "успешно добавлено";
                }
            }
            throw new MyException("больница с таким ID  не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId().equals(id)) {
                        return patient;
                    }
                }
            }
            throw new MyException("пациент с таким ID  не найден");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge(int age) {
        Map<Integer, Patient> patientMapByAge = new LinkedHashMap<>();
        try {
            for (Hospital hospital : DataBase.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getAge() == age) {
                        patientMapByAge.put(age, patient);
                        return patientMapByAge;
                    }
                }
            }
            throw new MyException("пациентов с таким возрастом нет");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            if (ascOrDesc.equals("1")) {
                for (Hospital hospital : DataBase.hospitals) {
                    Collections.sort(hospital.getPatients(), Comparator.comparing(Patient::getAge));
                    return hospital.getPatients();
                }
            } else if (ascOrDesc.equals("2")) {
                for (Hospital hospital : DataBase.hospitals) {
                    Collections.sort(hospital.getPatients(), Comparator.comparing(Patient::getAge).reversed());
                    return hospital.getPatients();
                }
            }
            throw new MyException("такого выбора нет");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                if (hospital.getId().equals(hospitalId)) {
                    hospital.getPatients().add(patient);
                    return "успешно добавлено";
                }
            }
            throw new MyException("больница с таким ID  не найдена");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                Iterator<Patient> iterator = hospital.getPatients().iterator();
                while (iterator.hasNext()) {
                    Patient patient = iterator.next();
                    if (patient.getId().equals(id)) {
                        iterator.remove();
                        System.out.println("Успешно удалено");
                        return;
                    }
                }
            }
            throw new MyException("пациент с таким ID  не найден");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
            for (Hospital hospital : DataBase.hospitals) {
                List<Patient> patients = hospital.getPatients();
                ListIterator<Patient> iterator = patients.listIterator();
                while (iterator.hasNext()) {
                    Patient patient1 = iterator.next();
                    if (patient1.getId().equals(id)) {
                        iterator.set(patient);
                        patient.setId(patient1.getId());
                        return "Успешно обновлено";
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
