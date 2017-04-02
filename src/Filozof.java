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
