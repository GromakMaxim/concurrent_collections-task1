package org.example;

/*Automatic Telephone Exchange
 *generates 60calls per second
 * producer
 * */
public class ATX extends Thread {
    private final int SLEEP_TIME = 1000;
    private final int NUMBER_OF_NEW_CALLS = 60;
    private int NUMBER_ITERATIONS = 5;
    PhonecallsPool pool;

    public ATX(PhonecallsPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        System.out.println("АТС: приступил к работе");
        while (NUMBER_ITERATIONS != 0) {
            try {
                //ждем, потом добавляем 60 звонков
                Thread.sleep(SLEEP_TIME);
                for (int i = 0; i < NUMBER_OF_NEW_CALLS; i++) {
                    IncomingCall call = new IncomingCall();
                    pool.addIncomeCall(call);
                }
                System.out.println("АТС: добавила " + NUMBER_OF_NEW_CALLS + " новых звонков!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NUMBER_ITERATIONS--;
        }
    }
}
