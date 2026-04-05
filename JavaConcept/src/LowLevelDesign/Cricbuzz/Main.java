package LowLevelDesign.Cricbuzz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main ob = new Main();

        Team teamA = ob.addTeam("India");
        Team teamB = ob.addTeam("SriLanka");

        MatchType matchType = new T20MatchType();
        Match match = new Match(teamA, teamB, null, "SMS STADIUM", matchType);
        match.startMatch();
    }
    private Team addTeam(String name) {

        Queue<PlayerDetails> playerDetails = new LinkedList<>();

        PlayerDetails p1 = addPlayer(name+"1", PlayerType.ALLROUNDER);
        PlayerDetails p2 = addPlayer(name+"2", PlayerType.ALLROUNDER);
        PlayerDetails p3 = addPlayer(name+"3", PlayerType.ALLROUNDER);
        PlayerDetails p4 = addPlayer(name+"4", PlayerType.ALLROUNDER);
        PlayerDetails p5 = addPlayer(name+"5", PlayerType.ALLROUNDER);
        PlayerDetails p6 = addPlayer(name+"6", PlayerType.ALLROUNDER);
        PlayerDetails p7 = addPlayer(name+"7", PlayerType.ALLROUNDER);
        PlayerDetails p8 = addPlayer(name+"8", PlayerType.ALLROUNDER);
        PlayerDetails p9 = addPlayer(name+"9", PlayerType.ALLROUNDER);
        PlayerDetails p10 = addPlayer(name+"10", PlayerType.ALLROUNDER);
        PlayerDetails p11 = addPlayer(name+"11", PlayerType.ALLROUNDER);

        playerDetails.add(p1);
        playerDetails.add(p2);
        playerDetails.add(p3);
        playerDetails.add(p4);
        playerDetails.add(p5);
        playerDetails.add(p6);
        playerDetails.add(p7);
        playerDetails.add(p8);
        playerDetails.add(p9);
        playerDetails.add(p10);
        playerDetails.add(p11);

        List<PlayerDetails> bowlers = new ArrayList<>();
        bowlers.add(p8);
        bowlers.add(p9);
        bowlers.add(p10);
        bowlers.add(p11);

        Team team = new Team(name, playerDetails, new ArrayList<>(), bowlers);
        return team;

    }

    private PlayerDetails addPlayer(String name, PlayerType playerType) {

        Person person = new Person();
        person.name = name;
        PlayerDetails playerDetails = new PlayerDetails(person, playerType);
        return playerDetails;
    }
}

/*

INNING 1 -- total Run: 141
---Batting ScoreCard : India---
PlayerName: India1 -- totalRuns: 13 -- totalBallsPlayed: 5 -- 4s: 1 -- 6s: 1 -- outby: SriLanka9
PlayerName: India2 -- totalRuns: 20 -- totalBallsPlayed: 6 -- 4s: 1 -- 6s: 2 -- outby: SriLanka11
PlayerName: India3 -- totalRuns: 27 -- totalBallsPlayed: 8 -- 4s: 1 -- 6s: 3 -- outby: SriLanka10
PlayerName: India4 -- totalRuns: 33 -- totalBallsPlayed: 9 -- 4s: 3 -- 6s: 3 -- outby: notout
PlayerName: India5 -- totalRuns: 14 -- totalBallsPlayed: 4 -- 4s: 2 -- 6s: 1 -- outby: SriLanka11
PlayerName: India6 -- totalRuns: 19 -- totalBallsPlayed: 8 -- 4s: 1 -- 6s: 1 -- outby: SriLanka9
PlayerName: India7 -- totalRuns: 12 -- totalBallsPlayed: 3 -- 4s: 0 -- 6s: 2 -- outby: SriLanka10
PlayerName: India8 -- totalRuns: 0 -- totalBallsPlayed: 1 -- 4s: 0 -- 6s: 0 -- outby: SriLanka10
PlayerName: India9 -- totalRuns: 0 -- totalBallsPlayed: 1 -- 4s: 0 -- 6s: 0 -- outby: SriLanka10
PlayerName: India10 -- totalRuns: 0 -- totalBallsPlayed: 1 -- 4s: 0 -- 6s: 0 -- outby: SriLanka10
PlayerName: India11 -- totalRuns: 3 -- totalBallsPlayed: 3 -- 4s: 0 -- 6s: 0 -- outby: SriLanka8

---Bowling ScoreCard : SriLanka---
PlayerName: SriLanka8 -- totalOversThrown: 2 -- totalRunsGiven: 41 -- WicketsTaken: 1
PlayerName: SriLanka9 -- totalOversThrown: 2 -- totalRunsGiven: 41 -- WicketsTaken: 2
PlayerName: SriLanka10 -- totalOversThrown: 2 -- totalRunsGiven: 25 -- WicketsTaken: 5
PlayerName: SriLanka11 -- totalOversThrown: 2 -- totalRunsGiven: 34 -- WicketsTaken: 2

INNING 2 -- total Run: 143
---Batting ScoreCard : SriLanka---
PlayerName: SriLanka1 -- totalRuns: 14 -- totalBallsPlayed: 5 -- 4s: 3 -- 6s: 0 -- outby: India8
PlayerName: SriLanka2 -- totalRuns: 25 -- totalBallsPlayed: 6 -- 4s: 0 -- 6s: 4 -- outby: India10
PlayerName: SriLanka3 -- totalRuns: 40 -- totalBallsPlayed: 13 -- 4s: 2 -- 6s: 4 -- outby: India11
PlayerName: SriLanka4 -- totalRuns: 25 -- totalBallsPlayed: 8 -- 4s: 2 -- 6s: 2 -- outby: India11
PlayerName: SriLanka5 -- totalRuns: 3 -- totalBallsPlayed: 3 -- 4s: 0 -- 6s: 0 -- outby: India8
PlayerName: SriLanka6 -- totalRuns: 6 -- totalBallsPlayed: 3 -- 4s: 1 -- 6s: 0 -- outby: India8
PlayerName: SriLanka7 -- totalRuns: 23 -- totalBallsPlayed: 6 -- 4s: 1 -- 6s: 3 -- outby: India11
PlayerName: SriLanka8 -- totalRuns: 1 -- totalBallsPlayed: 1 -- 4s: 0 -- 6s: 0 -- outby: notout
PlayerName: SriLanka9 -- totalRuns: 6 -- totalBallsPlayed: 1 -- 4s: 0 -- 6s: 1 -- outby: notout
PlayerName: SriLanka10 -- totalRuns: 0 -- totalBallsPlayed: 0 -- 4s: 0 -- 6s: 0 -- outby: notout
PlayerName: SriLanka11 -- totalRuns: 0 -- totalBallsPlayed: 0 -- 4s: 0 -- 6s: 0 -- outby: notout

---Bowling ScoreCard : India---
PlayerName: India8 -- totalOversThrown: 2 -- totalRunsGiven: 34 -- WicketsTaken: 3
PlayerName: India9 -- totalOversThrown: 2 -- totalRunsGiven: 47 -- WicketsTaken: 0
PlayerName: India10 -- totalOversThrown: 2 -- totalRunsGiven: 38 -- WicketsTaken: 1
PlayerName: India11 -- totalOversThrown: 1 -- totalRunsGiven: 24 -- WicketsTaken: 3

---WINNER---SriLanka
 */