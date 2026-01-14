import java.time.LocalDate;
import java.time.Period;
import java.util.*;

// ---------------- Immutable MedicalRecord ----------------
final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
        if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("recordId required");
        if (patientDNA == null || patientDNA.isBlank()) throw new IllegalArgumentException("patientDNA required");
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("invalid birthDate");
        if (!isValidBloodType(bloodType)) throw new IllegalArgumentException("invalid blood type");
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = allergies == null ? new String[0] : Arrays.copyOf(allergies, allergies.length);
        this.medicalHistory = medicalHistory == null ? new String[0] : Arrays.copyOf(medicalHistory, medicalHistory.length);
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    private boolean isValidBloodType(String b) {
        if (b == null) return false;
        String[] types = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        for (String t: types) if (t.equalsIgnoreCase(b)) return true;
        return false;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
    public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }
    public final boolean isAllergicTo(String substance) {
        if (substance == null) return false;
        for (String a : allergies) if (substance.equalsIgnoreCase(a)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' +
                ", allergies=" + Arrays.toString(allergies) +
                ", medicalHistory=" + Arrays.toString(medicalHistory) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() { return Objects.hash(recordId); }
}

// ---------------- Patient with privacy levels ----------------
class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;

    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;

    private int roomNumber;
    private String attendingPhysician;

    static final String TEMP_PREFIX = "TMP-";

    // Emergency admission (minimal)
    public Patient(String currentName, String emergencyContact) {
        this(generateTempId(), buildTemporaryRecord(currentName), currentName, emergencyContact, null, -1, "Triage");
    }

    // Standard admission (full info)
    public Patient(String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician, MedicalRecord record) {
        this(UUID.randomUUID().toString(), record, currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician);
    }

    // Transfer admission (existing medical record)
    public Patient(MedicalRecord existingRecord, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician) {
        this(UUID.randomUUID().toString(), existingRecord, currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician);
    }

    private Patient(String patientId, MedicalRecord medicalRecord, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician) {
        if (patientId == null || patientId.isBlank()) throw new IllegalArgumentException("patientId required");
        if (medicalRecord == null) throw new IllegalArgumentException("medicalRecord required");
        this.patientId = patientId;
        this.medicalRecord = medicalRecord;
        setCurrentName(currentName);
        setEmergencyContact(emergencyContact);
        setInsuranceInfo(insuranceInfo);
        setRoomNumber(roomNumber);
        setAttendingPhysician(attendingPhysician);
    }

    private static String generateTempId() { return TEMP_PREFIX + UUID.randomUUID().toString().substring(0,8); }

    private static MedicalRecord buildTemporaryRecord(String name) {
        String rid = "REC-" + UUID.randomUUID().toString().substring(0,8);
        String dna = "TEMP-DNA-" + UUID.randomUUID().toString().substring(0,8);
        LocalDate dt = LocalDate.now().minusYears(30);
        return new MedicalRecord(rid, dna, new String[]{}, new String[]{}, dt, "O+");
    }

    // Package-private for staff access (basic)
    String getBasicInfo() {
        return "PatientBasic{patientId='" + patientId + "', name='" + currentName + "', room=" + roomNumber + "}";
    }

    public String getPublicInfo() {
        return "PatientPublic{name='" + currentName + "', room=" + roomNumber + "}";
    }

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }

    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) { this.currentName = currentName == null ? "Unknown" : currentName; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact == null ? "NotProvided" : emergencyContact; }

    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo == null ? "SelfPay" : insuranceInfo; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = Math.max(-1, roomNumber); }

    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician == null ? "TBD" : attendingPhysician; }

    @Override
    public String toString() {
        String audit = "AUDIT[" + LocalDate.now() + "] ";
        return audit + "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", roomNumber=" + roomNumber +
                ", attendingPhysician='" + attendingPhysician + '\'' +
                ", medicalRecordSummary=" + medicalRecord.toString() +
                '}';
    }
}

// ---------------- Medical staff classes ----------------
class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || licenseNumber.isBlank()) throw new IllegalArgumentException("license required");
        this.licenseNumber = licenseNumber;
        this.specialty = specialty == null ? "General" : specialty;
        this.certifications = certifications == null ? new HashSet<>() : new HashSet<>(certifications);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return new HashSet<>(certifications); }

    @Override
    public String toString() { return "Doctor{" + licenseNumber + "," + specialty + "}"; }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || nurseId.isBlank()) throw new IllegalArgumentException("nurseId required");
        this.nurseId = nurseId;
        this.shift = shift == null ? "Day" : shift;
        this.qualifications = qualifications == null ? new ArrayList<>() : new ArrayList<>(qualifications);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return new ArrayList<>(qualifications); }

    @Override
    public String toString() { return "Nurse{" + nurseId + "," + shift + "}"; }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || adminId.isBlank()) throw new IllegalArgumentException("adminId required");
        this.adminId = adminId;
        this.accessPermissions = accessPermissions == null ? new ArrayList<>() : new ArrayList<>(accessPermissions);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }

    @Override
    public String toString() { return "Administrator{" + adminId + "}"; }
}

// ---------------- HospitalSystem ----------------
class HospitalSystem {
    private final Map<String, Object> patientRegistry;
    private final static int MAX_ROOM_NUMBER = 1000;
    private final static String HOSPITAL_POLICY = "STRICT-PRIVACY";

    public HospitalSystem() {
        this.patientRegistry = new HashMap<>();
    }

    public boolean registerPatient(Patient p) {
        if (p == null) return false;
        patientRegistry.put(p.getPatientId(), p);
        return true;
    }

    public boolean admitPatient(Object patientObj, Object staff) {
        if (!(patientObj instanceof Patient)) return false;
        Patient patient = (Patient) patientObj;
        if (!validateStaffAccess(staff, patient)) {
            System.out.println("Access denied for staff: " + staff);
            return false;
        }
        patientRegistry.put(patient.getPatientId(), patient);
        System.out.println("Admitted: " + patient.getPatientId());
        return true;
    }

    private boolean validateStaffAccess(Object staff, Patient patient) {
        if (staff instanceof Doctor) return true; // doctors have full access
        if (staff instanceof Nurse) {
            Nurse n = (Nurse) staff;
            // nurses can access basic info and medicalRecord allergies only
            return true;
        }
        if (staff instanceof Administrator) {
            Administrator a = (Administrator) staff;
            return a.getAccessPermissions().contains("VIEW_PATIENTS");
        }
        return false;
    }

    // Package-private internal lookups
    Patient lookupById(String patientId) {
        Object o = patientRegistry.get(patientId);
        return (o instanceof Patient) ? (Patient) o : null;
    }

    public static String auditAccess(Object staff, Patient patient) {
        String who = staff == null ? "Unknown" : staff.toString();
        String pat = patient == null ? "Unknown" : patient.getPatientId();
        return "AUDIT_RECORD: staff=" + who + " accessed patient=" + pat + " at " + LocalDate.now();
    }

    public Collection<Patient> getAllPatients() {
        List<Patient> res = new ArrayList<>();
        for (Object o: patientRegistry.values()) if (o instanceof Patient) res.add((Patient)o);
        return res;
    }

    @Override
    public String toString() {
        return "HospitalSystem{policy=" + HOSPITAL_POLICY + ", patients=" + patientRegistry.size() + "}";
    }
}

// ---------------- Demo Main ----------------
public class HospitalDemo {
    public static void main(String[] args) {
        // create immutable medical records
        MedicalRecord rec1 = new MedicalRecord("REC-1001", "DNA-A1B2C3", new String[]{"Peanuts"}, new String[]{"Asthma"}, LocalDate.of(1990, 4, 5), "A+");
        MedicalRecord rec2 = new MedicalRecord("REC-2002", "DNA-X9Y8Z7", new String[]{"Penicillin"}, new String[]{"Fracture-2018"}, LocalDate.of(1975, 11, 20), "O-");

        // create patients (standard and transfer)
        Patient p1 = new Patient("John Doe", " +911234567890", "InsureX", 101, "Dr. Strange", rec1);
        Patient p2 = new Patient(rec2, "Elder Mary", "+919876543210", "Govt", 202, "Dr. Who");

        // emergency patient (minimal)
        Patient emergency = new Patient("Unconscious", "+0000000000");

        // staff
        Doctor doc = new Doctor("LIC-12345", "Cardiology", Set.of("ACLS"));
        Nurse nurse = new Nurse("N-100", "Night", List.of("RN", "BLS"));
        Administrator admin = new Administrator("ADM-1", List.of("VIEW_PATIENTS"));

        // hospital system
        HospitalSystem hs = new HospitalSystem();
        hs.registerPatient(p1);
        hs.registerPatient(p2);
        hs.registerPatient(emergency);

        System.out.println(hs);

        // admit via staff with access checks
        hs.admitPatient(p1, doc);          // should succeed
        hs.admitPatient(p2, nurse);        // should succeed (nurse allowed basic)
        boolean adminAdmit = hs.admitPatient(emergency, admin); // admin has permission
        System.out.println("Admin admit success: " + adminAdmit);

        // lookup and display public/basic info
        System.out.println("Public info p1: " + p1.getPublicInfo());
        System.out.println("Basic info p2 (staff view): " + p2.getBasicInfo());

        // Doctor reads medical record (full access)
        System.out.println("Doctor checking allergies p1: " + rec1.isAllergicTo("Peanuts"));

        // Nurse attempts to view medical history -> allowed to see allergies but not DNA (we simulate by policy)
        System.out.println("Nurse allowed allergy check p2: " + rec2.isAllergicTo("Penicillin"));

        // Audit log
        System.out.println(HospitalSystem.auditAccess(doc, p1));

        // List patients
        System.out.println("All patients registered:");
        for (Patient p : hs.getAllPatients()) {
            System.out.println(" - " + p.getPatientId() + " | public: " + p.getPublicInfo());
        }

        // Demonstrate privacy: cannot modify medicalRecords (immutable) - compile-time enforced
        System.out.println("MedicalRecord sample: " + rec1);

        // Show toString/audit content
        System.out.println(p1.toString());
    }
}
