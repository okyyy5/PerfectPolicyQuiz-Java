package PerfectPolicy;

public class ShutdownInterceptor extends Thread {

    // Graceful shutdown routine
    private IApp app;

    public ShutdownInterceptor(IApp app) {
        this.app = app;
    }

    public void run() {
        System.out.println("Call the shutdown routine");
        app.shutDown();
    }
}
