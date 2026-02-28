package MultithreadingQuestions.SemaphoreProblem.DownloadManager;

import java.util.concurrent.Semaphore;

/*
Build a file downloader application where only 5 downloads can happen in parallel to avoid server overload.
New download requests should wait using Semaphore.
Also prioritize certain downloads (VIP,HIGH,MEDIUM,LOW) based on Task Priority
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
         PriorityDownloadManager downloadManager = new PriorityDownloadManager(3);
         downloadManager.submitDownload("VIP_file_1", Priority.VIP, 3000);
        downloadManager.submitDownload("HIGH_file_1", Priority.HIGH, 2000);
        downloadManager.submitDownload("LOW_file_1", Priority.LOW, 1000);
        downloadManager.submitDownload("VIP_file_2", Priority.VIP, 3000);
        downloadManager.submitDownload("MEDIUM_file_1", Priority.MEDIUM, 2500);
        downloadManager.submitDownload("HIGH_file_2", Priority.HIGH, 2000);
        downloadManager.submitDownload("LOW_file_2", Priority.LOW, 1500);

        Thread.sleep(5000);
        downloadManager.resizePool(5);
        downloadManager.submitDownload("VIP_file_3",Priority.VIP,1000);
        downloadManager.submitDownload("LOW_file_3",Priority.LOW,1000);
        downloadManager.submitDownload("HIGH_file_4", Priority.HIGH, 15000);

        for (int i = 4; i <= 10; i++) {
            downloadManager.submitDownload("LOW_file_" + i, Priority.LOW, 3000);
        }
        Thread.sleep(10000);
        downloadManager.shutdown();
        System.out.println("Peak concurrent downloads: " + downloadManager.getPeakConcurrentDownloads());
    }
}

/*
282171456423200 | pool-1-thread-6 | START | HIGH_file_2 | Active: 3
282171456374700 | pool-1-thread-1 | START | VIP_file_1 | Active: 1
282171456379100 | pool-1-thread-3 | START | LOW_file_1 | Active: 2
Completed: LOW_file_1
282172484437500 | pool-1-thread-5 | START | MEDIUM_file_1 | Active: 3
282172484263100 | RELEASE | LOW_file_1 | Active(after release): 2
Completed: HIGH_file_2
282173490111000 | pool-1-thread-4 | START | VIP_file_2 | Active: 3
282173490014100 | RELEASE | HIGH_file_2 | Active(after release): 2
Completed: VIP_file_1
282174494819700 | RELEASE | VIP_file_1 | Active(after release): 2
282174494938800 | pool-1-thread-2 | START | HIGH_file_1 | Active: 3
Completed: MEDIUM_file_1
282174994134400 | pool-1-thread-7 | START | LOW_file_2 | Active: 3
282174994206300 | RELEASE | MEDIUM_file_1 | Active(after release): 2
Pool resized to 5 downloads
282176469771100 | pool-1-thread-8 | START | VIP_file_3 | Active: 4
282176469897600 | pool-1-thread-3 | START | LOW_file_4 | Active: 5
Completed: LOW_file_2
Completed: HIGH_file_1
282176502714100 | RELEASE | LOW_file_2 | Active(after release): 4
Completed: VIP_file_2
282176502788800 | pool-1-thread-6 | START | LOW_file_5 | Active: 5
282176503554300 | pool-1-thread-10 | START | HIGH_file_4 | Active: 5
282176502871100 | RELEASE | HIGH_file_1 | Active(after release): 4
282176502909600 | pool-1-thread-1 | START | LOW_file_6 | Active: 5
282176503386100 | RELEASE | VIP_file_2 | Active(after release): 4
Completed: VIP_file_3
282177482132100 | RELEASE | VIP_file_3 | Active(after release): 4
282177482344500 | pool-1-thread-5 | START | LOW_file_7 | Active: 5
Completed: LOW_file_4
282179470992600 | RELEASE | LOW_file_4 | Active(after release): 4
282179471153400 | pool-1-thread-9 | START | LOW_file_3 | Active: 5
Completed: LOW_file_6
Completed: LOW_file_5
282179519152000 | pool-1-thread-7 | START | LOW_file_8 | Active: 5
282179519327700 | pool-1-thread-2 | START | LOW_file_9 | Active: 5
282179519064000 | RELEASE | LOW_file_6 | Active(after release): 4
282179519258200 | RELEASE | LOW_file_5 | Active(after release): 4
Completed: LOW_file_3
282180480513900 | pool-1-thread-4 | START | LOW_file_10 | Active: 5
282180480502700 | RELEASE | LOW_file_3 | Active(after release): 4
Completed: LOW_file_7
282180496452500 | RELEASE | LOW_file_7 | Active(after release): 4
Completed: LOW_file_9
Completed: LOW_file_8
282182530410700 | RELEASE | LOW_file_8 | Active(after release): 3
282182530667500 | RELEASE | LOW_file_9 | Active(after release): 2
Completed: LOW_file_10
282183486594400 | RELEASE | LOW_file_10 | Active(after release): 1
282186477168000 | RELEASE | HIGH_file_4 | Active(after release): 0
Peak concurrent downloads: 5


*/