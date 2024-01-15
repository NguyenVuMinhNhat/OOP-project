public class Timer {

    private long startTime;
    private long duration;
    private Runnable tenMinAction;

    public Timer() {
        this.duration = duration * 60 * 1000; // Convert minutes to milliseconds
        this.startTime = System.currentTimeMillis();
    }

    public void setTenMinAction(Runnable action) {
        this.tenMinAction = action;
    }
    
    public long getRemainingTime() {
        long remainingTime = duration - (System.currentTimeMillis() - startTime);
        return remainingTime > 0 ? remainingTime / 1000 : 0;
    }

    public void start() {
        new Thread(() -> {
            long remainingTime = duration;
            while (remainingTime > 0) {
                try {
                    Thread.sleep(1000);
                    remainingTime = duration - (System.currentTimeMillis() - startTime);
                    int minutes = (int) (remainingTime / (60 * 1000));
                    int seconds = (int) ((remainingTime / 1000) % 60);
                    int elapsedMinutes = (int) ((System.currentTimeMillis() - startTime) / (60 * 1000));
                    System.out.println("Time remaining: " + minutes + " minutes " + seconds + " seconds");
                    System.out.println("Time elapsed: " + elapsedMinutes + " minutes");

                    // Check if 10 minutes have passed
                    if (elapsedMinutes == 1) {
                        if (tenMinAction != null) {
                            tenMinAction.run();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Time is up!");
        }).start();
    }

    public long getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

   
}
