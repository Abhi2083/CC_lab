import java.util.*;

class Process {
    int pid;
    int arrival_time;
    int burst_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;
    int response_time;
}

public class SJF {
    static void sjf(Process[] proc, int n) {
        Arrays.sort(proc, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                if(p1.arrival_time == p2.arrival_time)
                    return p1.burst_time - p2.burst_time;
                else
                    return p1.arrival_time - p2.arrival_time;
            }
        });

        int time = 0;
        float total_turnaround_time = 0, total_waiting_time = 0, total_response_time = 0;

        System.out.println("\nGantt Chart: ");
        for(int i=0; i<n; i++){
            System.out.printf("| P%d ", proc[i].pid);
            if(proc[i].arrival_time > time){
                time = proc[i].arrival_time;
            }
            proc[i].response_time = time - proc[i].arrival_time;
            time += proc[i].burst_time;
            proc[i].completion_time = time; 
            proc[i].turnaround_time = proc[i].completion_time - proc[i].arrival_time;
            proc[i].waiting_time = proc[i].turnaround_time - proc[i].burst_time; 
            total_turnaround_time += proc[i].turnaround_time;
            total_waiting_time += proc[i].waiting_time;
            total_response_time += proc[i].response_time;
        }
        System.out.println("|\n\n");

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time\tResponse Time");
        for(int i=0; i<n; i++){
            System.out.printf("P%d\t%d\t\t%d\t\t%d\t\t%d\t\t\t%d\t\t\t%d\n", proc[i].pid, proc[i].arrival_time, proc[i].burst_time, proc[i].completion_time, proc[i].turnaround_time, proc[i].waiting_time, proc[i].response_time);
        }
        System.out.println("\n");

        System.out.printf("Average Turnaround Time = %.2f\n", total_turnaround_time/n);
        System.out.printf("Average Waiting Time = %.2f\n", total_waiting_time/n);
        System.out.printf("Average Response Time = %.2f\n", total_response_time/n);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number of processes: ");
            int n = sc.nextInt();
            Process[] proc = new Process[n];
            for(int i=0; i<n; i++){
                proc[i] = new Process();
                System.out.printf("Enter arrival time and burst time for process %d: ", i+1);
                proc[i].arrival_time = sc.nextInt();
                proc[i].burst_time = sc.nextInt();
                proc[i].pid = i+1;
            }

            sjf(proc, n);
        }
    }
}
