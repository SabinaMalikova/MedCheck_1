import dao.daoImpl.DepartmentDaoImpl;
import dataBase.DataBase;
import dataBase.GeneratedId;
import enums.Gender;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import service.serviceImpl.DepartmentServiceImpl;
import service.serviceImpl.DoctorServiceImpl;
import service.serviceImpl.HospitalServiceImpl;
import service.serviceImpl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        PatientServiceImpl patientService = new PatientServiceImpl();


        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ МЕНЮ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                          *Больница*                                 |          *Отделение*                      |        *Доктор*                            |         *Пациент*
                    1. Добавить больницу                             |   7.  Добавить отделение                  |  12. Добавить доктора                      |   19. Добавить пациента
                    2. Найти больницу по ID                          |   8.  Удалить отделение                   |  13. Удалить доктора                       |   20. Удалить пациента
                    3. Посмотреть все больницы                       |   9.  Обновить отделение                  |  14. Обновить доктора                      |   21. Обновить пациента
                    4. Посмотреть всех пациентов из больницы(по ID)  |  10. Посмотреть все отделения больницы    |  15. Посмотреть  доктора                   |   22. Добавить пациентов в больницу
                    5. Удалить больницу                              |  11. Найти отделение по названию          |  16. Назначить доктора в отделение         |   23. Посмотреть пациента по ID 
                    6. Посмотреть все больницы по адресу             |                                           |  17. Посмотреть всех докторов в больнице   |   24. Посмотреть пациента по возрасту
                    0.  выйти                                        |                                           |  18. Посмотреть всех докторов в отделении  |   25. Сортировка пациентов по возрасту
                    _________________________________________________|___________________________________________|____________________________________________|______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________                                             
                    """);
            switch (new Scanner(System.in).nextLine()) {
                case "1": {
                    Hospital hospital = new Hospital();
                    System.out.println("Введите название больницы: ");
                    hospital.setHospitalName(new Scanner(System.in).nextLine());
                    System.out.println("Введите вдрес больницы: ");
                    hospital.setAddress(new Scanner(System.in).nextLine());
                    hospital.setId(GeneratedId.genHospitalId());
                    System.out.println("ID болницы: " + hospital.getId());
                    System.out.println(hospitalService.addHospital(hospital));
                    break;
                }
                case "2": {
                    System.out.println("Введите ID больницы: ");
                    System.out.println(hospitalService.findHospitalById(new Scanner(System.in).nextLong()));
                    break;
                }
                case "3": {
                    System.out.println(hospitalService.getAllHospital());
                    break;
                }
                case "4": {
                    System.out.println("Введите ID больницы: ");
                    System.out.println(hospitalService.getAllPatientFromHospital(new Scanner(System.in).nextLong()));
                    break;
                }
                case "5": {
                    System.out.println("Введите ID больницы: ");
                    System.out.println(hospitalService.deleteHospitalById(new Scanner(System.in).nextLong()));
                    break;
                }
                case "6": {
                    System.out.println("Введите адрес больницы: ");
                    System.out.println(hospitalService.getAllHospitalByAddress(new Scanner(System.in).nextLine()));
                    break;
                }
                case "7": {
                    Department department = new Department();
                    System.out.println("Введите название отдела: ");
                    department.setDepartmentName(new Scanner(System.in).nextLine());
                    department.setId(GeneratedId.genDepartmentId());
                    System.out.println("ID отдела: " + department.getId());
                    System.out.println("Введите ID больницы куда нужно добавить отделение: ");
                    System.out.println(departmentService.add(new Scanner(System.in).nextLong(), department));
                    break;
                }
                case "8": {
                    System.out.println("Введите ID  отделения: ");
                    departmentService.removeById(new Scanner(System.in).nextLong());
                    break;
                }
                case "9": {
                    Department department = new Department();
                    System.out.println("Введите новое название отделения: ");
                    department.setDepartmentName(new Scanner(System.in).nextLine());
                    System.out.println("Введите ID отделения котрое нужно обновить: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(departmentService.updateById(id, department));
                    break;
                }
                case "10": {
                    System.out.println("Введите ID больницы: ");
                    System.out.println(departmentService.getAllDepartmentByHospital(new Scanner(System.in).nextLong()));
                    break;
                }
                case "11": {
                    System.out.println("Введите название отделения: ");
                    System.out.println(departmentService.findDepartmentByName(new Scanner(System.in).nextLine()));
                    break;
                }
                case "12": {
                    Doctor doctor = new Doctor();
                    System.out.println("Введите имя доктора: ");
                    doctor.setFirstName(new Scanner(System.in).nextLine());
                    System.out.println("Введите фамилию доктора: ");
                    doctor.setLastName(new Scanner(System.in).nextLine());
                    System.out.println("Введите гендер: F или M ");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            doctor.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                            isTrue = true;
                        } catch (IllegalArgumentException e) {
                            System.err.println("такого значения нет, введите снова");
                        }
                    }
                    System.out.println("Введите опыт работы: ");
                    doctor.setExperienceYear(new Scanner(System.in).nextInt());
                    doctor.setId(GeneratedId.genDoctorId());
                    System.out.println("Личный ID: " + doctor.getId());
                    System.out.println("Введите ID больницы в котрую нужно добавить: ");
                    System.out.println(doctorService.add(new Scanner(System.in).nextLong(), doctor));
                    break;
                }
                case "13": {
                    System.out.println("Введите ID доктора: ");
                    doctorService.removeById(new Scanner(System.in).nextLong());
                    break;
                }
                case "14": {
                    Doctor doctor = new Doctor();
                    System.out.println("Введите новое имя: ");
                    doctor.setFirstName(new Scanner(System.in).nextLine());
                    System.out.println("Введите фамилию доктора: ");
                    doctor.setLastName(new Scanner(System.in).nextLine());
                    System.out.println("Введите гендер: F или M ");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            doctor.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                            isTrue = true;
                        } catch (IllegalArgumentException e) {
                            System.err.println("такого значения нет, введите снова");
                        }
                    }
                    System.out.println("Введите опыт работы: ");
                    doctor.setExperienceYear(new Scanner(System.in).nextInt());
                    System.out.println("Введите ID доктора которого нужно обновить: ");
                    System.out.println(doctorService.updateById(new Scanner(System.in).nextLong(), doctor));
                    break;
                }
                case "15": {
                    System.out.println("Введите ID доктора: ");
                    System.out.println(doctorService.findDoctorById(new Scanner(System.in).nextLong()));
                    break;
                }
                case "16": {





                }
                case "17": {
                    System.out.println("Введите ID больницы: ");
                    System.out.println(doctorService.getAllDoctorsByHospitalId(new Scanner(System.in).nextLong()));
                    break;
                }
                case "18": {
                    System.out.println("Введите ID отделения: ");
                    System.out.println(doctorService.getAllDoctorsByDepartmentId(new Scanner(System.in).nextLong()));
                    break;
                }
                case "19": {
                    Patient patient = new Patient();
                    System.out.println("Введите имя пациента: ");
                    patient.setFirstName(new Scanner(System.in).nextLine());
                    System.out.println("Введите фамилию пациента: ");
                    patient.setLastName(new Scanner(System.in).nextLine());
                    System.out.println("Введите возраст: ");
                    patient.setAge(new Scanner(System.in).nextInt());
                    System.out.println("Введите гендер: F или M");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                            isTrue = true;
                        } catch (IllegalArgumentException e) {
                            System.err.println("такого значения нет, введите снова");
                        }
                    }
                    patient.setId(GeneratedId.genPatientId());
                    System.out.println("личный ID: " + patient.getId());
                    System.out.println("Введите ID больницы в который нужно добавить пациента: ");
                    System.out.println(patientService.add(new Scanner(System.in).nextLong(), patient));
                    break;
                }
                case "20": {
                    System.out.println("Введите ID пациента: ");
                    patientService.removeById(new Scanner(System.in).nextLong());
                    break;
                }
                case "21": {
                    Patient patient = new Patient();
                    System.out.println("Введите новое имя пациента: ");
                    patient.setFirstName(new Scanner(System.in).nextLine());
                    System.out.println("Введите фамилию пациента: ");
                    patient.setLastName(new Scanner(System.in).nextLine());
                    System.out.println("Введите возраст пациента: ");
                    patient.setAge(new Scanner(System.in).nextInt());
                    System.out.println("Введите гендер: M или F ");
                    boolean isTrue = false;
                    while (!isTrue) {
                        try {
                            patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                            isTrue = true;
                        } catch (IllegalArgumentException e) {
                            System.err.println("такого значения нет, введите снова");
                        }
                    }
                    System.out.println("Введите ID пациента которого нужно обновить: ");
                    System.out.println(patientService.updateById(new Scanner(System.in).nextLong(), patient));
                    break;
                }
                case "22": {
                    System.out.println("Введите ID больницы в котроую нужно добавить в пациентов: ");
                    Long idHospital = new Scanner(System.in).nextLong();
                    List<Patient> patients = new ArrayList<>();
                    boolean isTrue = false;
                    do {
                        Patient patient = new Patient();
                        System.out.println("Введите имя пациента: ");
                        patient.setFirstName(new Scanner(System.in).nextLine());
                        System.out.println("Введите фамилию пациента: ");
                        patient.setLastName(new Scanner(System.in).nextLine());
                        System.out.println("Введите возраст: ");
                        patient.setAge(new Scanner(System.in).nextInt());
                        System.out.println("Введите гендер: F или M");
                        boolean isGenderTrue = false;
                        while (!isGenderTrue) {
                            try {
                                patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                isGenderTrue = true;
                            } catch (IllegalArgumentException e) {
                                System.err.println("такого значения нет, введите снова");
                            }
                        }
                        patient.setId(GeneratedId.genPatientId());
                        System.out.println("Ваш личный ID: " + patient.getId());
                        patients.add(patient);
                        System.out.println("успешно добавлен");
                        System.out.println("Нужно ли добавить еще одного пациента: 1. да \n" +
                                "                                       2. нет");
                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equals("1") || answer.equalsIgnoreCase("да")) {
                            isTrue = false;
                        } else if (answer.equals("2") || answer.equalsIgnoreCase("нет")) {
                            System.out.println(patientService.addPatientsToHospital(idHospital, patients));
                            isTrue = true;
                        }
                    } while (!isTrue);
                    break;
                }
                case "23": {
                    System.out.println("Введите ID пациента: ");
                    System.out.println(patientService.getPatientById(new Scanner(System.in).nextLong()));
                    break;
                }
                case "24": {
                    System.out.println("Введите возраст пациента: ");
                    System.out.println(patientService.getPatientByAge(new Scanner(System.in).nextInt()));
                    break;
                }
                case "25": {
                    System.out.println("Введите по какому направлению отсортировать по возрасту: 1. по возрастанию\n" +
                            "                                                         2. по убыванию");
                    System.out.println(patientService.sortPatientsByAge(new Scanner(System.in).nextLine()));
                    break;
                }
                case "0": {
                    exit = true;
                    break;
                }
                default: {
                    System.err.println("такрй опции нет");
                }
            }
        }
    }
}