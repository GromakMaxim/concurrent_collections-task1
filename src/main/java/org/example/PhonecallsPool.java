package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PhonecallsPool {
//    private ArrayBlockingQueue<IncomingCall> callPool = new ArrayBlockingQueue<>(10_000, true);//37035мсек, нужно capacity указывать=(
    private ConcurrentLinkedQueue<IncomingCall> callPool = new ConcurrentLinkedQueue<>();
    private boolean isAccess = false;

    //добавить новый входящий звонок
    public void addIncomeCall(IncomingCall call) {
        callPool.add(call);
    }

    //оператор отвечает на звонок
    public IncomingCall removeIncomingCallOrReturnNullIfEmpty() {
        return callPool.poll();
    }

    public boolean isEmpty() {
        if (callPool.peek() == null) {
            return true;
        }
        return false;
    }

}
