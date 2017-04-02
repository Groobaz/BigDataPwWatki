/**
 * Created by mrudzki on 02.04.17.
 */
class Filozof extends Thread{

    private static int z=1;
    private int nr;
    private Stol stolik;

    public Filozof(Stol s) {
        nr = z;
        z++;
        stolik = s;
    }

    public void run() {
        try {
            for(;;) {
                sleep(2000);
                if (stolik.wezPaleczke(nr)) {
                    sleep(1000+(int)(Math.random()*5000));
                    stolik.polozPaleczke(nr);
                }
            }
        }
        catch (InterruptedException e) { }
    }

}

class Stol {

    private Posilek fil;
    private int[] paleczka = new int[5]; // 1 - paleczka w uzyciu, 0 - dostepna
    private int[] je = new int[5];

    public Stol(Posilek r) {
        fil = r;
        for (int i=0;i<5;i++)
            paleczka[i]=0;
        for (int k=0;k<5;k++)
            je[k]=0;
    }

    public synchronized boolean wezPaleczke(int f) {
        if (paleczka[f-1]==0) {
            paleczka[f-1]=1;
            if (f==5) {
                if (paleczka[0]==1) {
                    paleczka[f-1]=0;
                    rysuj();
                    return false;
                }
                else {
                    paleczka[0]=1;
                    je[f-1]=1;
                    rysuj();
                    return true;
                }
            }
            else {
                if (paleczka[f]==1) {
                    paleczka[f-1]=0;
                    rysuj();
                    return false;
                }
                else {
                    paleczka[f]=1;
                    je[f-1]=1;
                    rysuj();
                    return true;
                }
            }
        }

        else return false;
    }

    public synchronized void polozPaleczke(int f) {
        if (f==5)
            paleczka[0]=0;
        else
            paleczka[f]=0;
        paleczka[f-1]=0;
        je[f-1]=0;
        rysuj();
    }

    public int[] p(){
        return paleczka;
    }

    public int[] j(){
        return je;
    }

    public  synchronized void rysuj() {
        fil.repaint();
    }
}
