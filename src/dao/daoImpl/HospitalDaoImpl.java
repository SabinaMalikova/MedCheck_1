package dao.daoImpl;

import dao.HospitalDao;
import dataBase.DataBase;
import model.Hospital;
import model.Patient;
import myException.MyException;

import java.util.*;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        try {
            for (Hospital hospital1: DataBase.hospitals){
                if (hospital1.getHospitalName().equalsIgnoreCase(hospital.getHospitalName())){
                    throw new MyException("больница с таким названием уже существует");
                }

            }
            DataBase.hospitals.add(hospital);
            return "успешно добавлено";
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals){
                if (hospital.getId().equals(id)){
                    return hospital;
                }
            }
            throw new MyException("больница с таким ID не найдана");
        }catch (MyException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {

        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            for (Hospital hospital: DataBase.hospitals){
                if (hospital.getId().equals(id)){
                    return hospital.getPatients();
                }
            }
            throw new MyException("больница с таким ID не найдана");
        }catch (MyException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            for (Hospital hospital : DataBase.hospitals){
                if (hospital.getId().equals(id)){
                    DataBase.hospitals.remove(hospital);
                    return "успешно удалено";
                }
            }
            throw new MyException("больница с таким ID не найдана");
        }catch (MyException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalsByAddress = new LinkedHashMap<>();
        try {
            ListIterator<Hospital> iterator = DataBase.hospitals.listIterator();
            while (iterator.hasNext()) {
                Hospital hospital = iterator.next();
                if (hospital.getAddress().equalsIgnoreCase(address)) {
                    hospitalsByAddress.put(hospital.getHospitalName(), hospital);
                }
            }
            throw new MyException("больницы с таким адресом нет");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return hospitalsByAddress;
    }
}
