import java.util.Scanner;

public class Main
{

    int	level=30;
    int	max_level=100;
    int target = 80;
    int	min_level=0;



    boolean	stopped=false;

    public int gr(int min) {
        return (int) ((Math.random() * (10 - min)) + min);
    }
    public int ran() {
        return (int) (Math.random() * 9);
    }
    public int cor() {
        return (int) (Math.random() * 5);
    }
    public int kl() {
        return (int) (Math.random() * 100);
    }

    public ListSklad[] ls = new ListSklad[10];
    class ListSklad {
        private int name;
        private int numbers;
        public ListSklad (int name, int numbers) {
            this.name = name;
            this.numbers = numbers;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }
    }


    public Main()
    {  ListSklad[] ls = new ListSklad[11];
        int i;
        int j;
        for (i=0; i<10; i++){

            ls[i]= new  ListSklad(i, kl()*3 );

            System.out.println("Товар "+ ls[i].name + " имеется в количестве " + ls[i].numbers);
        }
        Thread post=new Thread() {
            public void run() {
                int s = 0;
                while (!stopped) {
                    for (int i = 0; i < 10; i++) {
                        s = s + ls[i].getNumbers();

                    }

                        try {
                            if (s <= 10000) {
                                sleep(1000);
                                int t = ran();
                                int m = gr(t);
                                int g;
                                int n;


                                synchronized (ls) {
                                    for (int i = t; i < m; i++) {
                                        g = ran();
                                        n = ls[g].getNumbers();
                                        ls[g].setNumbers(n + kl());
                                   
                                    }
                                }
                            } else
                                System.out.println ("Склад заполнен");
                                sleep(10000);
                        }
                        catch (Exception pe) {
                            pe.printStackTrace();


                        }

                }
                }

            };

        Thread potr=new Thread()
        {	public void run() {
            while (!stopped) {
            try {
                sleep(1000);
                int t = ran();
                int m = gr(t);
                int g;
                int c;
                int n;
                synchronized (ls){
                    for (int i = t; i<m; i++){
                        g=ran();
                        c=kl();
                     
                        n= ls[g].getNumbers();
                        n=n-c;
                        if ( n<0){
                            ls[g].setNumbers(0);
                            System.out.println("Товар "+ ls[g].getName() + " закончился");
                  
                        }
                    }

                }


            }
            catch(Exception pe)
            {

                pe.printStackTrace();
            }
        }


            }

        };

        Thread monitor=new Thread(){
            public void run() {
                while (!stopped) {
                    try {
                        sleep(3000);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("Товар " + ls[i].getName() + " имеется в количестве " + ls[i].getNumbers());
                            //System.out.println("Товар "+ i + " имеется в количестве "+  k[i]);

                        }
                    } catch (Exception pe) {
                        System.out.println("Error heigh: " + pe);

                    }
                }
            }
        };

        for (i=0; i<cor(); i++){
            new Thread (post).start();
        }
        for (i=0; i<cor(); i++){
            new Thread (potr).start();
        }
        monitor.start();


    }
    
    public static void main(String args[])
    {	System.out.println("Склад запущен");


        new Main();

    }

}
