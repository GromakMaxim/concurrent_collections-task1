package org.example;

/*consumer*/
public class Operator extends Thread {
    private final int WORKTIME = 500;//время обработки каждого звонка оператором
    private String name;
    private int numberReceivedCalls = 0;
    PhonecallsPool pool;

    public Operator(String name, PhonecallsPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        System.out.println(name + ": приступил к работе");
        boolean needToRepeat = true;
        while (needToRepeat) {
            if (!pool.isEmpty()) {
                while (needToRepeat) {
                    try {
                        Thread.sleep(WORKTIME);
                        IncomingCall call = pool.removeIncomingCallOrReturnNullIfEmpty();
                        if (call != null) {
                            System.out.println(name + " отработал звонок c номера: " + call.getPhoneNumber());
                            countCalls();
                        } else {
                            System.out.println(name + " завершил работу. Звонков отработано: " + numberReceivedCalls);
                            Thread.currentThread().interrupt();
                            needToRepeat = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    private void countCalls() {
        numberReceivedCalls++;
    }

    public int getNumberReceivedCalls() {
        return numberReceivedCalls;
    }
}

