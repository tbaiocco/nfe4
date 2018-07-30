package com.master.util.bd;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ObjectPool {

    private long expirationTime;
    private long lastCheckOut;
    private String nome;
    private static Hashtable locked;
    private static Hashtable unlocked;
    private static Timer timer;

    ObjectPool() {

        locked = new Hashtable();
        unlocked = new Hashtable();
        
        estatistica("*** Novo pool!");

        lastCheckOut = System.currentTimeMillis();
        
        // 30 seconds
        expirationTime = (1000 * 30); 
        // delay for 5 sec.
        int delay = 5 * 1000;
        // repeat every sec.
        int period = 30 * 60 * 1000; // 30 minutos
        //int period = 40 * 1000; // 40 segundos para teste

        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    cleanUp();
                }
            }, delay, period);
        }
    }

    abstract Object create() throws Exception;

    abstract boolean validate(Object o);

    abstract void expire(Object o);

    public void estatistica(String sMsg) {
        //if (locked != null && unlocked != null)
            //// System.out.println("Msg:" + sMsg + " " + System.currentTimeMillis() + " Unlocked: " + unlocked.size() + " Locked: " + locked.size());
    }

    synchronized Object checkOut() throws Exception {
        long now = System.currentTimeMillis();
        lastCheckOut = now;
        Object o;

        if (unlocked.size() > 0) {
            Enumeration e = unlocked.keys();

            while (e.hasMoreElements()) {
                o = e.nextElement();

                if (validate(o)) {
                    unlocked.remove(o);
                    locked.put(o, new Long(now));
                    estatistica("checkout");
                    return (o);
                } else {
                    unlocked.remove(o);
                    expire(o);
                    o = null;
                }
            }
        }

        o = create();

        locked.put(o, new Long(now));

        estatistica("checkout");
        return (o);
    }

    synchronized void checkIn(Object o) {
        if (o != null) {
            locked.remove(o);
            unlocked.put(o, new Long(System.currentTimeMillis()));
        }
        estatistica("checkin");
    }

    synchronized void cleanUp() {
        Object o;
        long now = System.currentTimeMillis();
        Enumeration e = unlocked.keys();

        while (e.hasMoreElements()) {
            o = e.nextElement();

            if ((now - ((Long) unlocked.get(o)).longValue()) > expirationTime) {
                unlocked.remove(o);
                expire(o);
                o = null;
            } //else // System.out.println("*** Não Expirou:"+o.toString());
        }
        estatistica("CleanUp: ");
        System.gc();
    }
}