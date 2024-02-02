import java.util.*;

class Process {
    int id, at, bt, btt, ct, fct, tat, wt, rt;
}

public class roundRobin {
    static Process[] queue = new Process[50];
    static int start = -1, end = -1;

    static void push(Process p) {
        if (start == -1 && end == -1)
            start = end = 0;
        else if (start != 0 && end == 49)
            end = 0;
        else
            end++;
        queue[end] = p;
    }

    static Process pop() {
        Process temp = queue[start];
        if (start == end)
            start = end = -1;
        else
            start++;
        return temp;
    }

    static void sort(int[] i, int n, Process[] p, int[] c) {
        if (i[0] == 0) {
            Process t;
            for (int j = n - 1; j > 0; j--) {
                if (p[j].at < p[j - 1].at || (p[j].at == p[j - 1].at && p[j].id < p[j - 1].id)) {
                    t = p[j - 1];
                    p[j - 1] = p[j];
                    p[j] = t;
                }
            }
        }
        while (p[i[0]].at > c[0])
            c[0] = c[0] + 1;
        while (i[0] < n && p[i[0]].at <= c[0]) {
            push(p[i[0]]);
            i[0] = i[0] + 1;
        }
    }

    static void complete(int n, Process[] p, int tq) {
        int[] i = { 0 };
        int[] c = { 0 };
        sort(i, n, p, c);
        while (start != -1) {
            Process temp = pop();
            int id = temp.id, pi;
            for (pi = 0; pi < n; pi++) {
                if (p[pi].id == id)
                    break;
            }
            if (p[pi].bt == p[pi].btt) {
                p[pi].fct = c[0];
            }
            if (p[pi].btt <= tq) {
                c[0] += p[pi].btt;
                p[pi].btt = 0;
                p[pi].ct = c[0];
            } else {
                c[0] += tq;
                p[pi].btt -= tq;
            }
            if (i[0] < n)
                sort(i, n, p, c);
            if (p[pi].btt != 0)
                push(p[pi]);
        }
    }

    static void time(int n, Process[] p) {
        for (int i = 0; i < n; i++) {
            p[i].tat = p[i].ct - p[i].at;
            p[i].wt = p[i].tat - p[i].bt;
            p[i].rt = p[i].fct - p[i].at;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n, i, tq;
            float avgtat = 0, avgwt = 0;

            System.out.print("Enter the number of processes and time quantum: ");
            n = scanner.nextInt();
            tq = scanner.nextInt();

            Process[] p = new Process[n];

            System.out.print("Enter id, arrival time, burst time: ");
            for (i = 0; i < n; i++) {
                p[i] = new Process();
                p[i].id = scanner.nextInt();
                p[i].at = scanner.nextInt();
                p[i].bt = scanner.nextInt();
                p[i].btt = p[i].bt;
            }

            complete(n, p, tq);
            time(n, p);

            System.out.println("Pid AT BT CT TAT WT RT");
            for (i = 0; i < n; i++) {
                System.out.printf("P%d  %2d %2d %2d %3d %2d %2d\n", p[i].id, p[i].at, p[i].bt, p[i].ct, p[i].tat, p[i].wt,
                        p[i].rt);
            }

            for (i = 0; i < n; i++) {
                avgtat += p[i].tat;
                avgwt += p[i].wt;
            }

            System.out.printf("Avg TAT:- %.3f\nAvg WT:- %.3f\n", (avgtat / n), (avgwt / n));

            scanner.close(); // Close the Scanner to avoid resource leak
        }
    }

}
