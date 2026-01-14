import java.util.*;

// ================= PATIENT CLASS =================
class Patient {
    String patientId;
    String patientName;
    int age;
    String gender;
    String contactInfo;
    List<String> medicalHistory;
    List<String> currentTreatments;

    // Static variable for patient count
    static int totalPatients = 0;

    public Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>();
        this.currentTreatments = new ArrayList<>();
        totalPatients++;
    }

    public void updateTreatment(String treatment) {
        currentTreatments.add(treatment);
        medicalHistory.add("Treatment: " + treatment);
    }

    public void dischargePatient() {
        System.out.println("🏥 Patient " + patientName + " has been discharged.");
        currentTreatments.clear();
    }

    public void displayInfo() {
        System.out.println("Patient ID: " + patientId + " | Name: " + patientName +
                " | Age: " + age + " | Gender: " + gender);
        System.out.println("Contact: " + contactInfo);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Current Treatments: " + currentTreatments);
    }
}

// ================= DOCTOR CLASS =================
class Doctor {
    String doctorId;
    String doctorName;
    String specialization;
    List<String> availableSlots;
    int patientsHandled;
    double consultationFee;

    public Doctor(String doctorId, String doctorName, String specialization,
                  String[] slots, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.availableSlots = new ArrayList<>(Arrays.asList(slots));
        this.patientsHandled = 0;
        this.consultationFee = consultationFee;
    }

    public void assignPatient() {
        patientsHandled++;
    }

    public void displayInfo() {
        System.out.println("Doctor ID: " + doctorId + " | Name: " + doctorName +
                " | Specialization: " + specialization + " | Fee: " + consultationFee);
        System.out.println("Available Slots: " + availableSlots);
        System.out.println("Patients Handled: " + patientsHandled);
    }
}

// ================= APPOINTMENT CLASS =================
class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate;
    String appointmentTime;
    String status;
    String type; // Consultation, Follow-up, Emergency
    double billAmount;

    // Static variables
    static int totalAppointments = 0;
    static double totalRevenue = 0;
    static String hospitalName = "CityCare Hospital";

    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                       String appointmentDate, String appointmentTime, String type) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.type = type;
        this.status = "Scheduled";
        totalAppointments++;
    }

    public void scheduleAppointment() {
        if (doctor.availableSlots.contains(appointmentTime)) {
            doctor.availableSlots.remove(appointmentTime);
            doctor.assignPatient();
            System.out.println("✅ Appointment Scheduled with Dr. " + doctor.doctorName +
                    " for Patient " + patient.patientName + " at " + appointmentTime);
        } else {
            System.out.println("⚠️ Slot not available!");
        }
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        doctor.availableSlots.add(appointmentTime);
        System.out.println("❌ Appointment " + appointmentId + " has been cancelled.");
    }

    public void generateBill() {
        double baseFee = doctor.consultationFee;
        switch (type) {
            case "Consultation": billAmount = baseFee; break;
            case "Follow-up": billAmount = baseFee * 0.5; break;
            case "Emergency": billAmount = baseFee * 2; break;
        }
        totalRevenue += billAmount;
        System.out.println("💰 Bill for Patient " + patient.patientName + ": $" + billAmount);
    }

    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentId + " | Patient: " + patient.patientName +
                " | Doctor: " + doctor.doctorName + " | Date: " + appointmentDate +
                " | Time: " + appointmentTime + " | Type: " + type + " | Status: " + status);
    }

    // Static reports
    public static void generateHospitalReport() {
        System.out.println("\n===== 🏥 " + hospitalName + " REPORT =====");
        System.out.println("Total Patients Registered: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    public static void getDoctorUtilization(Doctor doctor) {
        System.out.println("Dr. " + doctor.doctorName + " has handled " + doctor.patientsHandled + " patients.");
    }

    public static void getPatientStatistics(Patient patient) {
        System.out.println("📊 Patient: " + patient.patientName + " | History Records: " +
                patient.medicalHistory.size() + " | Current Treatments: " + patient.currentTreatments.size());
    }
}

// ================= MAIN CLASS =================
public class HospitalManagementSystem {
    public static void main(String[] args) {
        // Create Patients
        Patient p1 = new Patient("P001", "Amit Sharma", 30, "Male", "9876543210");
        Patient p2 = new Patient("P002", "Neha Gupta", 25, "Female", "9123456780");

        // Create Doctors
        Doctor d1 = new Doctor("D001", "Dr. Verma", "Cardiologist",
                new String[]{"10AM", "11AM", "12PM"}, 1000);
        Doctor d2 = new Doctor("D002", "Dr. Mehta", "Dermatologist",
                new String[]{"2PM", "3PM"}, 800);

        // Appointments
        Appointment a1 = new Appointment("A101", p1, d1, "2025-09-05", "10AM", "Consultation");
        a1.scheduleAppointment();
        a1.generateBill();
        a1.displayAppointment();

        Appointment a2 = new Appointment("A102", p2, d2, "2025-09-06", "2PM", "Emergency");
        a2.scheduleAppointment();
        a2.generateBill();
        a2.displayAppointment();

        // Treatments
        p1.updateTreatment("Blood Test");
        p2.updateTreatment("Skin Allergy Treatment");

        // Discharge
        p2.dischargePatient();

        // Reports
        Appointment.generateHospitalReport();
        Appointment.getDoctorUtilization(d1);
        Appointment.getPatientStatistics(p1);
    }
}
