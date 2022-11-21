import java.util.*;
class Producer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Producer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }
    public void run() {
        for (int i = 1; i <= 11; i++) {
            int a = (int) (Math.random() * 50 + Math.random() * 50);
            if(a % 2 == 1)
            {
                a++;
            }
            int b = (int) (Math.random() * 50 + Math.random() * 50);
            if(b % 2 == 1)
            {
                b++;
            }

            try {
                cubbyhole.put(a, b, this.number);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
class Consumer extends Thread {
    private CubbyHole cubbyhole;
    private int number;
    public Consumer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }
    public void run() {
        int value;
        for (int i = 1; i <= 11; i++) {

            try {
                value = cubbyhole.get();
                System.out.println("Consumatorul #" + this.number + " a consumat: " + value);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
        System.out.println("Consumatorul #" + this.number + " a consumat elementele necesare. ");
    }
}
class Lab4 {
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole();
        Producer p1 = new Producer(c, 1);
        Producer p2 = new Producer(c, 2);
        Consumer c1 = new Consumer(c, 1);
        Consumer c2 = new Consumer(c, 2);
        Consumer c3 = new Consumer(c, 3);
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();

        while(c1.isAlive() ||c2.isAlive() || c3.isAlive());
        p1.stop();
        p2.stop();

    }
}
class CubbyHole {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 8;
    private boolean available = false;
    public int get() throws InterruptedException
    {
        synchronized (this)
        {
            if(list.size() == 0)
            {
                System.out.println("Depozitul este gol!");
                wait();
            }

            int val = list.removeFirst();
            Thread.sleep(100);
            notifyAll();
            return val;
        }
    }
    public void put(int number1 , int number2, int th) throws InterruptedException
    {
        synchronized (this)
        {
            if (list.size() == capacity)
            {
                System.out.println("Depozitul este plin!");
                wait();
            } else
            {
                if(list.size() <= capacity-2)
                {
                    System.out.println("Producatorul #" + th + " a produs: " + number1 + " si "+ number2);
                    list.add(number1);
                    list.add(number2);
                } else if(list.size() == capacity-1)
                {
                    System.out.println("Producatorul #" + th + " a produs: " + number1);
                    list.add(number1);
                }

                Thread.sleep(100);
                notifyAll();
            }
        }
    }
}