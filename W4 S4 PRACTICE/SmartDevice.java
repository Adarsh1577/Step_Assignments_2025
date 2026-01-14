public class SmartDevice {
    private String deviceName;
    private String location;
    private boolean isOnline;
    private double powerConsumption;
    private String[] connectedDevices;
    private int connectionCount;

    // Constructor with parameter names matching field names
    public SmartDevice(String deviceName, String location,
                       boolean isOnline, double powerConsumption) {
        // Use this keyword to distinguish between parameters and fields
        this.deviceName = deviceName;
        this.location = location;
        this.isOnline = isOnline;
        this.powerConsumption = powerConsumption;

        // Initialize connectedDevices array (size 5)
        this.connectedDevices = new String[5];
        this.connectionCount = 0;
    }

    // Method using this for parameter disambiguation
    public void updateLocation(String location) {
        this.location = location; // disambiguation
        System.out.println(this.deviceName + " moved to " + this.location);
    }

    public void updatePowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption; // disambiguation
        System.out.println("Power consumption updated for " + this.deviceName);
    }

    // Method returning this for chaining
    public SmartDevice setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    public SmartDevice connectToDevice(String deviceName) {
        if (this.connectionCount < this.connectedDevices.length) {
            this.connectedDevices[this.connectionCount] = deviceName;
            this.connectionCount++;
            System.out.println(this.deviceName + " connected to " + deviceName);
        } else {
            System.out.println(this.deviceName + " cannot connect more devices!");
        }
        return this; // Enable method chaining
    }

    public SmartDevice rename(String deviceName) {
        String oldName = this.deviceName;
        this.deviceName = deviceName;
        System.out.println("Device renamed from " + oldName + " to " + this.deviceName);
        return this;
    }

    public void displayDeviceInfo() {
        System.out.println("\n=== " + this.deviceName + " INFO ===");
        System.out.println("Location: " + this.location);
        System.out.println("Status: " + (this.isOnline ? "Online" : "Offline"));
        System.out.println("Power: " + this.powerConsumption + "W");
        System.out.println("Connections: " + this.connectionCount);
        for (int i = 0; i < this.connectionCount; i++) {
            System.out.println(" -> " + this.connectedDevices[i]);
        }
    }

    // Method that calls other methods using this
    public void performInitialSetup() {
        this.setOnline(true); // call another method using this
        System.out.println(this.deviceName + " initial setup completed");
    }

    public static void main(String[] args) {
        System.out.println("=== SMART HOME DEVICE NETWORK ===");

        // Create devices with parameter names matching field names
        SmartDevice device1 = new SmartDevice("SmartLight", "Living Room", false, 10.5);
        SmartDevice device2 = new SmartDevice("Thermostat", "Bedroom", true, 5.0);

        // Perform setup
        device1.performInitialSetup();
        device2.performInitialSetup();

        // Test method chaining
        device1.setOnline(true)
               .connectToDevice("Alexa")
               .rename("LivingRoom Light")
               .updateLocation("Hallway");

        device2.setOnline(false)
               .connectToDevice("Google Home")
               .rename("Bedroom Thermostat");

        // Update power
        device1.updatePowerConsumption(12.0);

        // Display info
        device1.displayDeviceInfo();
        device2.displayDeviceInfo();
    }
}
