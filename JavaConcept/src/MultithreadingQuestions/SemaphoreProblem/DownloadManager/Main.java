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
Downloading Task :VIP_file_2 [Priority:VIP]
Downloading Task :HIGH_file_1 [Priority:HIGH]
Downloading Task :VIP_file_1 [Priority:VIP]
Completed Task :HIGH_file_1
Downloading Task :HIGH_file_2 [Priority:HIGH]
Completed Task :VIP_file_1
Completed Task :VIP_file_2
Downloading Task :MEDIUM_file_1 [Priority:MEDIUM]
Downloading Task :LOW_file_1 [Priority:LOW]
Completed Task :HIGH_file_2
Completed Task :LOW_file_1
Downloading Task :LOW_file_2 [Priority:LOW]
Pool resized to 5 downloads
Downloading Task :VIP_file_3 [Priority:VIP]
Downloading Task :HIGH_file_4 [Priority:HIGH]
Downloading Task :LOW_file_3 [Priority:LOW]
Completed Task :LOW_file_2
Completed Task :MEDIUM_file_1
Downloading Task :LOW_file_4 [Priority:LOW]
Downloading Task :LOW_file_5 [Priority:LOW]
Completed Task :LOW_file_3
Completed Task :VIP_file_3
Downloading Task :LOW_file_6 [Priority:LOW]
Downloading Task :LOW_file_7 [Priority:LOW]
Completed Task :LOW_file_4
Completed Task :LOW_file_5
Downloading Task :LOW_file_8 [Priority:LOW]
Downloading Task :LOW_file_9 [Priority:LOW]
Completed Task :LOW_file_6
Completed Task :LOW_file_7
Downloading Task :LOW_file_10 [Priority:LOW]
Timeout. Cancelling HIGH_file_4
Completed Task :LOW_file_8
Completed Task :LOW_file_9
Completed Task :LOW_file_10
Peak concurrent downloads: 5
*/