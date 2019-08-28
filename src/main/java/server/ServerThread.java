package server;

public class ServerThread extends Thread {
  private Runnable runnable;

  public ServerThread(Runnable runnable) {
    this.runnable = runnable;
  }

    private volatile boolean flag = true;

    public void stopRunning() {
      flag = false;
    }

//    @Override
//    public void run() {
//      while (flag)
//      {
//        runnable.run();
////        System.out.println("I am running....");
//      }
//
//      System.out.println("Stopped Running....");
//    }
}

