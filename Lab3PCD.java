public class Lab3PCD {
    static int size = 100;
    static int counter = 0;
    static int[] b = new int[size];
    static int[] a;

    static Straight first = new Straight();
    static Reverse second = new Reverse();
    static ReverseInterval third = new ReverseInterval();
    static StraightInterval fourth = new StraightInterval();

    static int pairone = 0;
    static int pairtwo = 0;

    static class Straight extends Thread {
        int result = 0;
        @Override
        public void run(){
            System.out.println("Starting Thread 1");
            for (int i = 0; i< counter; i+=4) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (a[i] >= 100 && a[i] <= 200){
                    if ((i + 4) >= counter) {
                    } else {
                        pairone = a[i] - a[i + 1];
                        pairtwo = a[i + 2] - a[i + 3];
                        result = pairone + pairtwo;
                        System.out.println("Current value for Thread 1 is: " + a[i] + "-" + a[i-1] + "="
                                +pairone + " + " + a[i-2] + "-" + a[i-3] + "=" + pairtwo + " = " + result);
                    }
                }
            }
            while(fourth.isAlive()){
                Thread.yield();
            }
            String prenume = "1: Agachi";
            for(int i = 0 ; i< prenume.length(); i++){
                System.out.print(prenume.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    static class Reverse extends Thread {
        int result = 0;
        @Override
        public void run(){
            System.out.println("Starting Thread 2");
            for (int i = counter-1; i>= 0; i-=4){
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (a[i] <= 110 && a[i] >= 10) {
                    if ((i - 4) <= 0) {
                    }
                    else {
                        pairone = a[i] - a[i-1];
                        pairtwo = a[i-2] - a[i-3];
                        result = pairone + pairtwo;
                        System.out.println ("Current value for Thread 2 is: " + a[i] + "-" + a[i-1] + "=" +pairone + " " +
                                "+ " + a[i-2] + "-" + a[i-3] + "=" + pairtwo + " = " + result);
                    }
                }

            }
            String nume = "2: Daniel";
            for(int i = 0 ; i< nume.length(); i++){
                System.out.print(nume.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public static class StraightInterval extends  Thread {
        @Override
        public void run(){
            System.out.println("Starting Thread 4 ");
            for (int i = 700; i>= 300; i--){
                System.out.print(i + " ");
            }
            System.out.println(" ");
            while(second.isAlive()){
                Thread.yield();
            }
            String grupa = "4: CR-201";
            for(int i = 0 ; i< grupa.length(); i++){
                System.out.print(grupa.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }

    }

    public static class ReverseInterval extends Thread {
        @Override
        public void run(){
            System.out.println("Starting Thread 3 ");
            for (int i = 100; i<= 500; i++){
                System.out.print(i + " ");
            }

            System.out.println(" ");
            while(first.isAlive()){
                Thread.yield();
            }
            String obiectul = "3: Programarea Concurenta si Distribuita";
            for(int i = 0 ; i< obiectul.length(); i++){
                System.out.print(obiectul.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Printing Array: ");
        for (int i = 0; i< 100; i++) {
            b[i] = (int) Math.round((Math.random() * 100) + 15);
            System.out.print (b[i] + " ");
            if (i == 50){
                System.out.println (b[i] + " ");
            }
            if (i == 99)
                System.out.println();
            if (b[i]%2==0){
                counter++;
            }
        }
        a = new int[counter+1];
        int k = 0;
        for (int i = 0; i< 100; i++){
            if (b[i]%2==0){
                a[k] = b[i];
                k++;
            }
        }

        first.start();
        second.start();
        third.start();
        fourth.start();
        try{
            first.join();
            second.join();
            third.join();
            fourth.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}