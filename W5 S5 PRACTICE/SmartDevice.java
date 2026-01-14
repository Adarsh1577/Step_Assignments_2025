import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SmartDevice {
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    private String deviceName;
    private boolean isEnabled;

    private final LocalDateTime startupTime;
    private int hashedEncryptionKey;
    private int hashedAdminPassword;

    public SmartDevice(String deviceId, LocalDateTime manufacturingDate, String deviceName) {
        this.deviceId = deviceId;
        this.manufacturingDate = manufacturingDate;
        this.serialNumber = UUID.randomUUID().toString();
        this.deviceName = deviceName;
        this.isEnabled = true;
        this.startupTime = LocalDateTime.now();
    }

    public String getDeviceId() { return deviceId; }
    public LocalDateTime getManufacturingDate() { return manufacturingDate; }
    public String getSerialNumber() { return serialNumber; }
    public long getUptime() { return ChronoUnit.SECONDS.between(startupTime, LocalDateTime.now()); }
    public int getDeviceAge() { return LocalDateTime.now().getYear() - manufacturingDate.getYear(); }

    public void setEncryptionKey(String key) {
        if (key != null && key.length() >= 8) {
            this.hashedEncryptionKey = key.hashCode();
            System.out.println("Encryption key set successfully.");
        } else {
            System.out.println("Invalid encryption key.");
        }
    }
    public void setAdminPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.hashedAdminPassword = password.hashCode();
            System.out.println("Admin password set successfully.");
        } else {
            System.out.println("Invalid admin password.");
        }
    }
    public boolean validateEncryptionKey(String key) { return key.hashCode() == hashedEncryptionKey; }
    public boolean validateAdminPassword(String password) { return password.hashCode() == hashedAdminPassword; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public boolean isEnabled() { return isEnabled; }
    public void setEnabled(boolean enabled) { this.isEnabled = enabled; }

    public Map<String,String> getPropertyInfo() {
        Map<String,String> info = new LinkedHashMap<>();
        info.put("deviceId","Read-Only");
        info.put("manufacturingDate","Read-Only");
        info.put("serialNumber","Read-Only");
        info.put("uptime","Computed Read-Only");
        info.put("deviceAge","Computed Read-Only");
        info.put("encryptionKey","Write-Only");
        info.put("adminPassword","Write-Only");
        info.put("deviceName","Read-Write");
        info.put("isEnabled","Read-Write");
        return info;
    }

    public void resetDevice() {
        hashedEncryptionKey = 0;
        hashedAdminPassword = 0;
        deviceName = "Unnamed Device";
        isEnabled = false;
        System.out.println("Device reset completed.");
    }

    public static void main(String[] args) {
        SmartDevice d1 = new SmartDevice("DEV001", LocalDateTime.of(2020,5,10,0,0), "Router");
        SmartDevice d2 = new SmartDevice("DEV002", LocalDateTime.of(2023,1,1,0,0), "Smart Bulb");

        System.out.println("Device1 ID: " + d1.getDeviceId());
        System.out.println("Manufactured: " + d1.getManufacturingDate());
        System.out.println("Serial: " + d1.getSerialNumber());
        System.out.println("Uptime: " + d1.getUptime() + " seconds");
        System.out.println("Device Age: " + d1.getDeviceAge() + " years");

        d1.setEncryptionKey("secureKey123");
        d1.setAdminPassword("admin@123");
        System.out.println("Encryption key valid? " + d1.validateEncryptionKey("secureKey123"));
        System.out.println("Admin password valid? " + d1.validateAdminPassword("admin@123"));

        System.out.println("Device Name: " + d1.getDeviceName());
        d1.setDeviceName("Home Router");
        System.out.println("Updated Device Name: " + d1.getDeviceName());
        System.out.println("Enabled: " + d1.isEnabled());
        d1.setEnabled(false);
        System.out.println("Enabled after update: " + d1.isEnabled());

        System.out.println("\nProperty Info:");
        d1.getPropertyInfo().forEach((k,v)-> System.out.println(k+" -> "+v));

        d1.resetDevice();
        System.out.println("Device Name after reset: " + d1.getDeviceName());
        System.out.println("Enabled after reset: " + d1.isEnabled());

        System.out.println("\nDevice2 Independent Test:");
        System.out.println("Device2 ID: " + d2.getDeviceId());
        System.out.println("Device2 Name: " + d2.getDeviceName());
    }
}
