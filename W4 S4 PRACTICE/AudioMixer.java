public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    // No-argument constructor using this() chaining
    public AudioMixer() {
        this("StandardMix-8", 8, false);
    }

    // Two-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);
    }

    // Three-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
    }

    // Main constructor - all parameters
    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;

        // Initialize connectedDevices array based on number of channels
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;

        // Print constructor execution message
        System.out.println("AudioMixer Constructor Executed → Model: " + mixerModel);
    }

    // Method to connect device
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName + " to " + mixerModel);
        } else {
            System.out.println("All channels occupied on " + mixerModel + "!");
        }
    }

    // Method to display mixer status
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");

        // Create mixer using no-argument constructor
        AudioMixer mixer1 = new AudioMixer();

        // Create mixer using two-parameter constructor
        AudioMixer mixer2 = new AudioMixer("CompactMix-4", 4);

        // Create mixer using three-parameter constructor
        AudioMixer mixer3 = new AudioMixer("BlueMix-12", 12, true);

        // Create mixer using full constructor
        AudioMixer mixer4 = new AudioMixer("ProMix-16", 16, true, 150.0);

        // Connect different devices
        mixer1.connectDevice("Microphone");
        mixer1.connectDevice("Keyboard");

        mixer2.connectDevice("Electric Guitar");

        mixer3.connectDevice("Drum Machine");
        mixer3.connectDevice("Bass Guitar");
        mixer3.connectDevice("Laptop");

        mixer4.connectDevice("DJ Controller");
        mixer4.connectDevice("Synthesizer");
        mixer4.connectDevice("Mixer Board");
        mixer4.connectDevice("Violin Mic");

        // Display status of all mixers
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        System.out.println("\n=== Constructor Chaining Execution Order ===");
        System.out.println("No-arg → calls 3-param → calls 4-param");
        System.out.println("2-param → calls 3-param → calls 4-param");
        System.out.println("3-param → calls 4-param");
        System.out.println("Full constructor initializes everything directly.");
    }
}
