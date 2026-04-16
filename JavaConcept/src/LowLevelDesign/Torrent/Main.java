package LowLevelDesign.Torrent;

import java.util.List;
import java.util.Set;

/*
Seeder (original uploader) ->  has ALL chunks
Peers -> have SOME chunks
Leechers ->  downloading
File Index - Identifies which file the chunk belongs to. It is basically the index in  file list
Offset - Tells where inside that file to write the chunk data. It is  the byte position (start point)
 */

/*
Torrent does NOT download files directly
It downloads chunks (pieces):
Example in main:
Chunk1 ->  part of file1
Chunk2 ->  part of file1
Chunk3-> part of file2
Chunk4 -> part of file2

 */

/*
Steps
Step 1: Thread Pool Starts
Step 2: Each thread runs worker loop
    while(not complete):
    pick chunk
    download
    write
Step 3: Example Execution
    Thread-1:
    picks Chunk 1 ->  from Peer-1 ->  writes to fileIndex=0 offset=0
    Thread-2:
    picks Chunk 3 -> from Peer-2 ->  writes to fileIndex=1 offset=0
    Thread-3:
    picks Chunk 2 ->  from Peer-3 ->  writes to fileIndex=0 offset=500
    Thread-4:
    picks Chunk 4 ->  from Peer-2 ->  writes to fileIndex=1 offset=500

Final Result

Even though download order was random:

Chunk3 came before Chunk1
Chunk4 came before Chunk2

 Files are STILL correct because:
fileIndex + offset ensures correct placement
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== TORRENT DOWNLOAD START =====");

        TorrentFile file1 = new TorrentFile(
                "movie_part1.mp4",
                1000,
                List.of(1, 2)
        );

        TorrentFile file2 = new TorrentFile(
                "movie_part2.mp4",
                1000,
                List.of(3, 4)
        );

        List<TorrentFile> files = List.of(file1, file2);


        List<Chunk> chunks = List.of(
                new Chunk(1, "h1", 0, 0),
                new Chunk(2, "h2", 0, 500),
                new Chunk(3, "h3", 1, 0),
                new Chunk(4, "h4", 1, 500)
        );

        /*
        File1 -> chunks [1, 2]
        File2 -> chunks [3, 4]
         */

        TorrentMetadata metadata = new TorrentMetadata(files, chunks);


        ChunkManager chunkManager = new ChunkManager(metadata.getChunks());

        Peer peer1 = new Peer("Peer-1", Set.of(1, 2));
        Peer peer2 = new Peer("Peer-2", Set.of(3, 4));
        Peer peer3 = new Peer("Peer-3", Set.of(2, 3));

        PeerManager peerManager = new PeerManager(
                List.of(peer1, peer2, peer3)
        );

        RateLimiter globalLimiter = new TokenBucketRateLimiter(100);
        RateLimiter perPeerLimiter = new TokenBucketRateLimiter(50);

        RateLimiter limiter = new CompositeRateLimiter(
                List.of(globalLimiter, perPeerLimiter)
        );

        FileWriterService writer = new FileWriterService();

        ChunkSelectionStrategy strategy = new SequentialStrategy();

        DownloadManager downloadManager = new DownloadManager(
                chunkManager,
                peerManager,
                strategy,
                limiter,
                writer,
                4 // threads
        );
        downloadManager.start();
        /*
        1. Pick ANY chunk (not file-wise)
        2. Find peer having that chunk
        3. Download it
        4. Write to correct file using (fileIndex + offset)
         */

        System.out.println("===== DOWNLOAD INITIATED =====");
    }
}

/*
Real Scenario:
User downloads 1 movie
Movie.mp4 (1000 MB)
->  split into 100 chunks
->  each chunk = 10 MB

---------------------------------
1️) User joins torrent
Gets metadata (.torrent or magnet)
Knows:
Total chunks = 100
Each chunk hash
2️) Connects to peers
Peer A ->  has chunks [1,2,3,10]
Peer B ->  has chunks [4,5,6]
Peer C ->  has chunks [7,8,9]

 System now knows  who has what

3) Parallel download starts

Threads do:

Thread-1 ->  chunk 1 from Peer A
Thread-2 ->  chunk 4 from Peer B
Thread-3 ->  chunk 7 from Peer C
Thread-4 ->  chunk 2 from Peer A

 -  Multiple peers
 - Multiple chunks
-  Parallel

Peer connection uses:
chunkId (piece index)

Example:

"Give me chunk 5"
-> Offset & fileIndex used for:
Writing chunk to disk

Example:

file.seek(offset);
write(data);

chunkId	->  network (peer communication)
fileIndex -> which file
offset	-> where to write

Flow
1. Pick chunkId = 5
2. Find peer that has chunk 5
3. Download chunk 5
4. Verify hash
5. Write to file using offset
Example
Chunk 5 → belongs to Movie.mp4
offset = 40MB

After download:

file.seek(40MB);
file.write(chunkData);

Torrent file ONLY knows:

- Total chunks
- Hash of each chunk
- Tracker / info-hash

 It does NOT know peer ->  chunk mapping

Then how do we know which peer has which chunk?
After connecting to a peer:

Peer sends → BITFIELD message

Example:

Peer A -> [1,1,0,0,1]

 Means:

has chunk 1
has chunk 2
missing chunk 3
missing chunk 4
has chunk 5

 Real-world situation
Initially:
Seeder ->  has ALL chunks [1..100]
After some time:
Peer A ->  [1,2,3,4]
Peer B ->  [5,6,7]
Peer C ->  [8,9,10]
Peer D ->  [2,5,8]

Everyone has partial data

Why this happens?

Because:

-> Peers download gradually

They don’t get full file instantly.

Peer = downloading + uploading simultaneously

So:

Peer A downloads chunk 1 ->  now shares it
Peer B downloads chunk 2 ->  now shares it
Network spreads chunks organically

Seeder
   |
Peer A gets [1,2]
   |
Peer B gets [3,4]
   |
Peer C gets [2,3]

->  Network becomes distributed

-> Final State (Healthy Torrent)
Chunks spread across peers
NOT all peers have everything
BUT collectively->  full file exists
-> Important Edge Case

If: No peer has chunk 50 Then:
->  download stuck
->  torrent dead
->  That’s why this exists:
-> Rarest First Strategy
Download rare chunks first
 */